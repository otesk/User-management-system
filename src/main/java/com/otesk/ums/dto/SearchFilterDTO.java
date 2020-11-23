package com.otesk.ums.dto;

import com.otesk.ums.controllers.UserAccountController;
import com.otesk.ums.domain.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Java-class used to store the filtering parameters of the list of {@link com.otesk.ums.domain.UserAccount}.
 */

@Getter
@Setter
public class SearchFilterDTO {
    /**
     * Stores the string for filtering.
     */
    private String usernameForSearchFilter;
    /**
     * Stores set of roles for filtering.
     */
    private Set<Role> rolesForSearchFilter;

    /**
     * The method creates a class object searchFilterDTO from the form that came from the request {@link UserAccountController#getUserAccountListPage}
     *
     * @param form that contains request parameters
     * @return searchFilterDTO for filter result of {@link UserAccountController#getUserAccountListPage}
     */
    public static SearchFilterDTO createSearchFilterFromForm(Map<String, String> form) {
        if (form != null) {
            SearchFilterDTO searchFilterDTO = new SearchFilterDTO();
            if (!StringUtils.isEmpty(form.get("usernameForSearchFilter"))) {
                searchFilterDTO.setUsernameForSearchFilter(form.get("usernameForSearchFilter"));
            }
            Set<String> allRolesName = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());
            Set<Role> selectedRoles = new HashSet<>();
            for (String roleName : allRolesName) {
                if (form.containsKey(roleName)) {
                    selectedRoles.add(Role.valueOf(roleName));
                }
            }
            searchFilterDTO.setRolesForSearchFilter(selectedRoles);
            return searchFilterDTO;
        } else return null;
    }
}
