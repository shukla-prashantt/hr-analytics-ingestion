package com.fsdprojects.hr_analytics_ingestion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
public class EmployeeMigrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void migrationAddedJoinedOnColumn() throws Exception  {
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT joined_on FROM employee LIMIT 1")) {
            // execution succeeds (even if no rows)
            ps.execute();
            // if the column wasn't present, this would throw SQLException
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
