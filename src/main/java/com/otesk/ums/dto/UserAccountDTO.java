package com.otesk.ums.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@Getter
@Setter
public class UserAccountDTO {

    @NotBlank(message = "required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", message = "cho")
    @Length(min = 3, max = 16, message = "length")
    private String username;

    @NotBlank(message = "required")
    @Length(min = 3, max = 16, message = "length")
    private String password;

    @NotBlank(message = "required")
    @Length(min = 3, max = 16, message = "length")
    private String confirmPassword;

    @NotBlank(message = "required")
    @Length(min = 1, max = 16, message = "length")
    private String firstName;

    @NotBlank(message = "required")
    @Length(min = 1, max = 16, message = "length")
    private String lastName;

}
