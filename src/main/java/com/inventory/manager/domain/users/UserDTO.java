package com.inventory.manager.domain.users;

public record UserDTO(
        Long id,
        String name,
        String email,
        boolean is_active,
        String role) {

    public UserDTO(Users user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.is_active(),
                String.valueOf(user.getRole())
        );
    }
}
