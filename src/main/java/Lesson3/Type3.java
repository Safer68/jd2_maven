package Lesson3;

import Lesson3.Bean.Skill;
import Lesson3.Bean.Student;
import util.Double;

public class Type3 implements Type {

    @Override
    public String masteringSkill(Skill skill, Student student) {
        double time = (skill.getStudyTime() / student.getTalent()) * 3;
        time = Double.rounding(time, 1);
        return "Студент " + student.getName() + " потратил " + time + "часа на практику.";
    }
}
