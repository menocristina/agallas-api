package com.agallas.agallasback.controller;

import com.agallas.agallasback.model.dto.UserDto;
import com.agallas.agallasback.model.repository.UserRepository;
import com.agallas.agallasback.service.user.UserEditService;
import com.agallas.agallasback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserEditService userEditService;
    private final UserRepository userRepository;

    @GetMapping("/page/{page}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<UserDto> getAllUser(@PathVariable Integer page){
        return userService.getAll(PageRequest.of(page,4));
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDto getUser(@RequestParam Long userId){
        return userService.get(userId);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editUser(@RequestBody UserDto userDto){
        this.userEditService.editUser(userDto);
    }

}
