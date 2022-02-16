package net.renfei.controllers._api.system;

import com.fasterxml.jackson.core.type.TypeReference;
import net.renfei.ApplicationTests;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.system.SysRoleVO;
import net.renfei.repositories.model.SysRolePermission;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 系统角色与权限接口测试
 *
 * @author renfei
 */
public class SysRoleApiControllerTests extends ApplicationTests {
    /**
     * 系统角色的增删查改测试
     *
     * @throws Exception
     */
    @Test
    public void roleTest() throws Exception {
        // 先测试超管的账号
        sysRoleTest();
        // 测试系统管理员的
        SignInVO signInVO = new SignInVO();
        signInVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
        signInVO.setUserName(SYS_ADMIN);
        signInVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
        signInVO.setUseVerCode(false);
        signInVO.settOtp("");
        this.mockMvc.perform(post("/-/api/auth/signIn")
                        .session(session)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(signInVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        sysRoleTest();
        // 测试安全管理员的
        signInVO = new SignInVO();
        signInVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
        signInVO.setUserName(SYS_SSO);
        signInVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
        signInVO.setUseVerCode(false);
        signInVO.settOtp("");
        this.mockMvc.perform(post("/-/api/auth/signIn")
                        .session(session)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(signInVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        sysRoleTest();
        // 测试安全审计员的
        signInVO = new SignInVO();
        signInVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
        signInVO.setUserName(SYS_AUD);
        signInVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
        signInVO.setUseVerCode(false);
        signInVO.settOtp("");
        this.mockMvc.perform(post("/-/api/auth/signIn")
                        .session(session)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(signInVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        sysRoleTest();
        // 测试普通用户的
        signInVO = new SignInVO();
        signInVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
        signInVO.setUserName(USER_NAME);
        signInVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
        signInVO.setUseVerCode(false);
        signInVO.settOtp("");
        this.mockMvc.perform(post("/-/api/auth/signIn")
                        .session(session)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(signInVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        sysRoleTest();
    }

    private void sysRoleTest() throws Exception {
        String testRoleZhName = "单元测试角色";
        String testRoleEnName = "UnitTestRole";
        SysRoleVO sysRole = new SysRoleVO();
        sysRole.setBuiltInRole(true);
        sysRole.setZhName(testRoleZhName);
        sysRole.setEnName(testRoleEnName);
        List<SysRolePermission> sysRolePermissionList = new ArrayList<>();
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setPermissionType("MENU");
        sysRolePermission.setPermissionId(1L);
        sysRolePermissionList.add(sysRolePermission);
        sysRole.setRolePermissions(sysRolePermissionList);
        this.mockMvc.perform(post("/_/api/sys/role")
                        .with(csrf())
                        .session(session)
                        .content(JacksonUtil.obj2String(sysRole))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        MockHttpServletResponse response = this.mockMvc.perform(get("/_/api/sys/role")
                        .session(session)
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200))
                .andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        APIResult<ListData<SysRoleVO>> apiResult = JacksonUtil.string2Obj(response.getContentAsString(), new TypeReference<APIResult<ListData<SysRoleVO>>>() {
            public int compareTo(TypeReference<APIResult<ListData<SysRoleVO>>> o) {
                return super.compareTo(o);
            }
        });
        for (SysRoleVO sysRoleData : apiResult.getData().getData()
        ) {
            if (sysRoleData.getZhName().equals(testRoleZhName)) {
                sysRole = sysRoleData;
                break;
            }
        }
        sysRole.setZhName("角色名称修改测试");
        this.mockMvc.perform(put("/_/api/sys/role/" + sysRole.getId())
                        .with(csrf())
                        .session(session)
                        .content(JacksonUtil.obj2String(sysRole))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(delete("/_/api/sys/role/" + sysRole.getId())
                        .with(csrf())
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}