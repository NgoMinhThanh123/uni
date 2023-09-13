/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.Score;
import dto.ScoreDto;
import dto.ScoreListDto;
import dto.Score_ScoreValueDto;
import dto.StudentScoreDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface ScoreService {
    List<Score> getScores(Map<String, String> params);
    int countScores();
    boolean addOrUpdateScore(Score u);
    Score getScoreById(int id);
    List<ScoreDto> getScoreByStudentId(String studentId, String subjectId, String semesterId);
    List<ScoreListDto> getListScoreStudent(String studentId, String semesterId);
    List<StudentScoreDTO> getStudentScores(String lecturerId, String semesterId, String subjectId);
    List<StudentScoreDTO> getListScoresExport(String subjectId, String semesterId);
    boolean deleteScore(int id);
    Score_ScoreValueDto addScore(Map<String, String> params);
}
