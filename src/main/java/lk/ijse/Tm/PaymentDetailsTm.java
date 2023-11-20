package lk.ijse.Tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PaymentDetailsTm {
    private String subject;
    private Double amount;
    private String day;
}
