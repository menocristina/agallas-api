package com.agallas.agallasback.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String user;
    private String pass;
    private String newPass;
    private String name;
    private String surname;
    private Boolean isAdmin;

    public UserDto(Long id, String user, String pass, String name, String surname) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
    }

    public UserDto(Long id, String user, String name, String surname, Boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
    }

}
