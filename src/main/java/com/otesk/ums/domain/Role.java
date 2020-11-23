package com.otesk.ums.domain;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple JavaBean object that represents role of {@link UserAccount}.
 */

@Getter
@AllArgsConstructor
public enum Role {
    USER(Set.of(Permission.USERS_READ)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE));

    /**
     * Field for storing set of permissions.
     */
    private final Set<Permission> permissions;

    /**
     * Converts permission set to {@link SimpleGrantedAuthority} set.
     *
     * @return set of simple granted authorities
     */
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
