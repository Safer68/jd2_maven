package Lesson3;

import Lesson3.Bean.Skill;
import Lesson3.Bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Есть три типа студентов:
 * 1 тип - делают разборы, практическую часть и находятся в потоке
 * 2 тип - делают только практическую часть и разборы
 * 3 тип - делают только практическую часть
 * <p>
 * У студентов есть талант: величина от 0,1 до 1. Талант влияет на освоение навыка(скила).
 * Например если для освоения навыка вождения нужно потратить 22 часа, то
 * студент с талантом 1 затратит 22 часа
 * студент с талантом 0,1 затратит 22/0,1 = 220 часов
 * студент с талантом 0,5 затратит 22/0,5 = 44 часа
 * студент с талантом 0,8 затратит 22/0,8 = 27,5 часов
 * <p>
 * Считается что 1 тип студентов освайвают материал за то время, которое нужно потратить на освоение.
 * 2 тип студентов освайвают материал в два раза дольше.
 * 3 тип студентов освайвают материал в три раза дольше.
 * Например если для освоения навыка вождения нужно потратить 22 часа, то
 * 1 тип студента потратит 22 часа (22/3 = 7,3; 7,3 часа на разборы, 7,3 часа на практику,
 * 7,3 часа на находение в потоке)
 * 2 тип студента потрати 22 x 2 = 44 часа (44/2 = 22; 22 часа на практику, 22 часа на разборы)
 * 3 тип студента потратит 22 x 3 = 66 часов (66 часов на практику)
 * <p>
 * Задача:
 * Студенту 1 типа с талантом 1 нужно потратить на разбор для преобретения
 * практического навыка по java core 66 часов (Всего на практический навык по java core 198 часов).
 * Необходимо посчитать для каждого студента затраченное время на изучение
 * java в классе(вывести на экран тип студента, его талант, время необходимое для разбора, практики,
 * нахождения в потоке и суммарное время на освоение навыка).
 * В классе 9 студентов (по 3 студента каждого типа).
 * Талант задается рандомно.
 * Использовать патерн стратегия.
 */
public class Task {
    private final List<Student> studentList;
    private final Skill skill;


    public Task(Skill skill) {
        this.studentList = new ArrayList<>();
        this.skill = skill;
    }

    public void addStudentList(Student student) {
       studentList.add(student);
    }

    public void printStudents() {

        for (Student student : studentList) {
            System.out.println(student.timeOnStudy(skill));
        }

    }

    public static void main(String[] args) {

        Task task = new Task(new Skill("java", 22));
        Type type1 = new Type1();
        Type type2 = new Type2();
        Type type3 = new Type3();

        task.addStudentList(new Student("Вася1", type1));
        task.addStudentList(new Student("Петя1", type1));
        task.addStudentList(new Student("Матвей1", type1));

        task.addStudentList(new Student("Вася2", type2));
        task.addStudentList(new Student("Петя2", type2));
        task.addStudentList(new Student("Матвей2", type2));

        task.addStudentList(new Student("Вася3", type3));
        task.addStudentList(new Student("Петя3", type3));
        task.addStudentList(new Student("Матвей3", type3));

        task.printStudents();

    }
}
