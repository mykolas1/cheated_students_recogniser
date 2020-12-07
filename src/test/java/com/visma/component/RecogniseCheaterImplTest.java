package com.visma.component;

import com.visma.dto.Student;
import com.visma.helper.StudentHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class RecogniseCheaterImplTest {

    @InjectMocks
    private RecogniseCheaterImpl recogniseCheater;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void recogniseCheater_AllAnswersCorrect() {

        Student student = new Student();
        Map<Integer, String> correctAnswers = StudentHelper.getCorrectAnswers();
        student.setAnswers(correctAnswers);
        student.setCheater(false);
        recogniseCheater.recogniseCheater(student, correctAnswers);
        assertFalse(student.isCheater());
    }

    @Test
    public void recogniseCheater_EqualAnswersBetweenNeighbors_Test() {
        Student neighbor = new Student();
        Student student = new Student();
        setAnswers(student);
        setAnswers(neighbor);
        student.getNeighbours().add(neighbor);


        Map<Integer, String> correctAnswers = getCorrectAnswers();

        student.setCheater(false);
        recogniseCheater.recogniseCheater(student, correctAnswers);
        assertTrue(student.isCheater());
    }

    @Test
    public void recogniseCheater_CheckIfStudentAnswersAreCollectedFromEachNeighbor_Test() {
        Student neighbor1 = new Student();
        neighbor1.setAnswers(getBadAnswers_neighbor1());
        Student neighbor2 = new Student();
        neighbor2.setAnswers(getBadAnswers_neighbor2());
        Student student = new Student();
        student.setAnswers(getBadAnswers());
        List<Student> neighbors = new ArrayList<>(List.of(neighbor1, neighbor2));
        student.setNeighbours(neighbors);

        Map<Integer, String> correctAnswers = getCorrectAnswers();

        student.setCheater(false);
        recogniseCheater.recogniseCheater(student, correctAnswers);
        assertTrue(student.isCheater());
    }



    private void setAnswers (Student student) {
        Map<Integer, String> answers = StudentHelper.getCorrectAnswers();
        answers.put(1, "1");
        answers.put(2, "2");
        answers.put(3, "3");
        answers.put(4, "4");
        student.setAnswers(answers);
    }

    private Map<Integer, String> getCorrectAnswers () {
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

    private Map<Integer, String> getBadAnswers () {
        Map<Integer, String> correctAnswers = new HashMap<>();
        correctAnswers.put(1, "1");
        correctAnswers.put(2, "2");
        correctAnswers.put(3, "3");
        correctAnswers.put(4, "4");
        correctAnswers.put(5, "5");
        correctAnswers.put(6, "6");
        correctAnswers.put(7, "7");
        correctAnswers.put(8, "8");
        correctAnswers.put(9, "9");
        correctAnswers.put(10, "10");
        correctAnswers.put(11, "11");
        correctAnswers.put(12, "12");
        correctAnswers.put(13, "13");
        correctAnswers.put(14, "14");
        correctAnswers.put(15, "15");
        correctAnswers.put(16, "16");
        return correctAnswers;
    }

    private Map<Integer, String> getBadAnswers_neighbor1 () {
        Map<Integer, String> correctAnswers = new HashMap<>();
        correctAnswers.put(1, "1");
        correctAnswers.put(2, "a");
        correctAnswers.put(3, "3");
        correctAnswers.put(4, "a");
        correctAnswers.put(5, "5");
        correctAnswers.put(6, "a");
        correctAnswers.put(7, "7");
        correctAnswers.put(8, "a");
        correctAnswers.put(9, "9");
        correctAnswers.put(10, "a");
        correctAnswers.put(11, "11");
        correctAnswers.put(12, "a");
        correctAnswers.put(13, "13");
        correctAnswers.put(14, "a");
        correctAnswers.put(15, "15");
        correctAnswers.put(16, "a");
        return correctAnswers;
    }

    private Map<Integer, String> getBadAnswers_neighbor2 () {
        Map<Integer, String> correctAnswers = new HashMap<>();
        correctAnswers.put(1, "a");
        correctAnswers.put(2, "2");
        correctAnswers.put(3, "a");
        correctAnswers.put(4, "4");
        correctAnswers.put(5, "a");
        correctAnswers.put(6, "6");
        correctAnswers.put(7, "a");
        correctAnswers.put(8, "8");
        correctAnswers.put(9, "a");
        correctAnswers.put(10, "10");
        correctAnswers.put(11, "a");
        correctAnswers.put(12, "12");
        correctAnswers.put(13, "a");
        correctAnswers.put(14, "14");
        correctAnswers.put(15, "a");
        correctAnswers.put(16, "16");
        return correctAnswers;
    }
}
