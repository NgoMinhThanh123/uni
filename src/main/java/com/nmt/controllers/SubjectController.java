/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Subject;
import com.nmt.service.FacultyService;
import com.nmt.service.SubjectService;
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
public class SubjectController {
    @Autowired
    private SubjectService subjectService;   
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private Environment env;
    
    @GetMapping("/subject")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("subject", this.subjectService.getSubjects(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.subjectService.countSubjects();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "subject";
    }
    
    @GetMapping("/add_subject")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_subject", new Subject());
        model.addAttribute("faculty", this.facultyService.getFaculties(params));

        return "add_subject";
    }

    @PostMapping(value = "/add_subject")
    public String add(@ModelAttribute(value = "add_lecturer") @Valid Subject s,
            BindingResult rs) {
        if(!rs.hasErrors()){
            if (this.subjectService.addSubject(s) == true) {
                return "redirect:/subject";
            }
        }

        return "add_subject";
    }
    
    @GetMapping("/update_subject/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_subject", this.subjectService.getSubjectById(id));
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        return "update_subject";
    }
    
    @PostMapping("/update_subject")
    public String update(@ModelAttribute(value = "update_subject") Subject s,
            BindingResult rs) {

            if (this.subjectService.updateSubject(s) == true) {
                return "redirect:/subject";
            }


        return "update_subject";
    }
}
