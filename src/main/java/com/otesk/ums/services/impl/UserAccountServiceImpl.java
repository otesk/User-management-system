package com.otesk.ums.services.impl;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.Status;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.dto.UserAccountDTO;
import com.otesk.ums.repositories.UserAccountRepository;
import com.otesk.ums.searchfilters.SearchFilter;
import com.otesk.ums.services.UserAccountService;
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

/**
 * Implementation of {@link UserAccountService}. The class contains the business logic for {@link UserAccount} entity.
 */
@AllArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    /**
     * Carries out work with the database.
     */
    private final UserAccountRepository userAccountRepository;
    /**
     * Encodes passwords of user account.
     */
    private final PasswordEncoder passwordEncoder;
    /**
     * Stores filtering data.
     */
    private final List<SearchFilter> searchFilters;

    /**
     * Finds a user account in the database by username.
     *
     * @param username stores username of user account for searching
     * @return user account if it exists in the database
     */
    @Override
    public UserAccount findByUsername(String username) {
        if (userAccountRepository.findByUsername(username).isPresent()) {
            return userAccountRepository.findByUsername(username).get();
        }
        return null;
    }

    /**
     * Represents a list of user accounts according to the pagination conditions.
     *
     * @param pageable     stores data for pagination
     * @param userAccounts stores data of user accounts list
     * @return sublist according to pagination conditions
     */
    @Override
    public Page<UserAccount> getUserAccountPage(Pageable pageable, List<UserAccount> userAccounts) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userAccounts.size());
        List<UserAccount> userAccountSublist = userAccounts.subList(start, end);
        return new PageImpl<>(userAccountSublist, pageable, userAccounts.size());
    }

    /**
     * Filters the list of user account.
     *
     * @param pageable        stores data for pagination
     * @param searchFilterDTO stores data for filtering
     * @return filtered list of user accounts
     */
    @Override
    public Page<UserAccount> findAllByFilter(Pageable pageable, SearchFilterDTO searchFilterDTO) {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        for (SearchFilter searchFilter : searchFilters) {
            userAccounts = searchFilter.findAllByFilter(userAccounts, searchFilterDTO);
        }
        return getUserAccountPage(pageable, userAccounts);
    }

    /**
     * Creates new user.
     *
     * @param userAccountDTO stores data for registration and validation
     * @return true if registration was successful
     */
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

    /**
     * Saves updated user account in DB.
     *
     * @param userAccount    stores data of user account
     * @param userAccountDTO stores updated data of user account
     */
    @Override
    @Transactional
    public void editUserAccount(UserAccount userAccount, UserAccountDTO userAccountDTO) {
        userAccount.setUsername(userAccountDTO.getUsername());
        userAccount.setFirstName(userAccountDTO.getFirstName());
        userAccount.setLastName(userAccountDTO.getLastName());
        userAccount.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));
        userAccountRepository.save(userAccount);
    }

    /**
     * Deletes user account with specific id from DB.
     *
     * @param id stores user account id
     */
    @Override
    @Transactional
    public void deleteUserAccountById(Long id) {
        userAccountRepository.deleteById(id);
    }

    /**
     * Changes user account status.
     *
     * @param userAccount stores data about user account for status changing
     */
    @Override
    @Transactional
    public void changeStatusOfUserAccount(UserAccount userAccount) {
        if (userAccount.getStatus().equals(Status.ACTIVE)) {
            userAccount.setStatus(Status.INACTIVE);
        } else {
            userAccount.setStatus(Status.ACTIVE);
        }
        userAccountRepository.save(userAccount);
    }
}
