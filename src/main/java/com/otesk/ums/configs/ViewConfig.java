package com.otesk.ums.configs;

import com.otesk.ums.repositories.UserAccountRepository;
import com.otesk.ums.searchfilters.SearchFilter;
import com.otesk.ums.searchfilters.impl.SearchFilterByRole;
import com.otesk.ums.searchfilters.impl.SearchFilterByUsername;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration of beans for view list of {@link com.otesk.ums.domain.UserAccount}.
 */
@Configuration
@AllArgsConstructor
public class ViewConfig {

    /**
     * Field for working with the database.
     */
    private final UserAccountRepository userAccountRepository;

    /**
     * Configures a bean to filter the list of user accounts in {@link com.otesk.ums.controllers.UserAccountController#getUserAccountListPage}
     *
     * @return list of searchFilters in a certain order
     */
    @Bean
    protected List<SearchFilter> searchFilters() {
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilters.add(new SearchFilterByRole(userAccountRepository));
        searchFilters.add(new SearchFilterByUsername());
        return searchFilters;
    }
}
