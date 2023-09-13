/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.Major;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface MajorService {
    List<Major> getMajors(Map<String, String> params);
    int countMajors();
    boolean addMajor(Major m);
    boolean updateMajor(Major m);
    Major getMajorById(String id);
    boolean deleteMajor(String id);
}
