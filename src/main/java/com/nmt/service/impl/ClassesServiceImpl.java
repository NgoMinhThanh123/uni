/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Classes;
import com.nmt.repository.ClassesRepository;
import com.nmt.service.ClassesService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class ClassesServiceImpl implements ClassesService{
    @Autowired
    private ClassesRepository classesRepo;

    @Override
    public List<Classes> getClasses(Map<String, String> params) {
        return this.classesRepo.getClasses(params);
    }

    @Override
    public boolean addClass(Classes c) {
        return this.classesRepo.addClass(c);
    }

    @Override
    public boolean updateClass(Classes c) {
        return this.classesRepo.updateClass(c);
    }
    
    @Override
    public Classes getClassById(String id) {
        return this.classesRepo.getClassById(id);
    }

    @Override
    public boolean deleteClass(String id) {
        return this.classesRepo.deleteClass(id);
    }

    @Override
    public int countClasses() {
        return this.classesRepo.countClasses();
    }
}
