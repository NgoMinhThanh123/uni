/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.ScoreValue;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class ScoreValueFormatter implements Formatter<ScoreValue>{

    @Override
    public String print(ScoreValue s, Locale locale) {
        return String.valueOf(s.getId());
    }

    @Override
    public ScoreValue parse(String scoreValueId, Locale locale) throws ParseException {
        return new ScoreValue(Integer.parseInt(scoreValueId));
    }
    
}
