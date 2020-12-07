package com.visma.service;

import com.visma.component.RecogniseCheater;
import com.visma.dto.Student;
import com.visma.helper.CSVReader;
import com.visma.helper.StudentHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Student service implementation.
 */
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final RecogniseCheater recogniseCheater;

    /**
     * Parses all students from an csv file.
     * Converts all student's sitting location into int coordinates.
     * Fetches correct answers.
     * Calculates all student's neighbors.
     * Calls recognise cheater component which recognises if a student is a cheater or not.
     * @return List of students who, according to the rules in "RecogniseCheaterImpl" component, cheated.
     */
    @Override
    public List<Student> identifyCheatedStudents() {
        List<Student> allStudents = CSVReader.parse();
        StudentHelper.convertStudentSittingLocationToIntegerCoordinates(allStudents);
        Map<Integer, String> correctAnswers = StudentHelper.getCorrectAnswers();
        allStudents.forEach(student -> {
            student.setNeighbours(getStudentNeighbors(student, allStudents));
            recogniseCheater.recogniseCheater(student, correctAnswers);
        });
        return allStudents
                .stream()
                .filter(Student::isCheater)
                .collect(Collectors.toList());
    }

    /**
     * Gets students neighbors.
     * @param mainStudent investigating student.
     * @param allStudents all students.
     * @return student's neighbors.
     */
    private List<Student> getStudentNeighbors (Student mainStudent, List<Student> allStudents) {
        List<Student> neighbors = new ArrayList<>();
        allStudents.forEach(student -> {
            if (
                    (student.getRow() == mainStudent.getRow() && student.getColumn() == mainStudent.getColumn() - 1) ||
                    (student.getRow() == mainStudent.getRow() && student.getColumn() == mainStudent.getColumn() + 1) ||
                    (student.getRow() == mainStudent.getRow() -1 && student.getColumn() == mainStudent.getColumn() - 1) ||
                    (student.getRow() == mainStudent.getRow() -1 && student.getColumn() == mainStudent.getColumn()) ||
                    (student.getRow() == mainStudent.getRow() -1 && student.getColumn() == mainStudent.getColumn() + 1)
            ) {
                neighbors.add(student);
            }
        });
        return neighbors;
    }
}
