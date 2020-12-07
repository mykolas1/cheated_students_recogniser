package com.visma.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Student class which stores name, sitting location, answers, converted sitting location, flag is cheater and neighbors.
 */
@Getter
public class Student {
    private String name;
    private String sittingLocation;
    private Map<Integer, String> answers = new HashMap<>();
    @Setter private int row, column;
    @Setter private boolean isCheater = false;
    @Setter private List<Student> neighbours = new ArrayList<>();

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setSittingLocation(String sittingLocation) {
        this.sittingLocation = sittingLocation;
        return this;
    }

    public Student setAnswers(Map<Integer, String> answers) {
        this.answers = answers;
        return this;
    }

    @Override
    public String toString() {
        return "\n" + "Student{" +
                "name='" + name + '\'' +
                ", sittingLocation='" + sittingLocation + '\'' +
                '}';
    }
}
