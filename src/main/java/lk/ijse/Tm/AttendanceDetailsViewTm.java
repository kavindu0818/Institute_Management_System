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
public class AttendanceDetailsViewTm {
   private String StuId;
   private String stu_name;
   private Date date;
   private String time;
   private String amId;
}
