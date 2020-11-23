package com.otesk.ums.unit;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.UserAccount;
import com.otesk.ums.dto.SearchFilterDTO;
import com.otesk.ums.repositories.UserAccountRepository;
import com.otesk.ums.searchfilters.impl.SearchFilterByRole;
import com.otesk.ums.searchfilters.impl.SearchFilterByUsername;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SearchFiltersTests {

    private static final UserAccountRepository userRepo = mock(UserAccountRepository.class);
    private static final SearchFilterDTO searchFilter = mock(SearchFilterDTO.class);
    private List<UserAccount> userList;

    @Before
    public void userAccountListInitialization() {
        userList = new ArrayList<>();
        userList.add(mock(UserAccount.class));
        userList.add(mock(UserAccount.class));
        userList.add(mock(UserAccount.class));
        when(userList.get(0).getUsername()).thenReturn("admin");
        when(userList.get(0).getRole()).thenReturn(Role.ADMIN);
        when(userList.get(1).getUsername()).thenReturn("user");
        when(userList.get(1).getRole()).thenReturn(Role.USER);
        when(userList.get(2).getUsername()).thenReturn("seconduser");
        when(userList.get(2).getRole()).thenReturn(Role.USER);
    }

    @Test
    public void searchFilterByRoleAdminTest() {
        searchFilterByRoleConfiguration(Set.of(Role.ADMIN));
        assertEquals(new SearchFilterByRole(userRepo).findAllByFilter(userList, searchFilter).size(), 1);
    }

    @Test
    public void searchFilterByRoleUserTest() {
        searchFilterByRoleConfiguration(Set.of(Role.USER));
        assertEquals(new SearchFilterByRole(userRepo).findAllByFilter(userList, searchFilter).size(), 2);
    }

    @Test
    public void searchFilterByRolesAdminAndUserTest() {
        searchFilterByRoleConfiguration(Set.of(Role.ADMIN, Role.USER));
        assertEquals(new SearchFilterByRole(userRepo).findAllByFilter(userList, searchFilter).size(), 3);
    }

    @Test
    public void searchFilterByUsernameTest() {
        when(searchFilter.getUsernameForSearchFilter()).thenReturn("user");
        assertEquals(new SearchFilterByUsername().findAllByFilter(userList, searchFilter).size(), 2);
    }

    private void searchFilterByRoleConfiguration(Set<Role> roles) {
        for (Role someRole : roles) {
            List<UserAccount> userAccountList = mockUserAccountRepository(someRole);
            when(userRepo.findAllByRole(someRole)).thenReturn(userAccountList);
            when(searchFilter.getRolesForSearchFilter()).thenReturn(roles);
        }
    }

    private List<UserAccount> mockUserAccountRepository(Role role) {
        List<UserAccount> userAccountList = new ArrayList<>();
        for (UserAccount userAccount : userList) {
            if (userAccount.getRole().equals(role)) {
                userAccountList.add(userAccount);
            }
        }
        return userAccountList;
    }
}
