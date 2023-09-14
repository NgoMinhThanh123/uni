package com.nmt.controllers;

import com.nmt.model.StudentSubject;
import com.nmt.service.StudentSubjectService;
import dto.StuScoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/get_student_subject/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<StudentSubject> getStudentSubject(
            @RequestParam String studentId,
            @RequestParam String subjectId) {
        StudentSubject studentSubject = studentSubjectService.getStudentSubjectByStudentAndSubjectId(studentId, subjectId);
        if (studentSubject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentSubject, HttpStatus.OK);
    }
}
