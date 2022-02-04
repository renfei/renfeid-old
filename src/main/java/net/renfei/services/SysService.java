package net.renfei.services;

import com.aliyun.oss.ServiceException;
import net.renfei.domain.user.User;
import net.renfei.exception.ForbiddenException;
import net.renfei.model.*;
import net.renfei.model.system.*;
import net.renfei.repositories.model.SysApiList;
import net.renfei.repositories.model.SysMenu;

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
     * 获取全部接口列表
     *
     * @return
     */
    List<SysApiList> getSysApiAllList();

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
    ListData<SysRoleVO> getSysRoleList(User user, String pages, String rows);

    /**
     * 添加系统角色
     * 只有超管和安全管理员可以添加角色
     *
     * @param user    登陆用户
     * @param sysRole 系统角色
     * @throws ForbiddenException 只有系统安全管理员才可以管理角色
     */
    void addSysRole(User user, SysRoleVO sysRole);

    /**
     * 修改系统角色
     * 只有超管和安全管理员可以修改角色
     *
     * @param user    登陆用户
     * @param sysRole 系统角色
     * @throws ForbiddenException 只有系统安全管理员才可以管理角色
     */
    void updateSysRole(User user, SysRoleVO sysRole);

    /**
     * 删除系统角色
     * 只有超管和安全管理员可以删除系统角色
     *
     * @param user 登陆用户
     * @param id   系统角色ID
     * @throws ForbiddenException 只有系统安全管理员才可以管理角色
     */
    void deleteSysRole(User user, Long id);

    /**
     * 根据用户获取用户的角色列表
     *
     * @param user 登陆用户
     * @return
     */
    List<SysRoleVO> getRoleListByUser(User user);

    /**
     * 根据API接口地址获取所需的角色列表
     *
     * @param sysApiList
     * @return
     */
    List<RoleDTO> getRoleDtoBySysApi(SysApiList sysApiList);

    /**
     * 根据用户获取菜单树
     *
     * @param user 登陆用户
     * @return
     */
    List<MenuDataItemVo> getMenuTreeByUser(User user);

    /**
     * 获取系统菜单列表
     * 超管和安全管理员可以获取全部，用分配权限，其他人只能获取到自己拥有的菜单
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    ListData<SysMenu> getSysMenuList(User user, String pages, String rows);

    /**
     * 添加系统菜单
     *
     * @param user    登陆用户
     * @param sysMenu 系统菜单
     */
    void addSysMenu(User user, SysMenu sysMenu);

    /**
     * 修改系统菜单
     *
     * @param user    登陆用户
     * @param sysMenu 系统菜单
     */
    void updateSysMenu(User user, SysMenu sysMenu);

    /**
     * 删除系统菜单
     * 下面的子菜单不会被删除，而是会断开树形链接
     *
     * @param user 登陆用户
     * @param id   删除的ID
     * @return
     */
    void deleteSysMenu(User user, Long id);
}
