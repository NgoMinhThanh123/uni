/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author acer
 */
@Getter
@Setter
public class ScoreListDto {
    private String subjectId;
    private String subjectName;
    private int credit;
    private String semesterName;
    private String schoolYear;
    private List<ScoreDto> scoreDto;
}
