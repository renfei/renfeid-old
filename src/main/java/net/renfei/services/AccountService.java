package net.renfei.services;

import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.auth.SignInVO;

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
     * 登出，返回Discuz的登出代码
     *
     * @param user
     * @return
     */
    String signOut(User user);
}
