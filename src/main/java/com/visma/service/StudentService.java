package com.visma.service;

import com.visma.dto.Student;

import java.util.List;

/**
 * Interface of student service.
 */
public interface StudentService {
    List<Student> identifyCheatedStudents();
}
