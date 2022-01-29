package net.renfei.model.system;

import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

/**
 * @author renfei
 */
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = -5194970536302876575L;
    private final User user;

    public UserDetail(User user) {
        this.user = user;
    }

    public UserDetail(UserDomain userDomain) {
        this.user = userDomain.getUser().orElse(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new UserDomain(this.user).getAuthorities();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Optional<UserDomain> getUserDomain() {
        return Optional.ofNullable(new UserDomain(this.user));
    }
}
