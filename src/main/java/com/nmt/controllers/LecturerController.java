/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Lecturer;
import com.nmt.service.FacultyService;
import com.nmt.service.LecturerService;
import com.nmt.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author acer
 */
@ControllerAdvice
@Controller
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @GetMapping("/lecturer")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("lecturer", this.lecturerService.getLecturers(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.lecturerService.countLecturers();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "lecturer";
    }

    @GetMapping("/add_lecturer")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_lecturer", new Lecturer());
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        model.addAttribute("user", this.userService.getUsers(params));

        return "add_lecturer";
    }

    @PostMapping(value = "/add_lecturer")
    public String add(@ModelAttribute(value = "add_lecturer") @Valid Lecturer l,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.lecturerService.addLeturer(l) == true) {
                return "redirect:/lecturer";
            }
        }
        return "add_lecturer";
    }

    @GetMapping("/update_lecturer/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_lecturer", this.lecturerService.getLecturerById(id));
        model.addAttribute("faculty", this.facultyService.getFaculties(params));
        model.addAttribute("user", this.userService.getUsers(params));
        return "update_lecturer";
    }

    @PostMapping("/update_lecturer")
    public String update(@ModelAttribute(value = "update_lecturer") Lecturer l,
            BindingResult rs) {

        if (this.lecturerService.updateLeturer(l) == true) {
            return "redirect:/lecturer";
        }

        return "update_lecturer";
    }
}
