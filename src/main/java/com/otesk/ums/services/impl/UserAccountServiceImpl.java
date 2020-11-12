package com.otesk.ums.services.impl;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.Status;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.dto.UserAccountDTO;
import com.otesk.ums.repositories.UserAccountRepository;
import com.otesk.ums.services.UserAccountService;
import com.otesk.ums.searchfilters.SearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<SearchFilter> searchFilters;

    @Override
    public UserAccount findByUsername(String username) {
        if (userAccountRepository.findByUsername(username).isPresent()) {
            return userAccountRepository.findByUsername(username).get();
        }
        return null;
    }

    @Override
    public UserAccount findById(Long id) {
        if (userAccountRepository.findById(id).isPresent()) {
            return userAccountRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Page<UserAccount> getUserAccountPage(Pageable pageable, List<UserAccount> userAccounts) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userAccounts.size());
        List<UserAccount> userAccountSublist = userAccounts.subList(start, end);
        return new PageImpl<>(userAccountSublist, pageable, userAccounts.size());
    }

    @Override
    public Page<UserAccount> findAllByFilter(Pageable pageable, SearchFilterDTO searchFilterDTO) {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        for (SearchFilter searchFilter : searchFilters){
            userAccounts = searchFilter.findAllByFilter(userAccounts, searchFilterDTO);
        }
        return getUserAccountPage(pageable, userAccounts);
    }

    @Override
    @Transactional
    public boolean registerUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccountFromDb = findByUsername(userAccountDTO.getUsername());
        if (userAccountFromDb != null) return false;
        else {
            userAccountRepository.save(UserAccount.builder()
                    .username(userAccountDTO.getUsername())
                    .password(passwordEncoder.encode(userAccountDTO.getPassword()))
                    .firstName(userAccountDTO.getFirstName())
                    .lastName(userAccountDTO.getLastName())
                    .role(Role.USER)
                    .status(Status.ACTIVE)
                    .createdAt(Date.from(Instant.now()))
                    .build());
            return true;
        }
    }

    @Override
    @Transactional
    public void editUserAccount(UserAccount userAccount, UserAccountDTO userAccountDTO) {
        userAccount.setUsername(userAccountDTO.getUsername());
        userAccount.setFirstName(userAccountDTO.getFirstName());
        userAccount.setLastName(userAccountDTO.getLastName());
        userAccount.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));
        userAccountRepository.save(userAccount);
    }

    @Override
    @Transactional
    public void deleteUserAccountById(Long id) {
        userAccountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changeStatusOfUserAccount(UserAccount userAccount) {
        if (userAccount.getStatus().equals(Status.ACTIVE)){
            userAccount.setStatus(Status.INACTIVE);
        } else {
            userAccount.setStatus(Status.ACTIVE);
        }
        userAccountRepository.save(userAccount);
    }
}
