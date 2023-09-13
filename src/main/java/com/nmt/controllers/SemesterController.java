/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Semester;
import com.nmt.service.SemesterService;
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
public class SemesterController {

    @Autowired
    private SemesterService semesterService;
    @Autowired
    private Environment env;

    @GetMapping("/semester")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("semester", this.semesterService.getSemesters(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.semesterService.countSemesters();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "semester";
    }

    @GetMapping("/add_semester")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_semester", new Semester());

        return "add_semester";
    }

    @GetMapping("/update_semester/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_semester", this.semesterService.getSemesterById(id));
        return "update_semester";
    }

    @PostMapping(value = "/add_semester")
    public String add(@ModelAttribute(value = "add_semester") @Valid Semester m,
            BindingResult rs) {
        if(!rs.hasErrors()){
            if (this.semesterService.addSemester(m) == true) {
                return "redirect:/semester";
            }
        }

        return "add_semester";
    }

    @PostMapping("/update_semester")
    public String update(@ModelAttribute(value = "update_semester") Semester m,
            BindingResult rs) {

        if (this.semesterService.updateSemester(m) == true) {
            return "redirect:/semester";
        }

        return "update_semester";
    }
}
