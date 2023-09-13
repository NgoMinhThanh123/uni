/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Student;
import com.nmt.model.Subject;
import com.nmt.request.MailRequest;
import com.nmt.request.RequestMailToStudent;
import com.nmt.service.MailService;
import com.nmt.service.StudentService;
import com.nmt.service.SubjectService;
import com.nmt.service.UserService;
import dto.StuScoreDto;
import dto.StudentDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import utils.HTMLConverter;


import static utils.Constants.YYYY_MM_DD;

@RestController
@RequestMapping("/api")
public class ApiStudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private JavaMailSender mailSender;

    @DeleteMapping("/update_student/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.studentService.deleteStudent(id);
    }

    @GetMapping("/students")
    @CrossOrigin
    public ResponseEntity<List<Student>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.studentService.getStudents(params), HttpStatus.OK);
    }

    @GetMapping("/students/{id}/")
    @CrossOrigin
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") String id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/students-un/{username}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<StudentDto> getStudentByUsename(@PathVariable(value = "username") String username) {
        StudentDto studentDto = studentService.getStudentByUsername(username);
        if (studentDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }


    @GetMapping(path = "/students-lecturer/{lecturerId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Student>> getStudentByHomeroomTeacher(@PathVariable(value = "lecturerId") String lecturerId) {
        List<Student> students = studentService.getStudentByHomeroomTeacher(lecturerId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(path = "/get-list-student/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<StuScoreDto>> getListStudent(
            @RequestParam String lecturerId,
            @RequestParam String subjectId,
            @RequestParam String semesterId) {
        List<StuScoreDto> list = studentService.getListStudent(lecturerId, subjectId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/students/mails/")
    @CrossOrigin
    public ResponseEntity<?> sendMailToStudent(
            @RequestParam String lecturerId,
            @RequestParam String subjectId,
            @RequestParam String semesterId) {
        Subject s = this.subjectService.getSubjectById(subjectId);
        String subject = "Đã có điểm môn học";
        String body = "Đã có điểm môn học " + s.getName() + ", các em vào xem nhé!!!";
        String from = "2051052127thanh@ou.edu.vn";
        List<String> listMail = studentService.getAllMailOfStudent(lecturerId, subjectId, semesterId);
        listMail.stream().forEach(m -> {
            MailRequest mailRequest = MailRequest.builder()
                    .date(LocalDate.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
                    .body(body)
                    .subject(subject)
                    .from(from)
                    .recipients(m)
                    .build();
            mailService.sendMailToStudent(mailRequest);
        });
        return ResponseEntity.ok("Send mail to group successfully!");
    }
    
}
