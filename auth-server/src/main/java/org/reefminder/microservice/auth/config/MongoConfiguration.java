package org.reefminder.microservice.auth.config;

import org.reefminder.microservice.auth.services.SecurityContextService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.token.ClientKeyGenerator;
import org.springframework.security.oauth2.client.token.DefaultClientKeyGenerator;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

@Configuration
public class MongoConfiguration {

    @Configuration
    static class SpringSecurityConfiguration {

        @Bean
        public SecurityContextService securityContextService() {
            return new SecurityContextService();
        }

        @Bean
        public AuthenticationKeyGenerator authenticationKeyGenerator() {
            return new DefaultAuthenticationKeyGenerator();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public ClientKeyGenerator clientKeyGenerator(){
            return new DefaultClientKeyGenerator();
        }

    }
}
