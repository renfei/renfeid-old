package net.renfei.controllers._api.system;

import net.renfei.ApplicationTests;
import net.renfei.model.auth.SignInVO;
import net.renfei.repositories.model.SysMenu;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author renfei
 */
public class SysMenuApiControllerTests extends ApplicationTests {
    @Test
    public void getMenuTreeByUserTest() throws Exception {
        this.mockMvc.perform(get("/_/api/sys/menu/tree")
                        .session(session)
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void sysMenuTest() throws Exception {
        this.mockMvc.perform(get("/_/api/sys/menu")
                        .session(session)
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuText("test");
        sysMenu.setMenuLink("/test");
        sysMenu.setParentId(1L);
        sysMenu.setOrderNumber(1);
        sysMenu.setNewWindow(false);
        this.mockMvc.perform(post("/_/api/sys/menu")
                        .with(csrf())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(sysMenu)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        sysMenu.setMenuText("test2");
        // 此处的 ID 5 是根据目前数据库是 4 自增而来的，如果不是注意修改
        this.mockMvc.perform(put("/_/api/sys/menu/5")
                        .with(csrf())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(sysMenu)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(delete("/_/api/sys/menu/5")
                        .with(csrf())
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        // 测试安全管理员的
        SignInVO signInVO = new SignInVO();
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
        this.mockMvc.perform(get("/_/api/sys/menu")
                        .session(session)
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
