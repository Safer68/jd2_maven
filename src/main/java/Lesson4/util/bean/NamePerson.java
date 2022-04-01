package Lesson4.util.bean;

import java.util.Random;

public enum NamePerson {
    ARTEM("Артем"),
    SASHA("Саша"),
    VADIM("Вадим"),
    VITYA("Витя"),
    GENA("Гена"),
    YEGOR("Егор"),
    VLAD("Влад");
    private final String name;

    NamePerson(String name) {
        this.name = name;
    }
    public static String getRandomName(){
        int j = new Random().nextInt(NamePerson.values().length);
        return NamePerson.values()[j].name;
    }
}
