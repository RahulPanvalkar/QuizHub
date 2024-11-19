package com.entities;

import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetail implements UserDetails {

    private static final Logger logger = LoggerUtil.getLogger(CustomUserDetail.class);

    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmailId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (Role role : user.getRoles()) { // Assuming `roles` is the Set<Role> in the User class
            logger.info("[{}]",role);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getDescr()));
        }

        return authorities;
    }
}
