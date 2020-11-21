package com.otesk.ums.domain;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple JavaBean object that represents role of {@link UserAccount}
 *
 * @author Aliaksei Dvornichenko
 * @version 1.0
 */

@Getter
@AllArgsConstructor
public enum Role{
    USER(Set.of(Permission.USERS_READ)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE));

    /**
     * Field for storing set of permissions.
     */
    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
