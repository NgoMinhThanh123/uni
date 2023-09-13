/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.repository;

import com.nmt.model.Subject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface SubjectRepository {
    List<Subject> getSubjects(Map<String, String> params);
    int countSubjects();
    boolean addSubject(Subject c);
    boolean updateSubject(Subject c);
    Subject getSubjectById(String id);
    List<Subject> getSubjectByLecturerId(String lecturerId);
    List<Subject> getSubjectByStudentId(String studentId);
    List<Subject> getSubjectByStudentAndSemesterId(String studentId, String semesterId);
    boolean deleteSubject(String id);
}
