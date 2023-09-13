/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author acer
 */
@Getter
@Setter
public class Score_ScoreValueDto {
    private String subjectId;
    private String semesterId;
    private String studentId;
    private double value;
    private int columnId;
}
