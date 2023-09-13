/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Score;
import com.nmt.service.*;

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
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private StudentSubjectService studentSubjectService;
    @Autowired
    private Environment env;

    @GetMapping("/score")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("score", this.scoreService.getScores(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.scoreService.countScores();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "score";
    }

    @GetMapping("/add_score")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_score", new Score());
        model.addAttribute("semester", this.semesterService.getSemesters(params));
        model.addAttribute("student_subject", this.studentSubjectService.getStudentSubjects(params));
        return "add_score";
    }

    @GetMapping("/add_score/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("add_score", this.scoreService.getScoreById(id));
        model.addAttribute("semester", this.semesterService.getSemesters(params));
        model.addAttribute("student_subject", this.studentSubjectService.getStudentSubjects(params));

        return "add_score";
    }

    @PostMapping("/add_score")
    public String add(@ModelAttribute(value = "add_score") @Valid Score u,
            BindingResult rs) {
//        if (!rs.hasErrors()) {
            if (this.scoreService.addOrUpdateScore(u) == true) {
                return "redirect:/score";
            }
//        }
        return "add_score";
    }
}
