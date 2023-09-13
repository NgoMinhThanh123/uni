/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.repository;

import com.nmt.model.Faculty;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface FacultyRepository {
    List<Faculty> getFaculties(Map<String, String> params);
    int countFaculties();
    boolean addFaculty(Faculty f);
    boolean updateFaculty(Faculty f);
    Faculty getFacultyById(String id);
    boolean deleteFaculty(String id);
}
