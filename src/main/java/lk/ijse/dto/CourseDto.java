package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseDto {
    private String cusId;
    private String cusName;
    private String CusFee;
    private String courseStartDay;
    private String courseDuration;

}
