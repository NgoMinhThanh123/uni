package com.nmt.controllers;

import com.nmt.model.Major;
import com.nmt.model.Semester;
import com.nmt.service.MajorService;
import com.nmt.service.SemesterService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiSemesterController {
    @Autowired
    private SemesterService semesterService;

    @DeleteMapping("/update_semester/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.semesterService.deleteSemester(id);
    }
    
    @GetMapping("/semesters/")
    @CrossOrigin
    public ResponseEntity<List<Semester>> list( @RequestParam String lecturerId) {
        List<Semester> list = this.semesterService.getSemesterByLecturerId(lecturerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/semesters/student/")
    @CrossOrigin
    public ResponseEntity<List<Semester>> getListSemesterByStudentId( @RequestParam String studentId) {
        List<Semester> list = this.semesterService.getSemesterByStudentId(studentId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
