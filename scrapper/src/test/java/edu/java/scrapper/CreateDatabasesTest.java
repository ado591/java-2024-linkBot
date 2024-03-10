package edu.java.scrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class CreateDatabasesTest extends IntegrationTest {

    @ParameterizedTest
    @CsvSource({"SELECT EXISTS (SELECT FROM pg_tables WHERE tablename = 'chat');",
        "SELECT EXISTS (SELECT FROM pg_tables WHERE tablename = 'link');",
        "SELECT EXISTS (SELECT FROM pg_tables WHERE tablename = 'chat_link');"})
    public void tableExistsTest(String query) {
        try (
            Connection connection = DriverManager.getConnection(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
            );
            Statement statement = connection.createStatement();
            ResultSet changelog = connection
                .getMetaData()
                .getTables(null, null, "databasechangelog", null);
            ResultSet changeloglock = connection
                .getMetaData()
                .getTables(null, null, "databasechangeloglock", null);
        ) {
            Assertions.assertTrue(changelog.next());
            Assertions.assertTrue(changeloglock.next());
            ResultSet result = statement.executeQuery(query);
            assertTrue(result.next());
        } catch (SQLException sqlException) {
            Assertions.fail("sql exception");
        }
    }

}
