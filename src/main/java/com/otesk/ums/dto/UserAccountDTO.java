package com.otesk.ums.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Java-class for transfer data of {@link com.otesk.ums.domain.UserAccount} between layers.
 */

@Getter
@Setter
public class UserAccountDTO {

    /**
     * Stores user account username.
     */
    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only latin letters.")
    @Length(min = 3, max = 16, message = "Username must be between 3 and 16 characters.")
    private String username;

    /**
     * Stores user account password.
     */
    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$",
             message = "Use only latin letters (1 minimum) and arabic numerals (1 minimum).")
    @Length(min = 3, max = 16, message = "Password must be between 3 and 16 characters.")
    private String password;

    /**
     * Stores user account confirm password.
     */
    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$",
             message = "Use only latin letters (1 minimum) and arabic numerals (1 minimum).")
    @Length(min = 3, max = 16, message = "Password must be between 3 and 16 characters.")
    private String confirmPassword;

    /**
     * Stores user account first name.
     */
    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only latin letters.")
    @Length(min = 1, max = 16, message = "First name must be between 1 and 16 characters.")
    private String firstName;

    /**
     * Stores user account last name.
     */
    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only latin letters.")
    @Length(min = 1, max = 16, message = "Last name must be between 1 and 16 characters.")
    private String lastName;
}
