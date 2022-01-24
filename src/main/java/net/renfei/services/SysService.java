package net.renfei.services;

import com.aliyun.oss.ServiceException;
import net.renfei.domain.user.User;
import net.renfei.exception.ForbiddenException;
import net.renfei.model.*;
import net.renfei.model.system.RegionVO;
import net.renfei.model.system.SysApi;
import net.renfei.repositories.model.SysRole;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 系统基础服务
 *
 * @author renfei
 */
public interface SysService {
    /**
     * 获取行政代码
     *
     * @param regionCode 行政代码
     * @return 下一级行政代码列表
     */
    List<RegionVO> getRegion(String regionCode);

    /**
     * 获取网站友情链接列表
     *
     * @return
     */
    List<LinkTree> getSysSiteFriendlyLinkList();

    /**
     * 【危险】在服务器主机上执行命令
     *
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    String execCmd(String cmd) throws IOException;

    /**
     * 【危险】在服务器主机上执行命令
     *
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    String execCmd(String[] cmd) throws IOException;

    Map<Integer, String> secretKey();

    Map<String, String> setSecretKey(ReportPublicKeyVO reportPublicKeyVO) throws ServiceException;

    String decrypt(String string, String keyId);

    PageHead getPageHead();

    PageHeader getPageHeader(HttpServletRequest request);

    PageFooter getPageFooter();

    /**
     * 系统接口列表
     * 用于给角色分配权限使用
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    ListData<SysApi> getSysApiList(User user, String pages, String rows);

    /**
     * 获取系统角色
     * 只能获取到登陆用户自己拥有的角色
     * 超管和安全管理员可以获取所有角色
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    ListData<SysRole> getSysRoleList(User user, String pages, String rows);

    /**
     * 添加系统角色
     * 只有超管和安全管理员可以添加角色
     *
     * @param user    登陆用户
     * @param sysRole 系统角色
     * @throws ForbiddenException 只有系统安全管理员才可以管理角色
     */
    void addSysRole(User user, SysRole sysRole) throws ForbiddenException;

    /**
     * 修改系统角色
     * 只有超管和安全管理员可以修改角色
     *
     * @param user    登陆用户
     * @param sysRole 系统角色
     * @throws ForbiddenException 只有系统安全管理员才可以管理角色
     */
    void updateSysRole(User user, SysRole sysRole) throws ForbiddenException;

    /**
     * 删除系统角色
     * 只有超管和安全管理员可以删除系统角色
     *
     * @param user 登陆用户
     * @param id   系统角色ID
     * @throws ForbiddenException 只有系统安全管理员才可以管理角色
     */
    void deleteSysRole(User user, Long id) throws ForbiddenException;
}
