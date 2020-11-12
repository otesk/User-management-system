package com.otesk.ums.searchfilters.impl;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.repositories.UserAccountRepository;
import com.otesk.ums.searchfilters.SearchFilter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchFilterByRole implements SearchFilter {

    private final UserAccountRepository userAccountRepository;

    @Override
    public List<UserAccount> findAllByFilter(List<UserAccount> userAccounts, SearchFilterDTO searchFilterDTO) {
        if(searchFilterDTO.getRolesForSearchFilter() != null && !searchFilterDTO.getRolesForSearchFilter().isEmpty()){
            userAccounts.clear();
            for (Role role : searchFilterDTO.getRolesForSearchFilter()){
                userAccounts.addAll(userAccountRepository.findAllByRole(role));
            }
        }
        return userAccounts;
    }
}
