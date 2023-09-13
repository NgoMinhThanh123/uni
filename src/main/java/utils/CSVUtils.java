/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import dto.StudentScoreDTO;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
    public class CSVUtils {
    public void exportToCSV(List<StudentScoreDTO> studentScores, PrintWriter writer) {
        try {
            // Ghi dòng tiêu đề
            writer.write("Subject Name,Semester Name,School Year,Student ID,Student Name,Score Column Name,Score Value\n");

            // Ghi thông tin từ danh sách studentScores
            for (StudentScoreDTO studentScore : studentScores) {
                writer.write(
                    studentScore.getSubjectName() + "," +
                    studentScore.getSemesterName() + "," +
                    studentScore.getSchoolYear() + "," +
                    studentScore.getStudentId() + "," +
                    studentScore.getStudentName() + "," +
                    studentScore.getScoreColumnName() + "," +
                    studentScore.getScoreValue() + "\n"
                );
            }

            System.out.println("Export to CSV response successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
