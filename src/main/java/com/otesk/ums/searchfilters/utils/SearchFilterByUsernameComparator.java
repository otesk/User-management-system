package com.otesk.ums.searchfilters.utils;

import com.otesk.ums.domain.UserAccount;

import java.util.Comparator;

public class SearchFilterByUsernameComparator implements Comparator<UserAccount> {
    @Override
    public int compare(UserAccount o1, UserAccount o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
