/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Semester;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class SemeterFormatter implements Formatter<Semester>{

    @Override
    public String print(Semester s, Locale locale) {
        return s.getId();
    }

    @Override
    public Semester parse(String semesterId, Locale locale) throws ParseException {
        return new Semester(semesterId);
    }
    
}
