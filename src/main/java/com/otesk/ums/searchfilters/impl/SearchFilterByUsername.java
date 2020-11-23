package com.otesk.ums.searchfilters.impl;


import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.searchfilters.SearchFilter;
import com.otesk.ums.searchfilters.utils.SearchFilterByUsernameComparator;
import com.otesk.ums.searchfilters.utils.SearchFilterByUsernameUtil;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Implementation of {@link SearchFilter}. Using for filter list of user accounts by username.
 */
@AllArgsConstructor
public class SearchFilterByUsername implements SearchFilter {

    /**
     * Filters list of user accounts by the username.
     *
     * @param userAccounts stores list of user accounts
     * @param searchFilterDTO stores data for filtering
     * @return filtered by username list of user accounts
     */
    @Override
    public List<UserAccount> findAllByFilter(List<UserAccount> userAccounts, SearchFilterDTO searchFilterDTO) {
        if (!StringUtils.isEmpty(searchFilterDTO.getUsernameForSearchFilter())) {
            Map<UserAccount, Integer> userAccountMap = new TreeMap<>(new SearchFilterByUsernameComparator());
            userAccounts.stream()
                    .filter(userAccount -> userAccount.getUsername().toLowerCase().contains(searchFilterDTO.getUsernameForSearchFilter().toLowerCase()))
                    .forEach(userAccount -> userAccountMap.put(userAccount,
                            SearchFilterByUsernameUtil.calculateLevenshteinDistance(searchFilterDTO.getUsernameForSearchFilter(), userAccount.getUsername())));
            userAccounts = userAccountMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
        return userAccounts;
    }
}
