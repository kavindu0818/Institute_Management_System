package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentAttendance {
    private String name;
    private Date date;
    private String full_id;
    private String stu_id;
    private String class_id;
    private String time;
}
