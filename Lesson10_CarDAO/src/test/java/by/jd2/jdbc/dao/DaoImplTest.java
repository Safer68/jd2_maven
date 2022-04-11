package by.jd2.jdbc.dao;


import by.jd2.jdbc.bean.Car;
import by.jd2.jdbc.util.ConnectionPool;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DaoImplTest {

        ConnectionPool connectionPool = new ConnectionPool();
        DaoImpl<Car> carDAO = new DaoImpl<>(connectionPool);

        @Before
        public void TestData() {
            try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE CAR")) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testTestSave() {
            Car expected = new Car(1, "Lada", "Red", 4500);
            assertNull(carDAO.selectById(1, new Car()));
            carDAO.save(expected);
            Car actual = carDAO.selectById(1, new Car());
            assertEquals(expected, actual);
        }

        @Test
        public void testTestUpdate() {
            Car expected = new Car(2, "Lada", "Red", 4500);
            carDAO.save(expected);
            Car actual1 = carDAO.selectById(2, new Car());
            assertEquals(expected, actual1);
            Car carForUpdate = new Car(2, "Lada", "Green", 4500);
            carDAO.update(carForUpdate);
            Car actual2 = carDAO.selectById(2, new Car());
            assertEquals(carForUpdate, actual2);
        }

        @Test
        public void testTestDelete() {
            Car expected = new Car(3, "Lada", "Red", 4500);
            carDAO.save(expected);
            Car actual = carDAO.selectById(3, new Car());
            assertEquals(expected, actual);
            carDAO.delete(expected);
            Car actualNull = carDAO.selectById(3, new Car());
            assertNull(actualNull);
        }
    }
