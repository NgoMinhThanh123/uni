/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author acer
 */
@Getter
@Setter
public class StuScoreDto {
    private String studentId;
    private String studentName;
    private Date studentBithday;
    private List<ScoreDto> scoreDto;
}
