package lk.ijse.Tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttendanceTm {
    private String regId;
    private String stuId;
    private String stuName;
    private Date date;
    private String time;
    private String classId;
}
