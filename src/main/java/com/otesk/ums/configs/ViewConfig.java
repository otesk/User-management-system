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

@Configuration
@AllArgsConstructor
public class ViewConfig {

    private final UserAccountRepository userAccountRepository;

    @Bean
    protected List<SearchFilter> searchFilters(){
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilters.add(new SearchFilterByRole(userAccountRepository));
        searchFilters.add(new SearchFilterByUsername());
        return searchFilters;
    }
}
