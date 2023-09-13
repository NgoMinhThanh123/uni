/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Lecturer;
import com.nmt.service.LecturerService;
import dto.LecturerDto;
import dto.StudentDto;
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
@RequestMapping("/api")
public class ApiLecturerController {

    @Autowired
    private LecturerService lecturerService;

    @DeleteMapping("/update_lecturer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.lecturerService.deleteLecturer(id);
    }

    @GetMapping("/lecturers/")
    @CrossOrigin
    public ResponseEntity<List<Lecturer>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.lecturerService.getLecturers(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/lecturers-un/{username}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<LecturerDto> getStudentByUsename(@PathVariable(value = "username") String username) {    
        LecturerDto lecturerDto = lecturerService.getLecturerByUsername(username);
        if (lecturerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lecturerDto, HttpStatus.OK);
    }
}
