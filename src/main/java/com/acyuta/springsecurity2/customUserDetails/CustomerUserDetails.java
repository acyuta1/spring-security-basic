package com.acyuta.springsecurity2.customUserDetails;

import com.acyuta.springsecurity2.domain.model.Authority;
import com.acyuta.springsecurity2.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UserDetails holds the schema of a user, that is, the details of the
 * user needed to authenticate.
 */
@RequiredArgsConstructor
public class CustomerUserDetails implements UserDetails {

    private final Customer customer;

    /**
     * Authorities/Roles: Used for authorization, AUTHZ.
     * @return List of authorities that a user possesses.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        var authoritiesOfUser = customer.getAuthorities();
        for(Authority authority : authoritiesOfUser) {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return customer.getPwd();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
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
