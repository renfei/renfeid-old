package net.renfei.controllers.error;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class ErrorControllerTests extends ApplicationTests {
    @Test
    public void errorPageTest() throws Exception {
        this.mockMvc.perform(get("/error/401"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
        this.mockMvc.perform(get("/error/403"))
                .andDo(print())
                .andExpect(status().isForbidden());
        this.mockMvc.perform(get("/error/404"))
                .andDo(print())
                .andExpect(status().isNotFound());
        this.mockMvc.perform(get("/error/405"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
        this.mockMvc.perform(get("/error/500"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
        // 404
        this.mockMvc.perform(get("/test/test/test/test"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
