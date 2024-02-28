package org.example.dto;


import lombok.Data;

/*In this class named UserDto we mentioned user's fields*/
@Data
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

}
