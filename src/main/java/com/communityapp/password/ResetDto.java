package com.communityapp.password;

import lombok.Data;

@Data
public class ResetDto {

    //private String emailId = null;
    private String token;
    private String password;

    private String confirmPassword;


}
