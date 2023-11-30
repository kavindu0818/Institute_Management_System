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
public class CourseAttendnaceTm {
    private String stuID;
    private String stuName;
    private Date date;
    private String time;
    private String attendnceId;
}
