package com.communityapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Data
public class UserRequest {
    @NotNull
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @NotNull @Length(min = 5, max = 100)
    private String password;

    @NotNull @Length(min = 5, max = 100)
    private String fullName;

    @NotNull @Length(min = 5, max = 100)
    private  String phoneNumber;

    @Transient
    private String confirmPassword;


}
