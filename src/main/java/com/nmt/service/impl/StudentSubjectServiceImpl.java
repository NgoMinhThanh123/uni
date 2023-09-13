package com.nmt.service.impl;

import com.nmt.model.StudentSubject;
import com.nmt.repository.StudentSubjectRepository;
import com.nmt.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    @Override
    public List<StudentSubject> getStudentSubjects(Map<String, String> params) {
        return this.studentSubjectRepository.getStudentSubjects(params);
    }

    @Override
    public StudentSubject getStudentSubjectById(int id) {
        return this.studentSubjectRepository.getStudentSubjectById(id);
    }

    @Override
    public boolean addOrUpdateStudentSubject(StudentSubject s) {
        return this.studentSubjectRepository.addOrUpdateStudentSubject(s);
    }

    @Override
    public boolean deleteStudentSubject(int id) {
        return this.studentSubjectRepository.deleteStudentSubject(id);
    }

    @Override
    public StudentSubject getStudentSubjectByStudentAndSubjectId(String studentId, String subjectId) {
        return this.studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(studentId, subjectId);
    }

    @Override
    public int countStudentSubject() {
        return this.studentSubjectRepository.countStudentSubject();
    }
}
