/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.User;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/user")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("user", this.userService.getUsers(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.userService.countUsers();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "user";
    }

    @GetMapping("/add_user")
    public String addList(Model model) {
        model.addAttribute("add_user", new User());

        return "add_user";
    }

    @GetMapping("/add_user/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("add_user", this.userService.getUserById(id));

        return "add_user";
    }

    @PostMapping("/add_user")
    public String add(@ModelAttribute(value = "add_user") @Valid User u,
            BindingResult rs) {
//        if(!rs.hasErrors()){
            if (this.userService.addOrUpdateUser(u) == true) {
                return "redirect:/user";
            }
//        }
        return "add_user";
    }
}
