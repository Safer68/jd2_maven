package Lesson4.util.bean;

import java.util.Random;

public enum SurnamePerson {
    KUZNETSOV("Кузнецов"),
    LYSENKO("Лысенко"),
    MALYSHEV("Малышев"),
    BUYNOV("Буйнов"),
    RAYKIN("Райкин"),
    MAYOROV("Майоров"),
    ZAYKOV("Зайков");
    private final String surname;

    SurnamePerson(String surname) {
        this.surname = surname;
    }

    public static String getRandomSurname() {
        int j = new Random().nextInt(NamePerson.values().length);
        return SurnamePerson.values()[j].surname;
    }
}
