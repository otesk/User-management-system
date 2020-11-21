package com.otesk.ums.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Java-class used in editing and registration an {@link com.otesk.ums.domain.UserAccount} to transfer data between layers.
 *
 * @version 1.0
 * @author Aleksey Dvornichenko
 */

@Getter
@Setter
public class UserAccountDTO {

    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only latin letters.")
    @Length(min = 3, max = 16, message = "Username must be between 3 and 16 characters.")
    private String username;

    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$",
             message = "Use only latin letters (1 minimum) and arabic numerals (1 minimum).")
    @Length(min = 3, max = 16, message = "Password must be between 3 and 16 characters.")
    private String password;

    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$",
             message = "Use only latin letters (1 minimum) and arabic numerals (1 minimum).")
    @Length(min = 3, max = 16, message = "Password must be between 3 and 16 characters.")
    private String confirmPassword;

    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only latin letters.")
    @Length(min = 1, max = 16, message = "First name must be between 1 and 16 characters.")
    private String firstName;

    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only latin letters.")
    @Length(min = 1, max = 16, message = "Last name must be between 1 and 16 characters.")
    private String lastName;
}
