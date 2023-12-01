package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class UserDto {

    private String userID;
    private String password;
    private String userName;
    private byte[] image;
}
