package com.marcRG.javaCommunity.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class User extends IncrementalClass implements UserDetails {

    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "email",nullable = false)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Task> taskList;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "motDePasse",nullable = false)
    private String motDePasse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return motDePasse;
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
