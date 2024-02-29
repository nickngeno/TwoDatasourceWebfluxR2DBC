package com.kimmy.two_database_connection.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.r2dbc.postgresql")
@EnableR2dbcRepositories(basePackages = "com.kimmy.two_database_connection.repository.product", entityOperationsRef ="postgresqlR2dbcEntityOperations" )
public class PostgresqlConfiguration extends AbstractR2dbcConfiguration {

    private String url;
    private String username;
    private String password;

    @Override
    @Bean(name = "postgresqlConnectionFactory")
    public ConnectionFactory connectionFactory() {
        try {
            URI uri = new URI(url.substring(6));
            String hostname = uri.getHost();
            int port = uri.getPort();
            String database = uri.getPath().substring(1);

            log.info("Connecting to Postgresql database at {}", url);

            ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.builder()
                    .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                    .option(ConnectionFactoryOptions.HOST, hostname)
                    .option(ConnectionFactoryOptions.PORT, port)
                    .option(ConnectionFactoryOptions.DATABASE, database)
                    .option(ConnectionFactoryOptions.USER, this.getUsername())
                    .option(ConnectionFactoryOptions.PASSWORD, this.getPassword())
                    .build();

            return ConnectionFactories.get(connectionFactoryOptions);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public R2dbcEntityOperations postgresqlR2dbcEntityOperations(@Qualifier("postgresqlConnectionFactory") ConnectionFactory connectionFactory) {
        log.info("Initializing TransactionalManager : " + connectionFactory.getMetadata().getName());
//        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);
        return new R2dbcEntityTemplate(connectionFactory);
    }
}
