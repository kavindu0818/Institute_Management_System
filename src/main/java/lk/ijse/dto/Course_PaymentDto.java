package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Course_PaymentDto {
    private String cusPayId;
    private double payment;
    private String Date;
    private String time;
    private String cusDfull;
}
