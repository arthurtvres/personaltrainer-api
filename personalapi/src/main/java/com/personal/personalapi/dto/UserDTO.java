package com.personal.personalapi.dto;

import com.personal.personalapi.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}

