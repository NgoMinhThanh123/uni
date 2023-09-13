/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Score;
import com.nmt.service.ScoreService;
import dto.ScoreDto;
import dto.ScoreListDto;
import dto.Score_ScoreValueDto;
import dto.StudentScoreDTO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import utils.CSVUtils;

/**
 *
 * @author acer
 */
@RestController
@RequestMapping("/api")
public class ApiScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CSVUtils csvExporter;

    @DeleteMapping("/add_score/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.scoreService.deleteScore(id);
    }

    @GetMapping(path = "/scores/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<StudentScoreDTO>> getScoresById(
            @RequestParam String lecturerId,
            @RequestParam String semesterId,
            @RequestParam String subjectId) {
        List<StudentScoreDTO> list = scoreService.getStudentScores(lecturerId, semesterId, subjectId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping(path = "/scores/student-id/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<ScoreDto>> getScoresByStudentId(
            @RequestParam String studentId,
            @RequestParam String subjectId,
            @RequestParam String semesterId) {
        List<ScoreDto> list = scoreService.getScoreByStudentId(studentId, subjectId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping(path = "/scores/list/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<ScoreListDto>> scoresList(
            @RequestParam String studentId,
            @RequestParam String semesterId) {
        List<ScoreListDto> list = scoreService.getListScoreStudent(studentId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/scores/export-csv/")
    @CrossOrigin
    public void exportCSV(
            @RequestParam String lecturerId,
            @RequestParam String semesterId,
            @RequestParam String subjectId,
            HttpServletResponse response) {
        try {
            List<StudentScoreDTO> list = scoreService.getStudentScores(lecturerId, semesterId, subjectId);

            String csvFileName = "student_scores.csv";
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=" + csvFileName);

            csvExporter.exportToCSV(list, response.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    
    @PostMapping(path="/add-score/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Score_ScoreValueDto> addScore(@RequestParam Map<String, String> params) {
        Score_ScoreValueDto scoreValueDto = this.scoreService.addScore(params);
        if (scoreValueDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(scoreValueDto, HttpStatus.CREATED);
    }
}
