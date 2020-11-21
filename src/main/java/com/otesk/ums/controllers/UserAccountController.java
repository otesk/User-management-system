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
 *
 * @author Aliaksei Dvornichenko
 * @version 1.0
 */

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

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

    @GetMapping("/{id}")
    public String getUserAccountPage(@PathVariable("id") UserAccount userAccount, Model model) {
        if (userAccount != null) model.addAttribute("user", userAccount);
        else return "redirect:/user";
        return "userAccountPage";
    }

    @PreAuthorize("hasAuthority('users:write')")
    @PutMapping("/{id}")
    public String changeStatusOfUserAccount(@PathVariable("id") UserAccount userAccount) {
        userAccountService.changeStatusOfUserAccount(userAccount);
        return "redirect:/user/{id}";
    }

    @PreAuthorize("hasAuthority('users:write')")
    @GetMapping("/{id}/edit")
    public String getUserAccountEditForm(@PathVariable("id") UserAccount userAccount,
                                         Model model) {
        if (userAccount != null) model.addAttribute("userAccount", userAccount);
        else return "redirect:/user";
        return "userAccountEdit";
    }

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

    @PreAuthorize("hasAuthority('users:write')")
    @GetMapping("/new")
    public String registration() {
        return "registration";
    }

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

    @PreAuthorize("hasAuthority('users:write')")
    @DeleteMapping("/{id}")
    public String deleteUserAccount(@PathVariable("id") Long id) {
        userAccountService.deleteUserAccountById(id);
        return "redirect:/user";
    }
}