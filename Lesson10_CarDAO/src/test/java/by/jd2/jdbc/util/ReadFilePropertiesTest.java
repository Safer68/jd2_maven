package by.jd2.jdbc.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadFilePropertiesTest {

    @Test
    public void testGetUrl() {
        String actual = ReadFileProperties.getUrl();
        String expected = "jdbc:h2:~/bdLesson10";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUser() {
        String actual = ReadFileProperties.getUser();
        String expected = "sa";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPassword() {
        String actual = ReadFileProperties.getPassword();
        String expected = "";
        assertEquals(expected, actual);
    }
}