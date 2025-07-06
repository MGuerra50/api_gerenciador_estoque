package com.inventory.manager.domain.users;

import java.util.Date;

public record UserDTO(
        Long id,
        String name,
        String email,
        String password,
        Date created_at,
        Date updated_at,
        boolean is_active,
        Roles role,
        boolean email_verified,
        boolean two_factor_enabled) {

    public UserDTO(Users user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated_at(),
                user.getUpdated_at(),
                user.is_active(),
                user.getRole(),
                user.isEmail_verified(),
                user.isTwo_factor_enabled()
        );
    }
}
