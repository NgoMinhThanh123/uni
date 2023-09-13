/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.repository;

import com.nmt.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface UserRepository {
    List<User> getUsers(Map<String, String> params);
    int countUsers();
    boolean addOrUpdateUser(User u);
    User getUserById(int id);
    boolean deleteUser(int id);
    User getUserByUsername(String username);
    User getUByUn(String username);
    User getUserByEmail(String email);
    boolean authUser(String username, String password);
    User addUser(User user);
    boolean isValidSchoolEmail(String email);
}
