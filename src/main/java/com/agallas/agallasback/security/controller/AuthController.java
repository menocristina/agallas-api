package com.agallas.agallasback.security.controller;

import com.agallas.agallasback.exception.UserAlreadyExistsException;
import com.agallas.agallasback.model.dto.UserDto;
import com.agallas.agallasback.security.details.UserCustomDetails;
import com.agallas.agallasback.security.dto.JwtDto;
import com.agallas.agallasback.security.dto.LoginDto;
import com.agallas.agallasback.security.jwt.JwtProvider;
import com.agallas.agallasback.service.RoleService;
import com.agallas.agallasback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody @NotNull LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserCustomDetails userDetails = (UserCustomDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities(), userDetails.getId());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<LoginDto> newUser(@RequestBody UserDto userDto){
        try{
            userService.newUser(userDto);
            return new ResponseEntity<>(new LoginDto(userDto.getUser(), userDto.getPass()), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
