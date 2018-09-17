package cn.webfuse.framework.mongo.option;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MongoClient.class)
@EnableConfigurationProperties(MongoOptionProperties.class)
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
public class MongoOptionAutoConfiguration {

    @Bean
    public MongoClientOptions mongoClientOptions(MongoOptionProperties mongoOptionProperties) {
        if (mongoOptionProperties == null) {
            return new MongoClientOptions.Builder().build();
        }

        return new MongoClientOptions.Builder()
                .minConnectionsPerHost(mongoOptionProperties.getMinConnectionsPerHost())
                .connectionsPerHost(mongoOptionProperties.getMaxConnectionsPerHost())
                .threadsAllowedToBlockForConnectionMultiplier(mongoOptionProperties.getThreadsAllowedToBlockForConnectionMultiplier())
                .serverSelectionTimeout(mongoOptionProperties.getServerSelectionTimeout())
                .maxWaitTime(mongoOptionProperties.getMaxWaitTime())
                .maxConnectionIdleTime(mongoOptionProperties.getMaxConnectionIdleTime())
                .maxConnectionLifeTime(mongoOptionProperties.getMaxConnectionLifeTime())
                .connectTimeout(mongoOptionProperties.getConnectTimeout())
                .socketTimeout(mongoOptionProperties.getSocketTimeout())
                .sslEnabled(mongoOptionProperties.isSslEnabled())
                .sslInvalidHostNameAllowed(mongoOptionProperties.isSslInvalidHostNameAllowed())
                .alwaysUseMBeans(mongoOptionProperties.isAlwaysUseMBeans())
                .heartbeatFrequency(mongoOptionProperties.getHeartbeatFrequency())
                .heartbeatConnectTimeout(mongoOptionProperties.getHeartbeatConnectTimeout())
                .heartbeatSocketTimeout(mongoOptionProperties.getSocketTimeout())
                .localThreshold(mongoOptionProperties.getLocalThreshold())
                .build();
    }

}
