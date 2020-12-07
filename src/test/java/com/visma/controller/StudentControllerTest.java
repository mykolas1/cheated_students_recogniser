package com.visma.controller;

import com.visma.dto.Student;
import com.visma.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void playPoker_Test () {
        assertEquals("<200 OK OK,Cheated students: [],[]>",
                studentController.getCheatedStudents().toString());
        verify(studentService, times(1)).identifyCheatedStudents();
    }
}
