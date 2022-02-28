package net.renfei.controller.other;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class AboutControllerTests extends ApplicationTests {

    @Test
    public void getAboutTest() throws Exception {
        this.mockMvc.perform(get("/about"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/about/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }
}
