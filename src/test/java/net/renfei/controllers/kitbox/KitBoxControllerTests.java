package net.renfei.controllers.kitbox;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KitBoxControllerTests extends ApplicationTests {
    @Test
    public void kitBoxTest() throws Exception {
        this.mockMvc.perform(get("/kitbox"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/kitbox/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }

    @Test
    public void ipTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/ip"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
