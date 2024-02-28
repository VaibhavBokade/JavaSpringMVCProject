package org.example.dto;

import lombok.Data;

/*In this class named LoginDto in which we give attribute as email and password*/
@Data
public class LoginDto {

    private String email;
    private String password;
}
