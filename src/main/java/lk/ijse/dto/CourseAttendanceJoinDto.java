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

public class CourseAttendanceJoinDto {
    private String stuID;
    private String stuName;
    private Date date;
    private String time;
    private String attendnceID;


}
