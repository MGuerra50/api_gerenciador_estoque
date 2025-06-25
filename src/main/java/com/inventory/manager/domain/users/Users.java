package com.inventory.manager.domain.users;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "im_user")
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date created_at;
    private Date updated_at;
    private boolean is_active;
    private Roles role;
    private boolean email_verified;
    private boolean two_factor_enabled;

}
