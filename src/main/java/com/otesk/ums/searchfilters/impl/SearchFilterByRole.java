package com.otesk.ums.searchfilters.impl;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.repositories.UserAccountRepository;
import com.otesk.ums.searchfilters.SearchFilter;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Implementation of {@link SearchFilter}. Using for filter list of user accounts by role.
 */
@AllArgsConstructor
public class SearchFilterByRole implements SearchFilter {

    /**
     * Carries out work with the database.
     */
    private final UserAccountRepository userAccountRepository;

    /**
     * Filters list of user accounts by the set of roles.
     *
     * @param userAccounts    stores list of user accounts
     * @param searchFilterDTO stores data for filtering
     * @return filtered by role list of user accounts
     */
    @Override
    public List<UserAccount> findAllByFilter(List<UserAccount> userAccounts, SearchFilterDTO searchFilterDTO) {
        if (searchFilterDTO.getRolesForSearchFilter() != null && !searchFilterDTO.getRolesForSearchFilter().isEmpty()) {
            userAccounts.clear();
            for (Role role : searchFilterDTO.getRolesForSearchFilter()) {
                userAccounts.addAll(userAccountRepository.findAllByRole(role));
            }
        }
        return userAccounts;
    }
}
