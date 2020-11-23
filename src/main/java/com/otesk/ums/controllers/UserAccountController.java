package com.otesk.ums.controllers;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.dto.UserAccountDTO;
import com.otesk.ums.services.UserAccountService;
import com.otesk.ums.validation.ValidationUserAccountUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


/**
 * Controller for {@link UserAccount}'s pages.
 */

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserAccountController {

    /**
     * Field for working with business logic.
     */
    private final UserAccountService userAccountService;

    /**
     * Displays a list of user accounts.
     *
     * @param form     for storing parameters from request
     * @param model    for storing attributes used to render view
     * @param pageable to ensure pagination
     * @return view of user account list
     */
    @GetMapping
    public String getUserAccountListPage(@RequestParam Map<String, String> form,
                                         Model model,
                                         @PageableDefault Pageable pageable) {
        SearchFilterDTO searchFilterDTO = SearchFilterDTO.createSearchFilterFromForm(form);
        Page<UserAccount> page = userAccountService.findAllByFilter(pageable, searchFilterDTO);
        model.addAttribute("searchFilter", searchFilterDTO);
        model.addAttribute("roles", Role.values());
        model.addAttribute("page", page);
        return "userAccountList";
    }

    /**
     * Displays a user with a specific id.
     *
     * @param userAccount for storing {@link UserAccount} with specific id
     * @param model       for storing attributes used to render view
     * @return page of user account with id from request
     */
    @GetMapping("/{id}")
    public String getUserAccountPage(@PathVariable("id") UserAccount userAccount, Model model) {
        if (userAccount != null) model.addAttribute("user", userAccount);
        else return "redirect:/user";
        return "userAccountPage";
    }

    /**
     * Changes user account status.
     *
     * @param userAccount for storing {@link UserAccount} with specific id
     * @return page of user account with id from request
     */
    @PreAuthorize("hasAuthority('users:write')")
    @PutMapping("/{id}")
    public String changeStatusOfUserAccount(@PathVariable("id") UserAccount userAccount) {
        userAccountService.changeStatusOfUserAccount(userAccount);
        return "redirect:/user/{id}";
    }

    /**
     * Displays a form for editing user account data with id from the request.
     *
     * @param userAccount for storing {@link UserAccount} with specific id
     * @param model       for storing attributes used to render view
     * @return page to edit user account with id from request
     */
    @PreAuthorize("hasAuthority('users:write')")
    @GetMapping("/{id}/edit")
    public String getUserAccountEditForm(@PathVariable("id") UserAccount userAccount,
                                         Model model) {
        if (userAccount != null) model.addAttribute("userAccount", userAccount);
        else return "redirect:/user";
        return "userAccountEdit";
    }

    /**
     * Updates user account data with id from request.
     *
     * @param userAccount    for storing {@link UserAccount} with specific id
     * @param userAccountDTO for storing form data for validation
     * @param bindingResult  for storing validation errors
     * @param model          for storing attributes used to render view
     * @return <ul>
     * <li>page to edit user account with id from request if validation was failed</li>
     * <li>page with list of user accounts if the validation was successful</li>
     * </ul>
     */
    @PreAuthorize("hasAuthority('users:write')")
    @PutMapping("/{id}/edit")
    public String userEdit(@PathVariable("id") UserAccount userAccount,
                           @Valid UserAccountDTO userAccountDTO,
                           BindingResult bindingResult,
                           Model model) {
        if (ValidationUserAccountUtils.failedValidate(userAccountDTO, bindingResult, model)) {
            model.addAttribute("userAccount", userAccount);
            model.addAttribute("userAccountDTO", userAccountDTO);
            return "userAccountEdit";
        }
        userAccountService.editUserAccount(userAccount, userAccountDTO);
        return "redirect:/user";
    }

    /**
     * Displays form for creating new user account.
     *
     * @return page for creating a new user account
     */
    @PreAuthorize("hasAuthority('users:write')")
    @GetMapping("/new")
    public String registration() {
        return "registration";
    }

    /**
     * Creates a new user account.
     *
     * @param userAccountDTO for storing form data for validation
     * @param bindingResult  for storing validation errors
     * @param model          for storing attributes used to render view
     * @return <ul>
     * <li>page to create new user account with id from request if validation was failed</li>
     * <li>page to create new user account with id from request if user account with the same username already exists</li>
     * <li>page with list of user accounts in other cases</li>
     * </ul>
     */
    @PreAuthorize("hasAuthority('users:write')")
    @PostMapping("/new")
    public String registerUserAccount(@Valid UserAccountDTO userAccountDTO,
                                      BindingResult bindingResult,
                                      Model model) {
        if (ValidationUserAccountUtils.failedValidate(userAccountDTO, bindingResult, model)) {
            model.addAttribute("userAccount", userAccountDTO);
            return "registration";
        }
        if (!userAccountService.registerUserAccount(userAccountDTO)) {
            model.addAttribute("usernameError", "User with this username is already exist!");
            return "registration";
        }
        return "redirect:/user";
    }

    /**
     * Deletes user account.
     *
     * @param id for storing user account id
     * @return page with user accounts
     */
    @PreAuthorize("hasAuthority('users:write')")
    @DeleteMapping("/{id}")
    public String deleteUserAccount(@PathVariable("id") Long id) {
        userAccountService.deleteUserAccountById(id);
        return "redirect:/user";
    }
}