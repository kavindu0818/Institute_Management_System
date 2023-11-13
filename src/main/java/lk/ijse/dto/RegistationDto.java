package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegistationDto {
    private String reg_id;
    private String name;
    private String regDate;
    private String stu_id;
}
