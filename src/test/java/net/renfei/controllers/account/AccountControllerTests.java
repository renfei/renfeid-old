package net.renfei.controllers.account;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class AccountControllerTests extends ApplicationTests {
    @Test
    public void managePageTest() throws Exception {
        this.mockMvc.perform(get("/account"))
                .andDo(print())
                .andExpect(status().isFound());
        this.mockMvc.perform(get("/account/manage")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void manageEmailPageTest() throws Exception {
        this.mockMvc.perform(get("/account/manage/email")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void managePhonePageTest() throws Exception {
        this.mockMvc.perform(get("/account/manage/phone")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
