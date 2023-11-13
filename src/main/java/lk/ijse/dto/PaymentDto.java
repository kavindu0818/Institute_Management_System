package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PaymentDto {

    private String name;
    private String paymentMonth;
    private String date;
    private String full_id;


}
