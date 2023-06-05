package com.agallas.agallasback.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtDto {

    private static final String BEARER = "Bearer";

    private String token;
    private String username;
    private Long id;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String username, Collection<? extends GrantedAuthority> authorities, Long id) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
        this.id = id;
    }
}
