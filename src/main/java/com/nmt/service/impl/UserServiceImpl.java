/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nmt.controllers.ApiUserController;
import com.nmt.model.User;
import com.nmt.repository.StudentRepository;
import com.nmt.repository.UserRepository;
import com.nmt.service.UserService;
import dto.UserDto;
import exception.UserException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author acer
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return this.userRepo.getUsers(params);
    }

    @Override
    public boolean addOrUpdateUser(User u) {

        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
                u.setPassword(encoder.encode(u.getPassword()));

            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.userRepo.addOrUpdateUser(u);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public boolean deleteUser(int id) {
        return this.userRepo.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid user!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public UserDto getUByUn(String username) {
        User u = this.userRepo.getUserByUsername(username);
        UserDto userDto = new UserDto();
        userDto.setUsername(u.getUsername());
        userDto.setPassword(u.getPassword());
        userDto.setEmail(u.getEmail());
        userDto.setRole(u.getRole());

        return userDto;

    }

    @Override
    public User getUserByUn(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        if(this.getUserByUn(params.get("username")) != null){
            throw new UserException(HttpStatus.BAD_REQUEST, "Tên tài khoản đã tồn tại");
        }
        if(this.getUserByEmail(params.get("email")) != null){
            throw new UserException(HttpStatus.BAD_REQUEST, "Email đã tồn tại");
        }
        if(!this.isValidSchoolEmail(params.get("email"))){
            throw new UserException(HttpStatus.BAD_REQUEST, "Email không đúng định dạng");
        }
        User u = new User();
        u.setEmail(params.get("email"));
        u.setUsername(params.get("username"));
        u.setPassword(this.encoder.encode(params.get("password")));
        u.setRole("ROLE_SINHVIEN");
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.userRepo.addUser(u);
        return u;
    }

    @Override
    public boolean isValidSchoolEmail(String email) {
        return this.userRepo.isValidSchoolEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.getUserByEmail(email);
    }

    @Override
    public int countUsers() {
        return this.userRepo.countUsers();
    }

}
