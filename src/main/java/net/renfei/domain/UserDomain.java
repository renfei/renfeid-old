package net.renfei.domain;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.application.ApplicationContextUtil;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.model.ListData;
import net.renfei.model.SecretLevelEnum;
import net.renfei.repositories.SysAccountMapper;
import net.renfei.repositories.SysAccountRoleMapper;
import net.renfei.repositories.SysRoleMapper;
import net.renfei.repositories.model.*;
import net.renfei.utils.ListUtils;
import net.renfei.utils.NumberUtils;
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

    private UserDomain() {
        sysRoleMapper = (SysRoleMapper) ApplicationContextUtil.getBean("sysRoleMapper");
        sysAccountMapper = (SysAccountMapper) ApplicationContextUtil.getBean("sysAccountMapper");
        sysAccountRoleMapper = (SysAccountRoleMapper) ApplicationContextUtil.getBean("sysAccountRoleMapper");
        user = null;
    }

    public UserDomain(User user) {
        sysRoleMapper = (SysRoleMapper) ApplicationContextUtil.getBean("sysRoleMapper");
        sysAccountMapper = (SysAccountMapper) ApplicationContextUtil.getBean("sysAccountMapper");
        sysAccountRoleMapper = (SysAccountRoleMapper) ApplicationContextUtil.getBean("sysAccountRoleMapper");
        this.user = user;
    }

    public UserDomain(String userName) {
        sysRoleMapper = (SysRoleMapper) ApplicationContextUtil.getBean("sysRoleMapper");
        sysAccountMapper = (SysAccountMapper) ApplicationContextUtil.getBean("sysAccountMapper");
        sysAccountRoleMapper = (SysAccountRoleMapper) ApplicationContextUtil.getBean("sysAccountRoleMapper");
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

    public static ListData<User> queryUserList(String userName, String email, String phone, Integer stateCode,
                                               SecretLevelEnum secretLevelEnum, String pages, String rows) {
        UserDomain userDomain = new UserDomain();
        return userDomain.selectUserList(userName, email, phone, stateCode, secretLevelEnum, pages, rows);
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
        if (account.getStateCode() < 0) {
            logger.error("根据用户名 {} 查询账户已被冻结。", userName);
            throw new BusinessException("账户已被冻结");
        }
        return new User(account);
    }

    private ListData<User> selectUserList(String userName, String email, String phone, Integer stateCode,
                                          SecretLevelEnum secretLevelEnum, String pages, String rows) {
        SysAccountExample example = new SysAccountExample();
        SysAccountExample.Criteria criteria = example.createCriteria()
                .andBuiltInUserEqualTo(false);
        if (userName != null && !userName.isEmpty()) {
            criteria.andUserNameLike("%" + userName + "%");
        }
        if (email != null && !email.isEmpty()) {
            criteria.andEmailLike("%" + email + "%");
        }
        if (phone != null && !phone.isEmpty()) {
            criteria.andPhoneLike("%" + phone + "%");
        }
        if (stateCode != null) {
            criteria.andStateCodeEqualTo(stateCode);
        }
        if (secretLevelEnum != null) {
            criteria.andSecretLevelEqualTo(secretLevelEnum.getLevel());
        }
        Page<SysAccount> page = PageHelper.startPage(
                NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        sysAccountMapper.selectByExample(example);
        ListData<User> userListData = new ListData<>(page);
        List<User> users = new CopyOnWriteArrayList<>();
        for (SysAccount sysAccount : page.getResult()
        ) {
            users.add(new User(sysAccount));
        }
        userListData.setData(users);
        return userListData;
    }
}
