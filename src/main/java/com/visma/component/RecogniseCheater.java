package com.visma.component;

import com.visma.dto.Student;

import java.util.Map;

public interface RecogniseCheater {
    void recogniseCheater (Student student, Map<Integer, String> correctAnswers);
}
