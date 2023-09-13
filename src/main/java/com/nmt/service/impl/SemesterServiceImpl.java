/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Semester;
import com.nmt.repository.SemesterRepository;
import com.nmt.service.SemesterService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesRepo;

    @Override
    public List<Semester> getSemesters(Map<String, String> params) {
       return this.semesRepo.getSemesters(params);
    }

    @Override
    public Semester getSemesterById(String id) {
        return this.semesRepo.getSemesterById(id);
    }

    @Override
    public boolean addSemester(Semester c) {
        return this.semesRepo.addSemester(c);
    }

    @Override
    public boolean updateSemester(Semester c) {
        return this.semesRepo.updateSemester(c);
    }

    @Override
    public boolean deleteSemester(String id) {
        return this.semesRepo.deleteSemester(id);
    }

    @Override
    public int countSemesters() {
        return this.semesRepo.countSemesters();
    }


    @Override
    public List<Semester> getSemesterByLecturerId(String lecturerId) {
        return this.semesRepo.getSemesterByLecturerId(lecturerId);
    }

    @Override
    public List<Semester> getSemesterByStudentId(String studentId) {
        return this.semesRepo.getSemesterByStudentId(studentId);
    }
}
