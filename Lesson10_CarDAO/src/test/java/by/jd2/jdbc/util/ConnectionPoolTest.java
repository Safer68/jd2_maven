package by.jd2.jdbc.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    @Test
    public void testGetConnection() throws SQLException {
        Connection connection = new ConnectionPool().getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
        String expected = connection.getMetaData().getURL();
        String actual = "jdbc:h2:~/bdLesson10";
        assertEquals(expected, actual);
        connection.close();
        assertTrue(connection.isClosed());
    }
}