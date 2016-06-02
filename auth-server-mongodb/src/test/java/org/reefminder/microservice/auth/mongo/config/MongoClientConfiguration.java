package org.reefminder.microservice.auth.mongo.config;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(MongoSettings.class)
@Profile("test")
public class MongoClientConfiguration {

    @Bean
    public MongoClient mongoClient(MongoSettings mongoSettings) throws IOException {
        Fongo fongo = new Fongo(mongoSettings.getDatabase());
        return fongo.getMongo();
    }

}
