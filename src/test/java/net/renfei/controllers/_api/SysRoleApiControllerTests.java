package net.renfei.controllers._api;

import com.fasterxml.jackson.core.type.TypeReference;
import net.renfei.ApplicationTests;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.auth.SignInVO;
import net.renfei.repositories.model.SysRole;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

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
        signInVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
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
        signInVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
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
        signInVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
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
        signInVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
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
        String testRoleName = "单元测试角色";
        SysRole sysRole = new SysRole();
        sysRole.setBuiltInRole(true);
        sysRole.setId(1L);
        sysRole.setZhName(testRoleName);
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
        APIResult<ListData<SysRole>> apiResult = JacksonUtil.string2Obj(response.getContentAsString(), new TypeReference<APIResult<ListData<SysRole>>>() {
            public int compareTo(TypeReference<APIResult<ListData<SysRole>>> o) {
                return super.compareTo(o);
            }
        });
        for (SysRole sysRoleData : apiResult.getData().getData()
        ) {
            if (sysRoleData.getZhName().equals(testRoleName)) {
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
