package com.visma.helper;

import com.visma.dto.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Student service helper class.
 */
public class StudentHelper {

    /**
     * Splits student's sitting location from string to int coordinates.
     * @param allStudents
     */
    public static void convertStudentSittingLocationToIntegerCoordinates(List<Student> allStudents) {
        allStudents.forEach(student -> {
            String[] sittingLocation = student.getSittingLocation().split("\\.");
            student.setRow(Integer.parseInt(sittingLocation[0]));
            student.setColumn(Integer.parseInt(sittingLocation[1]));
        });
    }

    /**
     * Creates a map with correct answers.
     * @return map with correct answers.
     */
    public static Map<Integer, String> getCorrectAnswers () {
        Map<Integer, String> correctAnswers = new HashMap<>();
        correctAnswers.put(1, "a");
        correctAnswers.put(2, "bd");
        correctAnswers.put(3, "abef");
        correctAnswers.put(4, "f");
        correctAnswers.put(5, "f");
        correctAnswers.put(6, "d");
        correctAnswers.put(7, "abe");
        correctAnswers.put(8, "abcde");
        correctAnswers.put(9, "abe");
        correctAnswers.put(10, "abd");
        correctAnswers.put(11, "b");
        correctAnswers.put(12, "af");
        correctAnswers.put(13, "ce");
        correctAnswers.put(14, "be");
        correctAnswers.put(15, "bdf");
        correctAnswers.put(16, "a");
        return correctAnswers;
    }

    /**
     * Returns flag are given maps equals.
     * @param firstMap
     * @param secondMap
     * @return flag true/false.
     */
    public static boolean areEqualMaps (Map<Integer, String> firstMap, Map<Integer, String> secondMap) {
        if (firstMap.size() != secondMap.size()) {
            return false;
        }
        return firstMap.entrySet().stream()
                .allMatch(e -> e.getValue().equals(secondMap.get(e.getKey())));
    }
}
