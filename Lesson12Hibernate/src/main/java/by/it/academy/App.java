package by.it.academy;

import by.it.academy.embed.Person;
import by.it.academy.entity.HomeTask;
import by.it.academy.entity.Task;
import by.it.academy.entity.WorkTask;
import by.it.academy.util.HibernateUtil;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        extracted();
        HibernateUtil.close();
    }

    public static void extracted() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Person executorPerson = Person.builder().name("Вася").surname("Пупкин").build();
        Person lookingPerson = Person.builder().name("Петя").surname("Иванов").build();
        HomeTask homeTask = HomeTask.builder()
                .name("Hibernate")
                .description("homeTask")
                .startDate("17.04.2022")
                .executor(executorPerson)
                .looking(lookingPerson)
                .build();
        Task task = Task.builder()
                .name("Hibernate")
                .description("task")
                .build();
        WorkTask workTask = WorkTask.builder()
                .name("Hibernate")
                .description("workTask")
                .cost(2200.0).build();
        entityManager.getTransaction().begin();
        entityManager.persist(homeTask);
        entityManager.persist(task);
        entityManager.persist(workTask);
        entityManager.getTransaction().commit();
    }

}
