package com.visma.helper;

import com.visma.dto.Student;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class StudentHelperTest {

    @Test
    public void convertStudentSittingLocationToIntegerCoordinates_Test () {
        Student student = new Student();
        student.setSittingLocation("1.1");
        List<Student> students = new ArrayList<>();
        students.add(student);
        StudentHelper.convertStudentSittingLocationToIntegerCoordinates(students);
        assertEquals(1, students.get(0).getRow());
        assertEquals(1, students.get(0).getColumn());
    }

    @Test
    public void getCorrectAnswers_Test () {
        Map<Integer, String> correctAnswers = StudentHelper.getCorrectAnswers();
        assertEquals("a", correctAnswers.get(1));
        assertEquals("bd", correctAnswers.get(2));
        assertEquals("abef", correctAnswers.get(3));
        assertEquals("f", correctAnswers.get(4));
        assertEquals("f", correctAnswers.get(5));
        assertEquals("d", correctAnswers.get(6));
        assertEquals("abe", correctAnswers.get(7));
        assertEquals("abcde", correctAnswers.get(8));
        assertEquals("abe", correctAnswers.get(9));
        assertEquals("abd", correctAnswers.get(10));
        assertEquals("b", correctAnswers.get(11));
        assertEquals("af", correctAnswers.get(12));
        assertEquals("ce", correctAnswers.get(13));
        assertEquals("be", correctAnswers.get(14));
        assertEquals("bdf", correctAnswers.get(15));
        assertEquals("a", correctAnswers.get(16));
    }

    @Test
    public void areEqualMaps_Test () {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "1");
        map1.put(2, "2");
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "1");
        map2.put(2, "2");
        assertTrue(StudentHelper.areEqualMaps(map1, map2));
    }
}
