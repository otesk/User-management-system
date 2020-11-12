package com.otesk.ums.security;

import com.otesk.ums.domain.Status;
import com.otesk.ums.domain.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



@Data
@AllArgsConstructor
public class SecurityUserAccount implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUserAccount(UserAccount userAccount){
        return new User(userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getRole().getAuthorities());
    }

    public boolean isAdmin(){
        return true;
    }

}
