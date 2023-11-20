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
public class AttendanceDetailsTm {
   private Date date;
   private String subject;
   private String time;
}
