/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.ScoreColumn;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface ScoreColumnSevice {
    List<ScoreColumn> getScoreColumns(Map<String, String> params);
    int countScoreColumns();
    boolean addOrUpdateScoreColumn(ScoreColumn u);
    boolean addScoreColumn(ScoreColumn u);
    ScoreColumn getScoreColumnById(int id);
    boolean deleteScoreColumn(int id);
}
