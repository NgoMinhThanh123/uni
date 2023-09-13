/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.formatters;

import com.nmt.model.Score;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author acer
 */
public class ScoreFormatter implements Formatter<Score>{

    @Override
    public String print(Score s, Locale locale) {
        return String.valueOf(s.getId());
    }

    @Override
    public Score parse(String scoreId, Locale locale) throws ParseException {
        return new Score(Integer.parseInt(scoreId));
    }
    
}
