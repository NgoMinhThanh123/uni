/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Faculty;
import com.nmt.repository.FacultyRepository;
import com.nmt.service.FacultyService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class FacultyServiceImpl implements FacultyService{
    @Autowired
    private FacultyRepository facultyRepo;

    @Override
    public List<Faculty> getFaculties(Map<String, String> params) {
        return this.facultyRepo.getFaculties(params);
    }

    @Override
    public Faculty getFacultyById(String id) {
        return this.facultyRepo.getFacultyById(id);
    }

    @Override
    public boolean addFaculty(Faculty f) {
        return this.facultyRepo.addFaculty(f);
    }

    @Override
    public boolean updateFaculty(Faculty f) {
        return this.facultyRepo.updateFaculty(f);
    }

    @Override
    public boolean deleteFaculty(String id) {
        return this.facultyRepo.deleteFaculty(id);
    }

    @Override
    public int countFaculties() {
        return this.facultyRepo.countFaculties();
    }
}
