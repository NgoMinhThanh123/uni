/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Major;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class MajorFormatter implements Formatter<Major>{

    @Override
    public String print(Major m, Locale locale) {
        return m.getId();
    }

    @Override
    public Major parse(String majorId, Locale locale) throws ParseException {
        return new Major(majorId);
    }
    
}
