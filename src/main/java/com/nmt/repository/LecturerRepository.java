/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.repository;

import com.nmt.model.Lecturer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface LecturerRepository {
    List<Lecturer> getLecturers(Map<String, String> params);
    boolean addLeturer(Lecturer l);
    boolean updateLeturer(Lecturer l);
    Lecturer getLecturerById(String id);
    int countLecturers();
    boolean deleteLecturer(String id);
    Lecturer getLecturerByUsername(String username);
}
