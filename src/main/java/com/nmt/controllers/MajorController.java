/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Major;
import com.nmt.model.User;
import com.nmt.service.FacultyService;
import com.nmt.service.MajorService;
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
public class MajorController {

    @Autowired
    private MajorService majorService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private Environment env;

    @GetMapping("/major")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("major", this.majorService.getMajors(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.majorService.countMajors();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "major";
    }

    @GetMapping("/add_major")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_major", new Major());
        model.addAttribute("faculty", this.facultyService.getFaculties(params));

        return "add_major";
    }

    @GetMapping("/update_major/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_major", this.majorService.getMajorById(id));
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        return "update_major";
    }

    @PostMapping(value = "/add_major")
    public String add(@ModelAttribute(value = "add_major") @Valid Major m,
            BindingResult rs) {
        if(!rs.hasErrors()){
            if (this.majorService.addMajor(m) == true) {
                return "redirect:/major";
            }
        }

        return "add_major";
    }

    @PostMapping("/update_major")
    public String update(@ModelAttribute(value = "update_major") Major m) {

        if (this.majorService.updateMajor(m) == true) {
            return "redirect:/major";
        }

        return "update_major";
    }

}
