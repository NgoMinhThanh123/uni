/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.ScoreValue;
import com.nmt.model.User;
import com.nmt.service.ScoreColumnSevice;
import com.nmt.service.ScoreService;
import com.nmt.service.ScoreValueService;
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
public class ScoreValueController {

    @Autowired
    private ScoreValueService scoreValueService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ScoreColumnSevice scoreColumnSevice;
    @Autowired
    private Environment env;

    @GetMapping("/score_value")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("score_value", this.scoreValueService.getScoreValues(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.scoreValueService.countScoreValues();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "score_value";
    }

    @GetMapping("/add_score_value")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_score_value", new ScoreValue());
        model.addAttribute("score", this.scoreService.getScores(params));
        model.addAttribute("score_column", this.scoreColumnSevice.getScoreColumns(params));

        return "add_score_value";
    }

    @GetMapping("/add_score_value/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("add_score_value", this.scoreValueService.getScoreValueById(id));
        model.addAttribute("score", this.scoreService.getScores(params));
        model.addAttribute("score_column", this.scoreColumnSevice.getScoreColumns(params));

        return "add_score_value";
    }

    @PostMapping("/add_score_value")
    public String add(@ModelAttribute(value = "add_score_value") @Valid ScoreValue u,
            BindingResult rs) {
        if (this.scoreValueService.addOrUpdateScoreValue(u) == true) {
            return "redirect:/score_value";
        }
        return "add_score_value";
    }
}
