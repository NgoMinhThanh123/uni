/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Student;
import com.nmt.service.ClassesService;
import com.nmt.service.FacultyService;
import com.nmt.service.MajorService;
import com.nmt.service.StudentService;
import com.nmt.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author acer
 */
@Controller
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private Environment env;

    @GetMapping("/student")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("student", this.studentService.getStudents(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.studentService.countStudents();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "student";
    }

    @GetMapping("/add_student")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_student", new Student());
        model.addAttribute("user", this.userService.getUsers(params));
        model.addAttribute("classes", this.classesService.getClasses(params));
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        model.addAttribute("major", this.majorService.getMajors(params));

        return "add_student";
    }

    @GetMapping("/update_student/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_student", this.studentService.getStudentById(id));
        model.addAttribute("user", this.userService.getUsers(params));
        model.addAttribute("classes", this.classesService.getClasses(params));
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        model.addAttribute("major", this.majorService.getMajors(params));
        return "update_student";
    }

    @PostMapping(value = "/add_student")
    public String add(@ModelAttribute(value = "add_student") @Valid Student s,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.studentService.addStudent(s) == true) {
                return "redirect:/student";
            }
        }

        return "add_student";
    }

    @PostMapping("/update_student")
    public String update(@ModelAttribute(value = "update_student") @Valid Student s,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.studentService.updateStudent(s) == true) {
                return "redirect:/student";
            }
        }

        return "update_student";
    }
}
