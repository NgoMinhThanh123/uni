/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Subject;
import com.nmt.repository.SubjectRepository;
import com.nmt.service.SubjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepo;

    @Override
    public List<Subject> getSubjects(Map<String, String> params) {
        return this.subjectRepo.getSubjects(params);
    }

    @Override
    public boolean addSubject(Subject c) {
        return this.subjectRepo.addSubject(c);
    }

    @Override
    public boolean updateSubject(Subject c) {
        return this.subjectRepo.updateSubject(c);
    }

    @Override
    public Subject getSubjectById(String id) {
        return this.subjectRepo.getSubjectById(id);
    }

    @Override
    public boolean deleteSubject(String id) {
        return this.subjectRepo.deleteSubject(id);
    }

    @Override
    public int countSubjects() {
        return this.subjectRepo.countSubjects();
    }

    @Override
    public List<Subject> getSubjectByLecturerId(String lecturerId) {
        return this.subjectRepo.getSubjectByLecturerId(lecturerId);
    }

    @Override
    public List<Subject> getSubjectByStudentId(String studentId) {
        return this.subjectRepo.getSubjectByStudentId(studentId);
    }

    @Override
    public List<Subject> getSubjectByStudentAndSemesterId(String studentId, String semesterId) {
        return this.subjectRepo.getSubjectByStudentAndSemesterId(studentId, semesterId);
    }

}
