package by.jd2.jdbc.util;

import java.util.ResourceBundle;

public class ReadFileProperties {

    private static final String NAME_PROPERTIES = "database";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static String getValue(String key) {
        ResourceBundle resource = ResourceBundle.getBundle(NAME_PROPERTIES);
        return resource.getString(key);
    }

    public static String getUrl() {
        return getValue(URL);
    }

    public static String getUser() {
        return getValue(USER);
    }

    public static String getPassword() {
        return getValue(PASSWORD);
    }
}
