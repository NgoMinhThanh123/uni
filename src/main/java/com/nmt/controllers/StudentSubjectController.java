package com.nmt.controllers;

import com.nmt.model.ScoreValue;
import com.nmt.model.StudentSubject;
import com.nmt.service.StudentService;
import com.nmt.service.StudentSubjectService;
import com.nmt.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class StudentSubjectController {
    @Autowired
    private StudentSubjectService studentSubjectService;
    @Autowired
    private Environment env;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/student_subject")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("student_subject", this.studentSubjectService.getStudentSubjects(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.studentSubjectService.countStudentSubject();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "student_subject";
    }

    @GetMapping("/add_student_subject")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_student_subject", new StudentSubject());
        model.addAttribute("student", this.studentService.getStudents(params));
        model.addAttribute("subject", this.subjectService.getSubjects(params));

        return "add_student_subject";
    }

    @GetMapping("/add_student_subject/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("add_student_subject", this.studentSubjectService.getStudentSubjectById(id));
        model.addAttribute("student", this.studentService.getStudents(params));
        model.addAttribute("subject", this.subjectService.getSubjects(params));

        return "add_student_subject";
    }

    @PostMapping("/add_student_subject")
    public String add(@ModelAttribute(value = "add_student_subject") @Valid StudentSubject u,
                      BindingResult rs) {
        if (this.studentSubjectService.addOrUpdateStudentSubject(u) == true) {
            return "redirect:/student_subject";
        }
        return "add_student_subject";
    }
}
