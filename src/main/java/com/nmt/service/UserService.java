/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.User;
import java.util.List;
import java.util.Map;

import dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author acer
 */
public interface UserService extends UserDetailsService{
    List<User> getUsers(Map<String, String> params);
    int countUsers();   
    boolean addOrUpdateUser(User u);
    User getUserById(int id);
    boolean deleteUser(int id);
    boolean authUser(String username, String password);
    UserDto getUByUn(String username);
    User getUserByUn(String username);
    User getUserByEmail(String email);
    User addUser(Map<String, String> params, MultipartFile avatar);
    boolean isValidSchoolEmail(String email);
}
