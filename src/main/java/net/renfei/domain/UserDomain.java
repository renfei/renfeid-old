package net.renfei.domain;

import net.renfei.application.ApplicationContextUtil;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.repositories.SysAccountMapper;
import net.renfei.repositories.SysAccountRoleMapper;
import net.renfei.repositories.SysRoleMapper;
import net.renfei.repositories.model.*;
import net.renfei.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author renfei
 */
public final class UserDomain implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserDomain.class);
    private static final long serialVersionUID = 5692684305054750477L;
    private final User user;
    private final SysRoleMapper sysRoleMapper;
    private final SysAccountMapper sysAccountMapper;
    private final SysAccountRoleMapper sysAccountRoleMapper;

    {
        sysRoleMapper = (SysRoleMapper) ApplicationContextUtil.getBean("sysRoleMapper");
        sysAccountMapper = (SysAccountMapper) ApplicationContextUtil.getBean("sysAccountMapper");
        sysAccountRoleMapper = (SysAccountRoleMapper) ApplicationContextUtil.getBean("sysAccountRoleMapper");
    }

    public UserDomain(User user) {
        this.user = user;
    }

    public UserDomain(String userName) {
        this.user = getUserByUserName(userName);
    }

    public List<GrantedAuthority> getAuthorities() {
        // 获取用户拥有的权限
        if (this.user == null) {
            return new ArrayList<>();
        }
        assert sysRoleMapper != null;
        assert sysAccountRoleMapper != null;
        SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
        accountRoleExample.createCriteria()
                .andAccountIdEqualTo(this.user.getId());
        List<SysAccountRole> sysAccountRoles = sysAccountRoleMapper.selectByExample(accountRoleExample);
        if (sysAccountRoles.isEmpty()) {
            return new CopyOnWriteArrayList<>();
        }
        List<Long> roleIds = new CopyOnWriteArrayList<>();
        sysAccountRoles.forEach(sysAccountRole -> roleIds.add(sysAccountRole.getRoleId()));
        SysRoleExample example = new SysRoleExample();
        example.createCriteria()
                .andIdIn(roleIds);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        if (sysRoles.isEmpty()) {
            return new CopyOnWriteArrayList<>();
        }
        List<GrantedAuthority> authorities = new CopyOnWriteArrayList<>();
        sysRoles.forEach(sysRole -> authorities.add(new GrantedAuthorityImpl(sysRole.getEnName())));
        return authorities;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        assert user != null;
        return user.getUserName();
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

    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    public static class GrantedAuthorityImpl implements GrantedAuthority {
        private static final long serialVersionUID = 1068092439492540925L;
        private final String authority;

        public GrantedAuthorityImpl(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
    }

    private User getUserByUserName(String userName) {
        SysAccountExample example = new SysAccountExample();
        example.createCriteria()
                .andUserNameEqualTo(userName);
        SysAccount account = ListUtils.getOne(sysAccountMapper.selectByExample(example));
        if (account == null) {
            logger.error("根据用户名 {} 查询用户不存在。", userName);
            throw new BusinessException("用户不存在");
        }
        // 检查用户状态
        if(account.getStateCode()<0){
            logger.error("根据用户名 {} 查询账户已被冻结。", userName);
            throw new BusinessException("账户已被冻结");
        }
        return new User(account);
    }
}
