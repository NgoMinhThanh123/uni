/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.ScoreColumn;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class ScoreColumnFormatter implements Formatter<ScoreColumn>{

    @Override
    public String print(ScoreColumn s, Locale locale) {
        return String.valueOf(s.getId());
    }

    @Override
    public ScoreColumn parse(String scoreColumnId, Locale locale) throws ParseException {
        return new ScoreColumn(Integer.parseInt(scoreColumnId));
    }
    
}
