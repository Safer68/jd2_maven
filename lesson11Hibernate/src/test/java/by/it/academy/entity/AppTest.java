package by.it.academy.entity;


import by.it.academy.entity.bean.Address;
import by.it.academy.entity.bean.Person;
import by.it.academy.entity.util.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class AppTest {
    private EntityManager entityManager;
    Person person;
    Address address;

    @Before
    public void setUp() {
        person = Person.builder()
                .name("Александр")
                .surname("Ненартович")
                .patronymic("Владимирович")
                .build();
        address = Address.builder()
                .city("Минск")
                .street("Варвашени")
                .house("9").build();
        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
    }

    @Test
    public void shouldPersistPersonAndAddress() {
        entityManager.persist(person);
        entityManager.persist(address);
        assertNotNull(entityManager.find(Person.class, person.getId()));
        assertNotNull(entityManager.find(Address.class, address.getId()));
    }

    @Test
    public void updatePersonAndAddress() {
        entityManager.persist(person);
        entityManager.persist(address);
        String expectedPerson = "Person(id=1, name=Александр, surname=Ненартович, patronymic=Владимирович)";
        String expectedAddress = "Address(id=1, city=Минск, street=Варвашени, house=9)";
        String actualPerson = entityManager.find(Person.class, person.getId()).toString();
        String actualAddress = entityManager.find(Address.class, address.getId()).toString();
        assertEquals(expectedPerson, actualPerson);
        assertEquals(expectedAddress, actualAddress);
        person.setName("Вася");
        address.setCity("Гродно");
        entityManager.persist(person);
        expectedPerson = "Person(id=1, name=Вася, surname=Ненартович, patronymic=Владимирович)";
        expectedAddress = "Address(id=1, city=Гродно, street=Варвашени, house=9)";
        actualPerson = entityManager.find(Person.class, person.getId()).toString();
        actualAddress = entityManager.find(Address.class, address.getId()).toString();
        assertEquals(expectedPerson, actualPerson);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void deleteFromDBPersonAndAddress() {
        entityManager.persist(person);
        entityManager.persist(address);
        assertNotNull(entityManager.find(Person.class, person.getId()));
        assertNotNull(entityManager.find(Address.class, address.getId()));
        entityManager.remove(person);
        entityManager.remove(address);
        assertNull(entityManager.find(Person.class, person.getId()));
        assertNull(entityManager.find(Address.class, address.getId()));
    }

    @After
    public void tearDown() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
}