package com.visma.service;

import com.visma.component.RecogniseCheater;
import com.visma.dto.Student;
import com.visma.helper.Reader;
import com.visma.helper.StudentHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @InjectMocks private StudentServiceImpl studentService;

    @Mock private RecogniseCheater recogniseCheater;
    @Mock private StudentHelper studentHelper;
    @Mock private Reader csvReader;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void identifyCheatedStudents_Test () {
        when(csvReader.parse()).thenReturn(new ArrayList<>(List.of(new Student(), new Student())));

        studentService.identifyCheatedStudents();

        verify(recogniseCheater, times(2)).recogniseCheater(any(), any());
        verify(studentHelper, times(1)).getCorrectAnswers();
        verify(csvReader, times(1)).parse();
    }
}
