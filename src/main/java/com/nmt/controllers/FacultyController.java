/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Faculty;
import com.nmt.service.FacultyService;
import java.util.List;
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
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private Environment env;

    @GetMapping("/faculty")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.facultyService.countFaculties();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "faculty";
    }

    @GetMapping("/add_faculty")
    public String addList(Model model) {
        model.addAttribute("add_faculty", new Faculty());

        return "add_faculty";
    }

    @PostMapping(value = "/add_faculty")
    public String add(@ModelAttribute(value = "add_faculty") @Valid Faculty f,
            BindingResult rs) {
        if(!rs.hasErrors()){
            if (this.facultyService.addFaculty(f) == true) {
                return "redirect:/faculty";
            }
        }

        return "add_faculty";
    }

    @GetMapping("/update_faculty/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_faculty", this.facultyService.getFacultyById(id));

        return "update_faculty";
    }

    @PostMapping("/update_faculty")
    public String update(@ModelAttribute(value = "update_faculty") Faculty f,
            BindingResult rs) {

        if (this.facultyService.updateFaculty(f) == true) {
            return "redirect:/faculty";
        }

        return "update_faculty";
    }

}
