/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Student;
import com.nmt.repository.ScoreRepository;
import com.nmt.repository.StudentRepository;
import com.nmt.service.StudentService;
import dto.ScoreDto;
import dto.StuScoreDto;
import dto.StudentDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private ScoreRepository scoreRepo;

    @Override
    public List<Student> getStudents(Map<String, String> params) {
        return this.studentRepo.getStudents(params);
    }

    @Override
    public boolean addStudent(Student c) {
        return this.studentRepo.addStudent(c);
    }

    @Override
    public boolean updateStudent(Student c) {
        return this.studentRepo.updateStudent(c);
    }

    @Override
    public Student getStudentById(String id) {
        return this.studentRepo.getStudentById(id);
    }

    @Override
    public boolean deleteStudent(String id) {
        return this.studentRepo.deleteStudent(id);
    }

    @Override
    public StudentDto getStudentByUsername(String username) {
        Student u = this.studentRepo.getStudentByUsername(username);
        StudentDto studentDto = new StudentDto();
        studentDto.setId(u.getId());
        studentDto.setName(u.getName());
        studentDto.setBirthday(u.getBirthday());
        studentDto.setGender(u.getGender());
        studentDto.setPhone(u.getPhone());
        studentDto.setAddress(u.getAddress());
        studentDto.setClassesId(u.getClassesId());
        studentDto.setFacultyId(u.getFacultyId());
        studentDto.setMajorId(u.getMajorId());
        
        return studentDto;
    }

    @Override
    public List<StuScoreDto> getListStudent(String lectureId, String subjectId, String semesterId) {
        List<Student> students = this.studentRepo.getListStudent(lectureId, subjectId, semesterId);
        List<StuScoreDto> stuScoreDtos = new ArrayList<>();
        for(int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            StuScoreDto stuScoreDto = new StuScoreDto();
            List<ScoreDto> scoreDtos = this.scoreRepo.getScoreByStudentId(student.getId(), subjectId, semesterId);
        
            stuScoreDto.setStudentId(student.getId());
            stuScoreDto.setStudentName(student.getName());
            stuScoreDto.setStudentBithday(student.getBirthday());
            stuScoreDto.setScoreDto(scoreDtos);       
            stuScoreDtos.add(stuScoreDto);
        }
        
        return stuScoreDtos;
    }

    @Override
    public int countStudents() {
        return this.studentRepo.countStudents();
    }

    @Override
    public List<String> getAllMailOfStudent(String lecturerId, String subjectId, String semesterId) {
        return this.studentRepo.getAllMailOfStudent(lecturerId, subjectId, semesterId);
    }

    @Override
    public List<Student> getStudentByHomeroomTeacher(String lecturerId) {
        return this.studentRepo.getStudentByHomeroomTeacher(lecturerId);
    }
    
}
