/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Student;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class StudentFormatter implements Formatter<Student>{

    @Override
    public String print(Student object, Locale locale) {
        return object.getId();
    }

    @Override
    public Student parse(String studentId, Locale locale) throws ParseException {
        return new Student(studentId);
    }
    
}
