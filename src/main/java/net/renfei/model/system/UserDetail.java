package net.renfei.model.system;

import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author renfei
 */
public class UserDetail implements UserDetails {
    private final UserDomain userDomain;

    public UserDetail(User user) {
        this.userDomain = new UserDomain(user);
    }

    public UserDetail(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userDomain.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.userDomain.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userDomain.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userDomain.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userDomain.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userDomain.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userDomain.isEnabled();
    }
}
