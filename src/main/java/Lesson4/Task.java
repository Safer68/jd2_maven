package Lesson4;

import Lesson4.Bean.Person;
import Lesson4.service.ServicePerson;
import Lesson4.util.MyFile;
import org.apache.commons.collections4.CollectionUtils;
import Lesson4.util.bean.NamePerson;
import Lesson4.util.bean.SurnamePerson;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Создайте класс Person, с полями name, surname, age. Сгенерируйте группу из 100 человек в возрасте от 15 до 30.
 * 1) выберете объекты, возраст которых меньше 21;
 * 2) распечатать их на экран;
 * 3) сортируем по фамилии, а потом по имени (использовать thenComparing у объекта Comparator);
 * 4) убираем дубли (если name, surname, age одинаковые)
 * 5) сохраняем в файл, как объект, каждый экземпляр класса Person
 * 6) читаем из файла
 * 7) создаем при помощи stream новую коллекцию (List<String>) только из Фамилии и Имени для прочтенных из фалов объектов
 * 8) выводим на экран
 */
public class Task implements ServicePerson {
    private final List<Person> list;

    public Task(List<Person> list) {
        this.list = list;
    }

    public List<Person> getList() {
        return list;
    }

    @Override
    public void generationPerson(int number) {

        for (int i = 0; i < number; i++) {
            list.add(Person.builder()
                    .name(NamePerson.getRandomName())
                    .surname(SurnamePerson.getRandomSurname())
                    .age(new Random().nextInt(16) + 15)
                    .build());
        }
    }

    @Override
    public void filterPersonAge(int age) {
        CollectionUtils.filter(list, person -> person.getAge() < age);
    }

    @Override
    public void sortPerson() {
        list.sort(Comparator.comparing(Person::getSurname).thenComparing(Person::getName));
    }

    @Override
    public void removeDuplicate() {
        Set<Person> hashSet = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(hashSet);
    }

    @Override
    public void printListPerson() {
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {

        int numberPersons = 100;
        int age = 21;
        String fileName = "PersonList";

        Task task = new Task(new ArrayList<>());
        task.generationPerson(numberPersons);
        task.filterPersonAge(age);
        task.printListPerson();
        System.out.println("-----------------");
        task.sortPerson();
        task.removeDuplicate();
        task.printListPerson();
        System.out.println("----------");
        MyFile.fileOutput(fileName, task.getList());
        task.getList().clear();
        List<Person> list = new ArrayList<>();
        MyFile.fileInput(fileName).forEach(o -> list.add((Person) o));
        List<String> list2 = list.stream()
                .map(person -> person.getName() + " " + person.getSurname())
                .collect(Collectors.toList());
        list2.forEach(System.out::println);
    }
}
