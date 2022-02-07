package net.renfei.controllers._api.account;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author renfei
 */
public class AccountManageApiControllerTests extends ApplicationTests {
    @Test
    public void queryAccountListTest() throws Exception {
        this.mockMvc.perform(get("/_/api/account")
                        .session(session)
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(get("/_/api/account")
                        .session(session)
                        .param("userName", "demo")
                        .param("email", "demo@renfei.net")
                        .param("stateCode", "1")
                        .param("secretLevel", "UNCLASSIFIED")
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(get("/_/api/account")
                        .session(session)
                        .param("userName", "demo")
                        .param("email", "demo@renfei.net")
                        .param("phone", "1")
                        .param("stateCode", "1")
                        .param("secretLevel", "UNCLASSIFIED")
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void settingAccountSecretLevelTest() throws Exception {
        this.mockMvc.perform(put("/_/api/account/demo/SecretLevel?SecretLevel=UNCLASSIFIED")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
