package com.nmt.service;

import com.nmt.model.StudentSubject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StudentSubjectService{
    List<StudentSubject> getStudentSubjects(Map<String, String> params);
    StudentSubject getStudentSubjectById(int id);
    boolean addOrUpdateStudentSubject(StudentSubject s);
    boolean deleteStudentSubject(int id);
    StudentSubject getStudentSubjectByStudentAndSubjectId(String studentId, String subjectId);
    int countStudentSubject();
}
