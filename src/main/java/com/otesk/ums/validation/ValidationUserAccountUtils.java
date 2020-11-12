package com.otesk.ums.validation;

import com.otesk.ums.dto.UserAccountDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Java-class that validates data from the request for {@link com.otesk.ums.controllers.UserAccountController}
 *
 * @author Aliaksei Dvornichenko
 * @version 1.0
 */

public class ValidationUserAccountUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector= Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage,
                (fieldErrorKey1, fieldErrorKey2) -> fieldErrorKey1
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public static boolean failedPasswordConfirmation(UserAccountDTO userAccountDTO){
        return !userAccountDTO.getPassword().equals(userAccountDTO.getConfirmPassword());
    }

    public static boolean failedValidate(UserAccountDTO userAccountDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
            model.addAttribute("userAccount", userAccountDTO);
            return true;
        }
        if (failedPasswordConfirmation(userAccountDTO)) {
            model.addAttribute("passwordError", "Passwords are different!");
            return true;
        }
        return false;
    }
}
