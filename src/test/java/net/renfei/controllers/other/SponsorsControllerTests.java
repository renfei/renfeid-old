package net.renfei.controllers.other;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class SponsorsControllerTests extends ApplicationTests {
    @Test
    public void sponsorsTest() throws Exception {
        this.mockMvc.perform(get("/sponsors"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/sponsors/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }
}
