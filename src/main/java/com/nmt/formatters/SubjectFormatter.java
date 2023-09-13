/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Subject;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class SubjectFormatter implements Formatter<Subject>{

    @Override
    public String print(Subject s, Locale locale) {
        return s.getId();
    }

    @Override
    public Subject parse(String subjectId, Locale locale) throws ParseException {
        return new Subject(subjectId);
    }
    
}
