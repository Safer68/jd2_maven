package by.jd2.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;

import static by.jd2.jdbc.util.ReadFileProperties.*;

public class ConnectionPool {
    private final int connPoolSize = 5;
    private final Deque<Connection> connectionList;

    public ConnectionPool(){
        connectionList = new LinkedList<>();
        try {
            createPoll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createPoll() throws SQLException {
        while (connectionList.size() < connPoolSize) {
            connectionList.add(DriverManager.getConnection(getUrl(), getUser(), getPassword()));
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        int minConnPoolSize = 2;
        if (connectionList.size() < minConnPoolSize) {
            connection = connectionList.pollLast();
            createPoll();
        } else connection = connectionList.pollLast();
        return connection;
    }

    public void backConnection(Connection connection) throws SQLException {
            if (this.connectionList.size() < this.connPoolSize) {
                this.connectionList.add(connection);
            } else {
                connection.close();
            }
    }
}
