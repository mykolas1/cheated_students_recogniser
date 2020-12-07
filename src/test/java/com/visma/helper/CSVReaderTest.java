package com.visma.helper;

import com.visma.dto.Student;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class CSVReaderTest {

    @Test
    public void parse_Test () {
        List<Student> parsedStudents = CSVReader.parse();
        assertEquals(62, parsedStudents.size());
        parsedStudents.forEach(student -> {
            assertEquals(16, student.getAnswers().size());
            assertNotNull(student.getName());
            assertNotEquals("", student.getName());
        });
    }
}
