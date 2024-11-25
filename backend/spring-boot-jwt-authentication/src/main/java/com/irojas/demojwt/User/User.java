package com.irojas.demojwt.User;

import java.util.Collection;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Schema(description = "ID único del usuario", example = "1")
    Integer id;

    @Basic
    @Column(nullable = false)
    @Schema(description = "Nombre de usuario", example = "johndoe", required = true)
    String username;

    @Column(nullable = false)
    @Schema(description = "Apellido del usuario", example = "Doe", required = true)
    String lastname;

    @Schema(description = "Nombre del usuario", example = "John")
    String firstname;

    @Schema(description = "País del usuario", example = "Panamá")
    String country;

    @Schema(description = "Contraseña del usuario", example = "P@ssw0rd")
    String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Rol del usuario", example = "ADMIN")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
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
