package Lesson4.util;

import Lesson4.Bean.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyFile {


    public static void fileOutput(String fileName, List<Person> o) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            for (Person o1:o) {
                objectOutputStream.writeObject(o1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> fileInput(String fileName) {
        List<Object> list = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                try {
                    list.add(objectInputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
