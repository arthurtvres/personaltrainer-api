package com.personal.personalapi.controller;

import com.personal.personalapi.dto.UserDTO;
import com.personal.personalapi.model.User;
import com.personal.personalapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User criar(@RequestBody UserDTO userDTO) {
        return userService.salvarUsuario(userDTO);
    }

    @GetMapping
    public List<User> listarUsuarios() {
        return userService.listarUsuarios();
    }

}
