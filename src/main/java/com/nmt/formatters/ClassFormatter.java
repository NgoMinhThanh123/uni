/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Classes;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class ClassFormatter implements Formatter<Classes>{

    @Override
    public String print(Classes cl, Locale locale) {
        return cl.getId();
    }

    @Override
    public Classes parse(String classId, Locale locale) throws ParseException {
        return new Classes(classId);
    }
    
}
