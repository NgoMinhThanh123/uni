
package dto;

import com.nmt.model.Classes;
import com.nmt.model.Faculty;
import com.nmt.model.Major;
import com.nmt.model.Score;
import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String id;
   
    private String name;
   
    private Date birthday;
 
    private short gender;
 
    private String phone;
  
    private String address;

    private Classes classesId;

    private Faculty facultyId;
 
    private Major majorId;
  
}
