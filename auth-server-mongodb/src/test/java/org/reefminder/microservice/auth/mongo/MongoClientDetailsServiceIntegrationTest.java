package org.reefminder.microservice.auth.mongo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.reefminder.microservice.auth.mongo.config.ApplicationConfiguration;
import org.reefminder.microservice.auth.mongo.domain.MongoClientDetails;
import org.reefminder.microservice.auth.mongo.repositories.MongoClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.reefminder.microservice.auth.mongo.builders.MongoClientDetailsBuilder;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
@ActiveProfiles("test")
@DirtiesContext
@WebAppConfiguration
public class MongoClientDetailsServiceIntegrationTest {

    @Autowired
    private MongoClientDetailsService mongoClientDetailsService;

    @Autowired
    private MongoClientDetailsRepository mongoClientDetailsRepository;

    @Test
    public void shouldPersistClientDetailsSuccessfully() throws Exception {
        //Given
        final MongoClientDetails clientDetails = MongoClientDetailsBuilder.mongoClientDetailsBuilder().build();

        //When
        mongoClientDetailsService.addClientDetails(clientDetails);

        //Then
        final MongoClientDetails expectedClientDetails = mongoClientDetailsRepository.findByClientId(clientDetails.getClientId());
        assertThat(expectedClientDetails).isNotNull();
        assertThat(expectedClientDetails).isEqualTo(clientDetails);
    }

    @Test
    public void shouldLoadClientDetailsByIdSuccessfully() throws Exception {
        //Given
        final MongoClientDetails clientDetails = MongoClientDetailsBuilder.mongoClientDetailsBuilder().build();

        //And
        mongoClientDetailsService.addClientDetails(clientDetails);

        //When
        final ClientDetails expectedClientDetails = mongoClientDetailsService.loadClientByClientId(clientDetails.getClientId());

        //Then
        assertThat(expectedClientDetails).isNotNull();
        assertThat(expectedClientDetails).isEqualTo(clientDetails);
    }

    @Test
    public void shouldGetListOfClientDetailsByIdSuccessfully() throws Exception {
        //Given
        final MongoClientDetails clientDetails = MongoClientDetailsBuilder.mongoClientDetailsBuilder().build();

        //And
        mongoClientDetailsService.addClientDetails(clientDetails);

        //When
        final List<ClientDetails> expectedClientDetails = mongoClientDetailsService.listClientDetails();

        //Then
        assertThat(expectedClientDetails).contains(clientDetails);
    }
}
