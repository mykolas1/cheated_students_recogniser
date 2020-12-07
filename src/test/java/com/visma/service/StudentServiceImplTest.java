package com.visma.service;

import com.visma.component.RecogniseCheater;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @InjectMocks private StudentServiceImpl studentService;

    @Mock private RecogniseCheater recogniseCheater;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void identifyCheatedStudents_Test () {
        studentService.identifyCheatedStudents();
        verify(recogniseCheater, times(62)).recogniseCheater(any(), any());
    }
}
