package by.it.academy;

import by.it.academy.entity.Task;
import by.it.academy.util.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

public class AppTest extends App {
    EntityManager entityManager;

    @Before
    public void setUp() {
        App.extracted();
        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
    }

    @Test
    public void testMain() {
        String expected1 = entityManager.find(Task.class, 1).getClass().getSimpleName();
        String actual1 = "HomeTask";
        assertEquals(expected1, actual1);
        String expected2 = entityManager.find(Task.class, 2).getClass().getSimpleName();
        String actual2 = "Task";
        assertEquals(expected2, actual2);
        String expected3 = entityManager.find(Task.class, 3).getClass().getSimpleName();
        String actual3 = "WorkTask";
        assertEquals(expected3, actual3);
    }

    @After
    public void tearDown() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
        HibernateUtil.close();
    }
}