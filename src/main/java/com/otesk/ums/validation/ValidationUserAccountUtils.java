package com.otesk.ums.validation;

import com.otesk.ums.dto.UserAccountDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Java-class that validates data from the request for {@link com.otesk.ums.controllers.UserAccountController}.
 */
public class ValidationUserAccountUtils {
    /**
     * Converts data from binding result to map.
     *
     * @param bindingResult for storing validation errors
     * @return map as a key storing the name of the error, as a value the corresponding message
     */
    private static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage,
                (fieldErrorKey1, fieldErrorKey2) -> fieldErrorKey1
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    /**
     * Compare password and confirm password.
     *
     * @param userAccountDTO stores data for validation
     * @return true if passwords are the same
     */
    private static boolean failedPasswordConfirmation(UserAccountDTO userAccountDTO) {
        return !userAccountDTO.getPassword().equals(userAccountDTO.getConfirmPassword());
    }

    /**
     * Validates data from {@link UserAccountDTO}.
     *
     * @param userAccountDTO for storing data for validation
     * @param bindingResult  for storing validation errors
     * @param model          for storing attributes used to render view
     * @return true if validation was failed
     */
    public static boolean failedValidate(UserAccountDTO userAccountDTO, BindingResult bindingResult, Model model) {
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
