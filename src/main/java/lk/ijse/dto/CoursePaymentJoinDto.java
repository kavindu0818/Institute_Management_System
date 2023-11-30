package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CoursePaymentJoinDto {
    private String stuID;
    private String stuName;
    private String date;
    private String amount;
}
