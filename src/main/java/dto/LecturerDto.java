/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import com.nmt.model.Faculty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author acer
 */
@Getter
@Setter
public class LecturerDto {
    private String id;
   
    private String name;
   
    private Date birthday;
 
    private short gender;
 
    private String phone;
  
    private String address;

    private Faculty facultyId;

}
