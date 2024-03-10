package edu.java.scrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class IntegrationTest {
    private static final String DOCKER_IMAGE_NAME = "postgres:16";
    private static final String DATABASE_NAME = "scrapper";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASS = "postgres";
    private static final String CHANGELOG_FILE = "master.xml";
    protected static PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse(DOCKER_IMAGE_NAME))
            .withDatabaseName(DATABASE_NAME)
            .withUsername(DATABASE_USERNAME)
            .withPassword(DATABASE_PASS);
        postgreSQLContainer.start();
        runMigrations(postgreSQLContainer);
    }

    private static void runMigrations(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {
        Path changelogPath = new File(".").toPath().toAbsolutePath().resolve("../migrations/");

        try (
            Connection connection = DriverManager.getConnection(
                jdbcDatabaseContainer.getJdbcUrl(),
                jdbcDatabaseContainer.getUsername(),
                jdbcDatabaseContainer.getPassword()
            )
        ) {
            Database database = DatabaseFactory
                .getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase =
                new Liquibase(CHANGELOG_FILE, new DirectoryResourceAccessor(changelogPath), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException | FileNotFoundException e) {
            Assertions.fail("ошибка при выполнении миграции базы данных");
        }
    }

    @DynamicPropertySource
    public static void jdbcProperties(@NotNull DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }
}
