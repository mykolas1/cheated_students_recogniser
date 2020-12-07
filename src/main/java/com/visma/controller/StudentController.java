package com.visma.controller;

import com.visma.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller to test a game.
 */
@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * End point controller to recognise cheaters.
     */
    @GetMapping(value = "/cheater/recognise")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getCheatedStudents() {
        return new ResponseEntity<>(
                "Cheated students: " + studentService.identifyCheatedStudents().toString(),
                HttpStatus.OK
        );
    }
}
