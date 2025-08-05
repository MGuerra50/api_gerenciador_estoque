package com.inventory.manager.domain.users;

import com.inventory.manager.exception.AuthenticationFailureExcetion;
import jakarta.persistence.*;
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

    public Users(Long id, String name, String email, String password, Date created_at, Date updated_at, boolean is_active, Roles role, boolean email_verified, boolean two_factor_enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_active = is_active;
        this.role = role;
        this.email_verified = email_verified;
        this.two_factor_enabled = two_factor_enabled;
    }

    public Users() {
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

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public boolean is_active() {
        return this.is_active;
    }

    public Roles getRole() {
        return this.role;
    }

    public boolean isEmail_verified() {
        return this.email_verified;
    }

    public boolean isTwo_factor_enabled() {
        return this.two_factor_enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void set_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public void setTwo_factor_enabled(boolean two_factor_enabled) {
        this.two_factor_enabled = two_factor_enabled;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Users)) return false;
        final Users other = (Users) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Users;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
