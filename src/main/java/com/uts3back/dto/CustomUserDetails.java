package com.uts3back.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final UsersDTO usersDTO;

    public CustomUserDetails(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return usersDTO.getUseLevel()+"";
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return usersDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return usersDTO.getEmail();
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
