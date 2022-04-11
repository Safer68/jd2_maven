package by.jd2.jdbc.dao;

import by.jd2.jdbc.annotation.MyTable;
import by.jd2.jdbc.bean.Car;
import by.jd2.jdbc.util.ConnectionPool;
import by.jd2.jdbc.util.ReflectBean;

import java.sql.*;

import static by.jd2.jdbc.dao.Query.*;

public class DaoImpl<T> implements Dao<T> {
    private final ConnectionPool connectionPool;


    public DaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void save(T t) {
        String nameTable = t.getClass().getAnnotation(MyTable.class).value();
        StringBuilder sql = new StringBuilder(INSERT_INTO + nameTable);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("VALUES(");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_FROM + nameTable);
            connection.setAutoCommit(false);
            ResultSetMetaData meta = resultSet.getMetaData();
            int iColumnCount = meta.getColumnCount();

            for (int i = 1; i <= iColumnCount; i++) {
                String s1 = meta.getColumnName(i).toLowerCase();
                values.append("'").append(ReflectBean.getFieldValue(s1, t)).append("'");
                columns.append(s1);
                if (i < iColumnCount) {
                    columns.append(",");
                    values.append(",");
                } else {
                    columns.append(")");
                    values.append(")");
                }
            }
            sql.append(columns).append(values);
            statement.executeUpdate(sql.toString());
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.backConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T selectById(int id, T t) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        T t1 = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_BY_ID + id);
            connection.setAutoCommit(false);
            ResultSetMetaData meta = resultSet.getMetaData();
            int iColumnCount = meta.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= iColumnCount; i++) {
                    String columnName = meta.getColumnName(i).toLowerCase();
                    ReflectBean.setFieldValue(columnName, t, resultSet.getObject(i));
                }
                t1 = t;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.backConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return t1;
    }


    @Override
    public void update(T t) {
        String nameTable = t.getClass().getAnnotation(MyTable.class).value();
        StringBuilder sql = new StringBuilder("UPDATE " + nameTable + " SET ");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_FROM + nameTable);

            connection.setAutoCommit(false);
            ResultSetMetaData meta = resultSet.getMetaData();
            int iColumnCount = meta.getColumnCount();

            for (int i = 2; i <= iColumnCount; i++) {
                String s = meta.getColumnName(i).toLowerCase();
                sql.append(s);
                sql.append(" = ").append("'").append(ReflectBean.getFieldValue(s, t)).append("'");
                if (i < iColumnCount) {
                    sql.append(",");
                }
            }
            sql.append(" WHERE ").append(meta.getColumnName(1).toLowerCase());
            sql.append(" = ").append("'").append(ReflectBean.getFieldValue(meta.getColumnName(1)
                    .toLowerCase(), t)).append("'");
            statement.executeUpdate(sql.toString());
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.backConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(T t) {
        String nameTable = t.getClass().getAnnotation(MyTable.class).value();
        StringBuilder sql = new StringBuilder(DELETE_FROM);
        sql.append(nameTable).append(" WHERE id = '");
        sql.append(ReflectBean.getFieldValue("id", t)).append("'");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connectionPool.backConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        ConnectionPool connectionPool = new ConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE CAR")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaoImpl<Car> carDAO = new DaoImpl<>(connectionPool);

        carDAO.save(new Car(1, "Lada", "Red", 4500));
        Car car = carDAO.selectById(1, new Car());
        System.out.println(car);

        carDAO.update(new Car(1, "Lada", "green", 4500));
        car = carDAO.selectById(1, new Car());
        System.out.println(car);

        carDAO.delete(car);
        car = carDAO.selectById(1, new Car());
        System.out.println(car);
    }

}
