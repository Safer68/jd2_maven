package Lesson3;

import Lesson3.Bean.Skill;
import Lesson3.Bean.Student;
import org.junit.Test;
import util.Double;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void type1() {
        Type type1 = new Type1();
        Student student = new Student("Вася1", type1);
        Skill skill = new Skill("java", 22);
        student.setTalent(1);
        String expected = "Студент " + "Вася1" + " потратил " + 22.0 + " часа на освоение навыка:" +
                "\n- " + Double.rounding(22.0 / 3, 1) + "часа на разбор;" +
                "\n- " + Double.rounding(22.0 / 3, 1) + "часа на практику;" +
                "\n- " + Double.rounding(22.0 / 3, 1) + "часа на нахождение в потоке.";
        String actual = type1.masteringSkill(skill, student);
        assertEquals(expected, actual);
    }

    @Test
    public void type2() {
        Type type2 = new Type2();
        Student student = new Student("Вася1", type2);
        Skill skill = new Skill("java", 22);
        student.setTalent(1);
        String expected = "Студент " + "Вася1" + " потратил " + 44.0 + " часа на освоение навыка:" +
                "\n- " + 22.0 + "часа на практику;" +
                "\n- " + 22.0 + "часа на нахождение в потоке.";
        String actual = type2.masteringSkill(skill, student);
        assertEquals(expected, actual);
    }
    @Test
    public void type3() {
        Type type3 = new Type3();
        Student student = new Student("Вася1", type3);
        Skill skill = new Skill("java", 22);
        student.setTalent(1);
        String expected = "Студент " + "Вася1" + " потратил " + 66.0 + "часа на практику.";

        String actual = type3.masteringSkill(skill, student);
        assertEquals(expected, actual);
    }
}
