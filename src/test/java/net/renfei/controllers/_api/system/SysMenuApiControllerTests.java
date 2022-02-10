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
        // 测试普通用户的
        SignInVO signInVO = new SignInVO();
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
        this.mockMvc.perform(get("/_/api/sys/menu")
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
