package com.otesk.ums.unit;

import com.otesk.ums.dto.UserAccountDTO;
import com.otesk.ums.validation.ValidationUserAccountUtils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ValidationTests {

    private static final UserAccountDTO userAccountDTO = mock(UserAccountDTO.class);
    private static final BindingResult bindingResult = mock(BindingResult.class);
    private static final Model model = mock(Model.class);

    @Test
    public void failedValidationWithSuccessfulPasswordConfirmationTest(){
        validationConfiguration("user1234", "user1234", false);
        assertFalse(ValidationUserAccountUtils.failedValidate(userAccountDTO, bindingResult, model));
    }

    @Test
    public void failedValidationWithFailedPasswordConfirmationTest(){
        validationConfiguration("admin1234", "user12", false);
        assertTrue(ValidationUserAccountUtils.failedValidate(userAccountDTO, bindingResult, model));
    }

    @Test
    public void failedValidationWithBindingErrors(){
        validationConfiguration("user1234", "user1234", true);
        assertTrue(ValidationUserAccountUtils.failedValidate(userAccountDTO, bindingResult, model));
    }

    private void validationConfiguration(String password, String confirmPassword, boolean bindingResultHasErrors){
        when(userAccountDTO.getPassword()).thenReturn(password);
        when(userAccountDTO.getConfirmPassword()).thenReturn(confirmPassword);
        when(bindingResult.hasErrors()).thenReturn(bindingResultHasErrors);
    }
}
