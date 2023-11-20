package lk.ijse.dto;

import lk.ijse.Tm.CourseDetailsTm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class SetPaymentDto {
    private String payId;
    private Double amount;
    private String cusDfull;
    private String stuID;


}
