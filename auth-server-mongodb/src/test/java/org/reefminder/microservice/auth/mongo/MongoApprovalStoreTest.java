package org.reefminder.microservice.auth.mongo;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.reefminder.microservice.auth.mongo.commons.SecurityRDG;
import org.springframework.security.oauth2.provider.approval.Approval;
import uk.co.caeldev.springsecuritymongo.domain.MongoApproval;
import uk.co.caeldev.springsecuritymongo.repositories.MongoApprovalRepository;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.list;
import static org.reefminder.microservice.auth.mongo.commons.SecurityRDG.string;

@RunWith(MockitoJUnitRunner.class)
public class MongoApprovalStoreTest {

    @Mock
    private MongoApprovalRepository mongoApprovalRepository;

    private MongoApprovalStore mongoApprovalStore;

    @Before
    public void setup() {
        mongoApprovalStore = new MongoApprovalStore(mongoApprovalRepository);
    }

    @Test
    public void shouldAddApprovals() {
        //Given
        final List<Approval> approvals = list(SecurityRDG.ofApproval()).next();

        //And
        given(mongoApprovalRepository.updateOrCreate(anyCollectionOf(MongoApproval.class))).willReturn(true);

        //When
        final boolean result = mongoApprovalStore.addApprovals(approvals);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenSomeApprovalsFailedToUpdateOrInsert() {
        //Given
        final List<Approval> approvals = list(SecurityRDG.ofApproval()).next();

        //And
        given(mongoApprovalRepository.updateOrCreate(anyCollectionOf(MongoApproval.class))).willReturn(false);

        //When
        final boolean result = mongoApprovalStore.addApprovals(approvals);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldRevokeApprovalsByRemoveWhenHandleRevocationsAsExpiryIsFalse() {
        //Given
        final List<Approval> approvals = list(SecurityRDG.ofApproval()).next();

        //And
        mongoApprovalStore.setHandleRevocationsAsExpiry(false);

        //And
        given(mongoApprovalRepository.deleteByUserIdAndClientIdAndScope(any(MongoApproval.class))).willReturn(true);

        //When
        final boolean result = mongoApprovalStore.revokeApprovals(approvals);

        //Then
        assertThat(result).isTrue();
        verify(mongoApprovalRepository, never()).updateExpiresAt(any(LocalDate.class), any(MongoApproval.class));
    }

    @Test
    public void shouldRevokeApprovalsByUpdateWhenHandleRevocationsAsExpiryIsTrue() {
        //Given
        final List<Approval> approvals = list(SecurityRDG.ofApproval()).next();

        //And
        mongoApprovalStore.setHandleRevocationsAsExpiry(true);

        //And
        given(mongoApprovalRepository.updateExpiresAt(any(LocalDate.class), any(MongoApproval.class))).willReturn(true);

        //When
        final boolean result = mongoApprovalStore.revokeApprovals(approvals);

        //Then
        assertThat(result).isTrue();
        verify(mongoApprovalRepository, never()).deleteByUserIdAndClientIdAndScope(any(MongoApproval.class));
    }

    @Test
    public void shouldGetApprovals() {
        //Given
        final String userId = string().next();
        final String clientId = string().next();

        //And
        final List<MongoApproval> expectedMongoApprovals = list(SecurityRDG.ofMongoApproval()).next();
        given(mongoApprovalRepository.findByUserIdAndClientId(userId, clientId)).willReturn(expectedMongoApprovals);

        //When
        final Collection<Approval> approvals = mongoApprovalStore.getApprovals(userId, clientId);

        //Then
        assertThat(approvals).hasSameSizeAs(expectedMongoApprovals);
    }


}
