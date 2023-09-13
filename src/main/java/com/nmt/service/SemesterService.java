/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.Semester;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface SemesterService {
    List<Semester> getSemesters(Map<String, String> params);
    int countSemesters();
    boolean addSemester(Semester m);
    boolean updateSemester(Semester m);
    Semester getSemesterById(String id);
    List<Semester> getSemesterByLecturerId(String lecturerId);
    List<Semester> getSemesterByStudentId(String studentId);
    boolean deleteSemester(String id);
}
