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

public class EmpAttendnaceDto {
    private String attenId;
    private String attenMarkId;
    private String empId;
    private Date date;
    private String time;


}
