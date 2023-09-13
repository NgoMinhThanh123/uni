/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.ScoreValue;
import com.nmt.repository.ScoreValueRepository;
import com.nmt.service.ScoreValueService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class ScoreValueServiceImpl implements ScoreValueService{
    @Autowired
    private ScoreValueRepository scoreValueRepo;

    @Override
    public List<ScoreValue> getScoreValues(Map<String, String> params) {
        return this.scoreValueRepo.getScoreValues(params);
    }

    @Override
    public boolean addOrUpdateScoreValue(ScoreValue u) {
        return this.scoreValueRepo.addOrUpdateScoreValue(u);
    }

    @Override
    public ScoreValue getScoreValueById(int id) {
        return this.scoreValueRepo.getScoreValueById(id);
    }

    @Override
    public boolean deleteScoreValue(int id) {
        return this.scoreValueRepo.deleteScoreValue(id);
    }

    @Override
    public int countScoreValues() {
        return this.scoreValueRepo.countScoreValues();
    }

    @Override
    public ScoreValue addScoreValue(ScoreValue u) {
        return this.scoreValueRepo.addScoreValue(u);
    }

    
}
