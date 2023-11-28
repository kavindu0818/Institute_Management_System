package lk.ijse.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttendanceJoinDto {
    private String attendanceID;
    private Date date;
    private String time;
    private String cusID;
    private String stuName;
    private String stuID;




}
