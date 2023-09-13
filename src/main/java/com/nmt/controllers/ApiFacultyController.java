/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Faculty;
import com.nmt.service.FacultyService;
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

/**
 *
 * @author acer
 */
@RestController
@RequestMapping("/api")
public class ApiFacultyController {
    @Autowired
    private FacultyService facultyService;
    
    @DeleteMapping("/update_faculty/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.facultyService.deleteFaculty(id);
    }
    
    @GetMapping("/faculties/")
    @CrossOrigin
    public ResponseEntity<List<Faculty>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.facultyService.getFaculties(params), HttpStatus.OK);
    }
  
}
