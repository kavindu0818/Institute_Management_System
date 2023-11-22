package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentfullDetailsDto {

    private String stu_id;
    private String reg_id;
    private String name;
    private String regDate;
    private String Student_gmail;
    private String Student_contactNo;
    private String sub_id;
    private String address;
    private String age;
    private String grade;

    private String Perant_Name;
    private String Perant_Gmail;
    private String Perant_contactNo;
    private byte[] image;


    //public StudentfullDetailsDto(String stuId, String regID, String fullName, String registDate, String stuGmail, String stuContact, String subject, String addresss, String age, String stuGrade, String perantId, String pfullName, String perantGmail, String perantContact, javafx.scene.image.Image image) {
    //}
}

