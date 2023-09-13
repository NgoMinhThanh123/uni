/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Faculty;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class FacultyFormatter implements Formatter<Faculty>{

    @Override
    public String print(Faculty fac, Locale locale) {
        return fac.getId();
    }

    @Override
    public Faculty parse(String facultyId, Locale locale) throws ParseException {
        return new Faculty(facultyId);
    }
    
}
