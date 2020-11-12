package com.otesk.ums.dto;


import com.otesk.ums.domain.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class SearchFilterDTO {
    private String usernameForSearchFilter;
    private Set<Role> rolesForSearchFilter;

    public static SearchFilterDTO createSearchFilterFromForm(Map<String, String> form){
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
//        Set<String> rolesNames = form.entrySet().stream()
//                .filter(role -> role.getValue().equals("on"))
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toSet());
//        Set<Role> selectedRoles = new HashSet<>();
//        for (String roleName : rolesNames){
//            if()
//        }
    }
}
