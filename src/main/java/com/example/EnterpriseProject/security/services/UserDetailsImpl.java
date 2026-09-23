package com.example.EnterpriseProject.security.services;

import com.example.EnterpriseProject.entity.User;
import com.example.EnterpriseProject.state.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cardId;

    private String username;

    private String email;

    private boolean enabled;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String cardId, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.cardId = cardId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //Help to tranform the User we defined internally to UserDetailsImpl so that Spring Security can understand and manage
    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        boolean isEnabled = user.getStatus() == UserStatus.ACTIVE;

        UserDetailsImpl userDetails = new UserDetailsImpl(
                user.getId(), user.getCardId(), user.getUsername(), user.getEmail(), user.getPassword(), authorityList
        );

        userDetails.setEnabled(isEnabled);

        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return this.enabled;
    }


}
