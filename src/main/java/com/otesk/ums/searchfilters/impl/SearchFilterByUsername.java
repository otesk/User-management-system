package com.otesk.ums.searchfilters.impl;


import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.searchfilters.SearchFilter;
import com.otesk.ums.searchfilters.utils.SearchFilterByUsernameComparator;
import com.otesk.ums.searchfilters.utils.SearchFilterByUsernameUtil;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SearchFilterByUsername implements SearchFilter {

    @Override
    public List<UserAccount> findAllByFilter(List<UserAccount> userAccounts, SearchFilterDTO searchFilterDTO) {
        if (searchFilterDTO.getUsernameForSearchFilter() != null && !searchFilterDTO.getUsernameForSearchFilter().isEmpty()) {
            Map<UserAccount, Integer> userAccountMap = new TreeMap<>(new SearchFilterByUsernameComparator());
            userAccounts.forEach(userAccount -> userAccountMap.put(userAccount,
                    SearchFilterByUsernameUtil.calculateLevenshteinDistance(searchFilterDTO.getUsernameForSearchFilter(), userAccount.getUsername())));
            userAccounts = userAccountMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
        return userAccounts;
    }
}
