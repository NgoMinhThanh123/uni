/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Student;
import com.nmt.model.Subject;
import com.nmt.service.SubjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author acer
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiSubjectController {
    @Autowired
    private SubjectService subjectService;
    
    @DeleteMapping("/update_subject/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.subjectService.deleteSubject(id);
    }
    
    @GetMapping("/subjects/")
    public ResponseEntity<List<Subject>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.subjectService.getSubjects(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/subjects/{lecturerId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subject>> getSubjectByLecturerId(@PathVariable(value = "lecturerId") String lecturerId) {
        List<Subject> list = subjectService.getSubjectByLecturerId(lecturerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping(path = "/subjects/studentId/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subject>> getSubjectByStudentId(@RequestParam String studentId) {
        List<Subject> list = subjectService.getSubjectByStudentId(studentId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
     @GetMapping(path = "/subjects/studentId-semesterId/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subject>> getSubjectByStudentAndSemesterId(
            @RequestParam String studentId,
            @RequestParam String semesterId) {
        List<Subject> list = subjectService.getSubjectByStudentAndSemesterId(studentId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
}
