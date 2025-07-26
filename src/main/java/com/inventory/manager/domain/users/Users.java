package com.inventory.manager.domain.users;

import com.inventory.manager.exception.AuthenticationFailureExcetion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "im_user")
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = " UPDATE im_user " +
        " SET is_active = false " +
        " WHERE id = ? ")
@SQLRestriction(" is_active = true ")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date created_at;
    private Date updated_at;
    private boolean is_active;

    @Enumerated(EnumType.STRING)
    private Roles role;
    private boolean email_verified;
    private boolean two_factor_enabled;

    public Users(String email, String encryptedPassword, Roles roles) {
        this.email = email;
        this.password = encryptedPassword;
        this.role = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            if (this.role == Roles.ADMIN) {
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
            } else if (this.role == null) {
                throw new AuthenticationFailureExcetion("Usuário sem permissão");
            }
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        } catch (AuthenticationFailureExcetion e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
