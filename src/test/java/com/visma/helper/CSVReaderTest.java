package com.visma.helper;

import com.visma.dto.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class CSVReaderTest {

    @InjectMocks
    private CSVReader csvReader;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void parse_Test () {
        List<Student> parsedStudents = csvReader.parse();
        assertEquals(62, parsedStudents.size());
        parsedStudents.forEach(student -> {
            assertEquals(16, student.getAnswers().size());
            assertNotNull(student.getName());
            assertNotEquals("", student.getName());
        });
    }
}
