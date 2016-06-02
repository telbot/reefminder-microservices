package org.reefminder.microservice.auth.mongo.commons;

import net.minidev.json.JSONObject;
import org.reefminder.microservice.auth.mongo.builders.MongoApprovalBuilder;
import org.reefminder.microservice.auth.mongo.builders.MongoOAuth2AccessTokenBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.approval.Approval;
import uk.co.caeldev.springsecuritymongo.domain.MongoApproval;
import uk.co.caeldev.springsecuritymongo.domain.MongoOAuth2AccessToken;
import uk.org.fyodor.generators.Generator;
import uk.org.fyodor.generators.characters.CharacterSetFilter;

import java.io.Serializable;

import static org.reefminder.microservice.auth.mongo.builders.ApprovalBuilder.approvalBuilder;

public class SecurityRDG extends uk.org.fyodor.generators.RDG {

    public static Generator<String> ofEscapedString() {
        return new Generator<String>() {
            @Override
            public String next() {
                return string(30, CharacterSetFilter.LettersAndDigits).next();
            }
        };
    }

    public static Generator<GrantedAuthority> ofGrantedAuthority() {
        return new Generator<GrantedAuthority>() {
            @Override
            public GrantedAuthority next() {
                return new SimpleGrantedAuthority(string().next());
            }
        };
    }

    public static Generator<GrantedAuthority> ofInvalidAuthority() {
        return new Generator<GrantedAuthority>() {
            @Override
            public GrantedAuthority next() {
                return new SimpleGrantedAuthority("");
            }
        };
    }

    public static Generator<MongoOAuth2AccessToken> ofMongoOAuth2AccessToken() {
        return new Generator<MongoOAuth2AccessToken>() {
            @Override
            public MongoOAuth2AccessToken next() {
                return MongoOAuth2AccessTokenBuilder.mongoOAuth2AccessTokenBuilder().build();
            }
        };
    }

    public static Generator<Object> objectOf(final Generator generator) {
        return new Generator<Object>() {
            @Override
            public Object next() {
                return generator.next();
            }
        };
    }

    public static Generator<Serializable> serializableOf(final Generator<? extends Serializable> generator) {
        return new Generator<Serializable>() {
            @Override
            public Serializable next() {
                return generator.next();
            }
        };
    }

    public static Generator<Approval> ofApproval() {
        return new Generator<Approval>() {
            @Override
            public Approval next() {
                return approvalBuilder().build();
            }
        };
    }

    public static Generator<MongoApproval> ofMongoApproval() {
        return new Generator<MongoApproval>() {
            @Override
            public MongoApproval next() {
                return MongoApprovalBuilder.mongoApprovalBuilder().build();
            }
        };
    }
}
