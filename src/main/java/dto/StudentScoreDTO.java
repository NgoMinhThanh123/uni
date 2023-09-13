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
public class StudentScoreDTO {
    private String subjectName;
    private String semesterName;
    private String schoolYear;
    private String studentId;
    private String studentName;
    private String scoreColumnName;
    private double scoreValue;
}
