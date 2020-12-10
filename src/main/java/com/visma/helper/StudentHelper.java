package com.visma.helper;

import com.visma.dto.Student;

import java.util.List;
import java.util.Map;

public interface StudentHelper  {
    void convertStudentSittingLocationToIntegerCoordinates(List<Student> allStudents);
    Map<Integer, String> getCorrectAnswers();
    boolean areEqualMaps (Map<Integer, String> firstMap, Map<Integer, String> secondMap);
}
