package com.visma.component;

import com.visma.dto.Student;
import com.visma.helper.StudentHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * RecogniseCheater component recognises students who cheated during test.
 * Cheating rules:
 * 1. If student answers all questions right -> student knows everything -> not a cheater.
 * 2. If student's all answers are equal with neighbor answers -> student is a cheater.
 * 3. If student's more than 10 wrong answers are collected from neighbors -> student is a cheater.
 *
 * It is impossible to recognise which student wrote down an answer. 1st one or the 2nd one.
 */
@RequiredArgsConstructor
@Component
public class RecogniseCheaterImpl implements RecogniseCheater {

    private final StudentHelper studentHelper;

    /**
     * Component entry point to recognise cheater.
     * If all answers are correct returns from method else tries to identify if a student is a cheater.
     * @param student object with answers and neighbors.
     * @param correctAnswers correct answers map.
     */
    @Override
    public void recogniseCheater(Student student, Map<Integer, String> correctAnswers) {
        if (studentHelper.areEqualMaps(student.getAnswers(), correctAnswers)) {
            return;
        }
        checkAnswersEqualityWithNeighborAnswers(student);

        checkIfStudentAnswersAreCollectedFromEachNeighbor(student, correctAnswers);
    }

    /**
     * Iterates through neighbors list and checks are all answers equals.
     * There is no possibility that student's all answers are correct.
     * @param student object with answers and neighbors.
     */
    private void checkAnswersEqualityWithNeighborAnswers (Student student) {
        student.getNeighbours().forEach(neighbour -> {
            if (studentHelper.areEqualMaps(student.getAnswers(), neighbour.getAnswers())) {
                student.setCheater(true);
            }
        });
    }

    /**
     * Method checks if student's all answers are collected from each neighbor.
     * If more then 10 wrong answers are identical like neighbors have -> student cheated.
     * @param student object with answers and neighbors.
     * @param correctAnswers correct answers map.
     */
    private void checkIfStudentAnswersAreCollectedFromEachNeighbor (Student student,  Map<Integer, String> correctAnswers) {

        Map<Integer, Boolean> studentNotCorrectAnswersWithCheatedFlag =
                initiateNotCorrectStudentAnswersWithCheatedFlagFalse(student, correctAnswers);
        recogniseCheatedQuestions(student, studentNotCorrectAnswersWithCheatedFlag);

        if (fetchCheatedQuestions(studentNotCorrectAnswersWithCheatedFlag).size() > 10) {
            student.setCheater(true);
        }
    }

    /**
     * Filters wrong answers to a map of integer(answer id) and boolean (isAnswerCheated) -> all false at this state
     * @param student object with answers and neighbors.
     * @param correctAnswers correct answers map.
     * @return Map<Integer, Boolean> which stored wrong answer id and false state.
     */
    private Map<Integer, Boolean> initiateNotCorrectStudentAnswersWithCheatedFlagFalse (Student student, Map<Integer, String> correctAnswers) {
        Map<Integer, Boolean> studentNotCorrectAnswersWithCheatedFlag = new HashMap<>();
        student.getAnswers().forEach((key, value) -> {
            if (!correctAnswers.get(key).equals(value)) {
                studentNotCorrectAnswersWithCheatedFlag.put(key, false);
            }
        });
        return studentNotCorrectAnswersWithCheatedFlag;
    }

    /**
     * Recognises cheated questions. Is wrong answer appears on at least one of neighbor, it is possibility that
     * this answer was cheated. We set flag to true.
     * @param student object with answers and neighbors.
     * @param studentWrongAnswersWithCheatedFlag Student wrong answers with cheated flag false.
     */
    private void recogniseCheatedQuestions (Student student,  Map<Integer, Boolean> studentWrongAnswersWithCheatedFlag) {
        for (Map.Entry<Integer, String> entry : student.getAnswers().entrySet()) { //recognises cheated questions
            student.getNeighbours().forEach(neighbor -> {
                if (entry.getValue().equals(neighbor.getAnswers().get(entry.getKey()))) {
                    studentWrongAnswersWithCheatedFlag.put(entry.getKey(), true);
                }
            });
        }
    }

    /**
     * Filters only cheated answers.
     * @param studentWrongAnswersWithCheatedFlag map which stores id of answer and flag is answer cheated.
     * @return studentWrongAnswersWithCheatedFlag
     */
    private List<Map.Entry<Integer, Boolean>> fetchCheatedQuestions (Map<Integer, Boolean> studentWrongAnswersWithCheatedFlag) {
        return studentWrongAnswersWithCheatedFlag
                .entrySet()
                .stream()
                .filter(integerBooleanEntry -> integerBooleanEntry.getValue().equals(true))
                .collect(Collectors.toList());
    }
}
