package com.visma.helper;

import com.visma.dto.Student;

import java.util.List;

/**
 * Reader class interface.
 */
public interface Reader {
    List<Student> parse();
}
