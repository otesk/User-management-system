package com.otesk.ums.searchfilters;

import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;

import java.util.List;

public interface SearchFilter {

    List<UserAccount> findAllByFilter(List<UserAccount> userAccounts, SearchFilterDTO searchFilterDTO);
}
