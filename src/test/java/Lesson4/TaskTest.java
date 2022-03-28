package Lesson4;

import Lesson4.Bean.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskTest {
    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;
    List<Person> actual = new ArrayList<>();

    @Before
    public void type1() {
        person1 = new Person("Саша", "Буйнов", 19);
        person2 = new Person("Егор", "Райкин", 20);
        person3 = new Person("Егор", "Райкин", 20);
        person4 = new Person("Саша", "Буйнов", 28);
        actual.add(person1);
        actual.add(person2);
        actual.add(person3);
        actual.add(person4);
    }

    @Test
    public void testFilterPersonAge() {
        int age = 21;
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        expected.add(person2);
        expected.add(person3);
        Task task = new Task(actual);
        task.filterPersonAge(age);
        Assert.assertEquals(expected, task.getList());
    }

    @Test
    public void testSortPerson() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        expected.add(person4);
        expected.add(person2);
        expected.add(person3);
        Task task = new Task(actual);
        task.sortPerson();
        Assert.assertEquals(expected, task.getList());
    }

    @Test
    public void testRemoveDuplicate() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        expected.add(person2);
        expected.add(person4);
        Task task = new Task(actual);
        task.removeDuplicate();
        Assert.assertEquals(expected, task.getList());
    }

}

