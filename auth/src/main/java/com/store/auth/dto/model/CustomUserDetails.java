package com.store.auth.dto.model;

import com.store.auth.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {

    private String username;
    private String passwordDetails;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User byEmail) {
        this.username = byEmail.getEmail();
        this.passwordDetails = byEmail.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for(String role : byEmail.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordDetails;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
