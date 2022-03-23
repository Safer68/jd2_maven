package Lesson3.Bean;


import Lesson3.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Student {
    private final String name;
    @Setter
    private double talent;
    private final Type type;

    public Student(String name, Type type) {
        this.name = name;
        this.type = type;
        this.talent = Math.floor((Math.random()+0.1) * 10)/10;
    }

    public String timeOnStudy(Skill skill) {
        return type.masteringSkill(skill, this);
    }
}
