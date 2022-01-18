package net.renfei.services;

import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.account.UpdatePasswordVO;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.auth.SignUpActivationVO;
import net.renfei.model.auth.SignUpVO;
import net.renfei.repositories.model.SysAccount;
import net.renfei.utils.PasswordUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 账号服务
 *
 * @author renfei
 */
public interface AccountService {
    /**
     * 登陆
     *
     * @param signInVO 登陆请求对象
     * @param request  请求对象
     * @return
     */
    User signIn(SignInVO signInVO, HttpServletRequest request) throws BusinessException, NeedU2FException;

    /**
     * 注册
     *
     * @param signUpVO 注册请求对象
     * @param request  请求对象
     */
    void signUp(SignUpVO signUpVO, HttpServletRequest request) throws PasswordUtils.CannotPerformOperationException;

    /**
     * 账户激活
     *
     * @param signUpActivationVO
     */
    void activation(SignUpActivationVO signUpActivationVO);

    /**
     * 登出，返回Discuz的登出代码
     *
     * @param user
     * @return
     */
    String signOut(User user);

    SysAccount getAccountByUser(User user);

    SysAccount getAccountByEmail(String email);

    SysAccount getAccountByPhone(String phone);

    void updateAccount(SysAccount account);

    void updateAccountAll(SysAccount account);

    void updatePassword(SysAccount account, UpdatePasswordVO updatePasswordVO) throws PasswordUtils.CannotPerformOperationException;
}
