package com.nmt.formatters;

import com.nmt.model.StudentSubject;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class StudentSubjectFormatter implements Formatter<StudentSubject> {
    @Override
    public StudentSubject parse(String studentSubjectId, Locale locale) throws ParseException {
        return new StudentSubject(Integer.parseInt(studentSubjectId));
    }

    @Override
    public String print(StudentSubject studentSubject, Locale locale) {
        return String.valueOf(studentSubject.getId());
    }
}
