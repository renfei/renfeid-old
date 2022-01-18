package net.renfei.controllers.api;

import net.renfei.ApplicationTests;
import net.renfei.model.account.UpdatePasswordVO;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author renfei
 */
public class AccountApiControllerTests extends ApplicationTests {
    @Test
    public void sendEmailVerCodeTest() throws Exception {
        this.mockMvc.perform(post("/-/api/account/manage/email/verCode")
                        .with(csrf())
                        .session(session)
                        .param("newEmail", "unittesting@163.com")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void updatePasswordTest() throws Exception {
        UpdatePasswordVO updatePasswordVO = new UpdatePasswordVO();
        updatePasswordVO.setKeyId("a3c2a92a-08ce-4598-80ef-55e0366b4484");
        updatePasswordVO.setOldPwd("QSZv+HMaiJLT6YFS8yZrlg==");
        updatePasswordVO.setNewPwd("QSZv+HMaiJLT6YFS8yZrlg==");
        this.mockMvc.perform(post("/-/api/account/manage/password")
                        .with(csrf())
                        .session(session)
                        .content(JacksonUtil.obj2String(updatePasswordVO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void updateFirstNameTest() throws Exception {
        this.mockMvc.perform(post("/-/api/account/manage/firstName")
                        .with(csrf())
                        .param("firstName", "123")
                        .param("lastName", "456")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
