/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.Student;
import dto.StuScoreDto;
import dto.StudentDto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface StudentService {
    List<Student> getStudents(Map<String, String> params);
    int countStudents();
    boolean addStudent(Student c);
    boolean updateStudent(Student c);
    Student getStudentById(String id);
    boolean deleteStudent(String id);
    StudentDto getStudentByUsername(String username);
    List<Student> getStudentByHomeroomTeacher(String lecturerId);
    List<StuScoreDto> getListStudent(String lectureId, String subjectId, String semesterId);
    List<String> getAllMailOfStudent(String lecturerId, String subjectId, String semesterId);

}
