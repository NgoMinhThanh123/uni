package com.nmt.controllers;

import com.nmt.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiStudentSubjectController {
    @Autowired
    private StudentSubjectService studentSubjectService;

    @DeleteMapping("/add_student_subject/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.studentSubjectService.deleteStudentSubject(id);
    }
}
