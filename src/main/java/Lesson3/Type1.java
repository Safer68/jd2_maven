package Lesson3;

import Lesson3.Bean.Skill;
import Lesson3.Bean.Student;
import util.Double;

public class Type1 implements Type {
    @Override
    public String masteringSkill(Skill skill, Student student) {
        double time = skill.getStudyTime() / student.getTalent();
        time = Double.rounding(time,1);
        return "Студент " + student.getName() + " потратил " + time + " часа на освоение навыка:" +
                "\n- " + Double.rounding(time / 3,1) + "часа на разбор;" +
                "\n- " + Double.rounding(time / 3,1) + "часа на практику;" +
                "\n- " + Double.rounding(time / 3,1) + "часа на нахождение в потоке.";
    }
}

