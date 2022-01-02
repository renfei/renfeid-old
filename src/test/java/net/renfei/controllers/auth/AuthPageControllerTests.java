package net.renfei.controllers.auth;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class AuthPageControllerTests extends ApplicationTests {
    @Test
    public void signInPageTest() throws Exception {
        this.mockMvc.perform(get("/auth/signIn"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
