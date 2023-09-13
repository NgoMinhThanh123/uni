/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Score;
import com.nmt.model.ScoreColumn;
import com.nmt.model.ScoreValue;
import com.nmt.model.Semester;
import com.nmt.model.Student;
import com.nmt.model.StudentSubject;
import com.nmt.model.Subject;
import com.nmt.repository.ScoreColumnRepository;
import com.nmt.repository.ScoreRepository;
import com.nmt.repository.ScoreValueRepository;
import com.nmt.repository.SemesterRepository;
import com.nmt.repository.StudentRepository;
import com.nmt.repository.StudentSubjectRepository;
import com.nmt.repository.SubjectRepository;
import com.nmt.service.ScoreService;
import dto.ScoreDto;
import dto.ScoreListDto;
import dto.Score_ScoreValueDto;
import dto.StudentScoreDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepo;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studetnRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private ScoreColumnRepository scoreColumnRepository;
    @Autowired
    private ScoreValueRepository scoreValueRepository;
     @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    @Override
    public List<Score> getScores(Map<String, String> params) {
        return this.scoreRepo.getScores(params);
    }

    @Override
    public boolean addOrUpdateScore(Score u) {
        return this.scoreRepo.addOrUpdateScore(u);
    }

    @Override
    public Score getScoreById(int id) {
        return this.scoreRepo.getScoreById(id);
    }

    @Override
    public boolean deleteScore(int id) {
        return this.scoreRepo.deleteScore(id);
    }

    @Override
    public int countScores() {
        return this.scoreRepo.countScores();
    }

    @Override
    public List<StudentScoreDTO> getStudentScores(String lecturerId, String semesterId, String subjectId) {
        return this.scoreRepo.getStudentScores(lecturerId, semesterId, subjectId);
    }

    @Override
    public List<StudentScoreDTO> getListScoresExport(String subjectId, String semesterId) {
        return this.scoreRepo.getListScoresExport(semesterId, subjectId);
    }

    @Override
    public List<ScoreDto> getScoreByStudentId(String studentId, String subjectId, String semesterId) {
        return this.scoreRepo.getScoreByStudentId(studentId, subjectId, semesterId);
    }

    @Override
    public List<ScoreListDto> getListScoreStudent(String studentId, String semesterId) {
        List<ScoreListDto> scoreListDtos = new ArrayList<>();
        List<Subject> subjects = this.subjectRepository.getSubjectByStudentAndSemesterId(studentId, semesterId);
        Semester semesters = this.semesterRepository.getSemesterById(semesterId);
        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            List<ScoreDto> scoreDtos = this.scoreRepo.getScoreByStudentId(studentId, subject.getId(), semesterId);
            ScoreListDto scoreListDto = new ScoreListDto();
            scoreListDto.setScoreDto(scoreDtos);
            scoreListDto.setSubjectId(subject.getId());
            scoreListDto.setSubjectName(subject.getName());
            scoreListDto.setCredit(subject.getCredit());
            scoreListDto.setSemesterName(semesters.getName());
            scoreListDto.setSchoolYear(String.valueOf(semesters.getSchoolYear()));
            scoreListDtos.add(scoreListDto);
        }

        return scoreListDtos;
    }

    @Override
    public Score_ScoreValueDto addScore(Map<String, String> params) {
        Subject subject = this.subjectRepository.getSubjectById(params.get("subjectId"));
        Semester semester = this.semesterRepository.getSemesterById(params.get("semesterId"));
        Student student = this.studetnRepository.getStudentById(params.get("studentId"));
        ScoreColumn scoreColumn = this.scoreColumnRepository.getScoreColumnById(Integer.parseInt(params.get("scoreColumnId")));
        StudentSubject studentSubject = this.studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(params.get("studentId"), params.get("subjectId"));
        
        Score score = new Score();
        score.setStudentSubjectId(studentSubject);
        score.setSemesterId(semester);

        Score score1 = this.scoreRepo.addScore(score);

        ScoreValue scoreValue = new ScoreValue();
        String scoreValueStr = params.get("scoreValue");
        if (scoreValueStr != null) {
            scoreValue.setValue(Double.parseDouble(scoreValueStr));
        } else {
            scoreValue.setValue(0.0);
        }
        scoreValue.setScoreColumnId(scoreColumn);
        scoreValue.setScoreId(score1);

        ScoreValue scoreValue1 = this.scoreValueRepository.addScoreValue(scoreValue);

        Score_ScoreValueDto scoreValueDto = new Score_ScoreValueDto();

        scoreValueDto.setSubjectId(score1.getStudentSubjectId().getSubjectId().toString());
        scoreValueDto.setSemesterId(score1.getSemesterId().toString());
        scoreValueDto.setStudentId(score1.getStudentSubjectId().getStudentId().toString());
        scoreValueDto.setColumnId(scoreValue1.getScoreColumnId().getId());
        scoreValueDto.setValue(Double.parseDouble(String.valueOf(scoreValue1.getValue())));

        return scoreValueDto;
    }

}
