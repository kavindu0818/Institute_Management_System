package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDto {

    private String emp_id;
    private String name;
    private String gmail;
    private String contactNo;
    private String nic;
    private String address;
    private String position;
    private String date;
    private String bankAccountNum;

    private String bankBranchName;
    private Integer age;
    private String gendar;
    private byte[] image;
    private String empAttendanceID;
}
