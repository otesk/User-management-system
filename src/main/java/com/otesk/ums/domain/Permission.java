package com.otesk.ums.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Java class that describes the {@link Role} permission of {@link UserAccount}.
 */

@Getter
@AllArgsConstructor
public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    /**
     * Field for storing string representation of permission.
     */
    private final String permission;
}
