/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.ScoreValue;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface ScoreValueService {
    List<ScoreValue> getScoreValues(Map<String, String> params);
    int countScoreValues();
    boolean addOrUpdateScoreValue(ScoreValue u);
    ScoreValue addScoreValue(ScoreValue u);
    ScoreValue getScoreValueById(int id);
    boolean deleteScoreValue(int id);
}
