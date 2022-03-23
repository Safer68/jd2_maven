package Lesson3.Bean;

import lombok.Getter;

@Getter
public class Skill {
    private final String name;
    private final int studyTime;

    public Skill(String name, int studyTime) {
        this.name = name;
        this.studyTime = studyTime;
    }
}
