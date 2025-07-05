package com.inventory.manager.domain.users;

public record RegisterDTO(String email, String password, Roles roles) {
}
