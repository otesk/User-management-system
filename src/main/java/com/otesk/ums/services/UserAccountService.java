package com.otesk.ums.services;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.dto.UserAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAccountService {

    Page<UserAccount> findAllByFilter(Pageable pageable, SearchFilterDTO searchFilterDTO);

    UserAccount findByUsername(String username);

    UserAccount findById(Long id);

    boolean registerUserAccount(UserAccountDTO userAccountDTO);

    void editUserAccount(UserAccount userAccount, UserAccountDTO userAccountDTO);

    void deleteUserAccountById(Long id);

    void changeStatusOfUserAccount(UserAccount userAccount);

    Page<UserAccount> getUserAccountPage(Pageable pageable, List<UserAccount> userAccounts);
}
