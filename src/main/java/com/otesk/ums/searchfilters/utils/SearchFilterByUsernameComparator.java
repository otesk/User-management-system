package com.otesk.ums.searchfilters.utils;

import com.otesk.ums.domain.UserAccount;

import java.util.Comparator;

/**
 * Java-class used to sort by username alphabetically of items in a list of {@link UserAccount} during a search.
 *
 * @version 1.0
 * @author Aleksey Dvornichenko
 */

public class SearchFilterByUsernameComparator implements Comparator<UserAccount> {
    @Override
    public int compare(UserAccount o1, UserAccount o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
