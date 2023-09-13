/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.repository;
import com.nmt.model.StudentSubject;
import com.nmt.model.User;

import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface StudentSubjectRepository {
    List<StudentSubject> getStudentSubjects(Map<String, String> params);
    StudentSubject getStudentSubjectById(int id);
    boolean addOrUpdateStudentSubject(StudentSubject st);
    boolean deleteStudentSubject(int id);
    StudentSubject getStudentSubjectByStudentAndSubjectId(String studentId, String subjectId);
    int countStudentSubject();
}
