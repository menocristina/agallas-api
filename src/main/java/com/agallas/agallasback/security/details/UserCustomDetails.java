package com.agallas.agallasback.security.details;

import com.agallas.agallasback.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserCustomDetails implements UserDetails {

    private String nombre;
    private String username;
    private String password;
    private Long id;

    private Collection<? extends GrantedAuthority> authorities;

    public UserCustomDetails(String nombre, String username, String password, Long id, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.id = id;
    }

    public static UserCustomDetails build(UserEntity user){
        List<GrantedAuthority> authorities =
                user.getRoles()
                .stream()
                .map( role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                .collect(Collectors.toList());

        return new UserCustomDetails(
                user.getName(),
                user.getUser(),
                user.getPass(),
                user.getId(),
                authorities
        );
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
