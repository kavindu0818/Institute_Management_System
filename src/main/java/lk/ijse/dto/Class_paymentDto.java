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
public class Class_paymentDto {
    private String pay_id;
    private String class_Id;
    private String stu_Id;
    private String name;
    private String paymentMonth;
    private Date date;
    private String full_Id;
    private Double amount;


}
