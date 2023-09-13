/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.ScoreColumn;
import com.nmt.repository.ScoreColumnRepository;
import com.nmt.service.ScoreColumnSevice;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class ScoreColumnSeviceImpl implements ScoreColumnSevice{
    @Autowired
    private ScoreColumnRepository scoreColumnRepo;
    @Override
    public List<ScoreColumn> getScoreColumns(Map<String, String> params) {
        return this.scoreColumnRepo.getScoreColumns(params);
    }

    @Override
    public boolean addOrUpdateScoreColumn(ScoreColumn u) {
        return this.scoreColumnRepo.addOrUpdateScoreColumn(u);
    }

    @Override
    public ScoreColumn getScoreColumnById(int id) {
        return this.scoreColumnRepo.getScoreColumnById(id);
    }

    @Override
    public boolean deleteScoreColumn(int id) {
        return this.scoreColumnRepo.deleteScoreColumn(id);
    }

    @Override
    public int countScoreColumns() {
        return this.scoreColumnRepo.countScoreColumns();
    }

    @Override
    public boolean addScoreColumn(ScoreColumn u) {
        return this.scoreColumnRepo.addScoreColumn(u);
    }
    
}
