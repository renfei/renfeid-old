package net.renfei.domain;

import net.renfei.domain.user.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author renfei
 */
public final class UserDomain {
    private final User user;

    public UserDomain(User user) {
        this.user = user;
    }

    public UserDomain(String userName) {
        this.user = null;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }
}
