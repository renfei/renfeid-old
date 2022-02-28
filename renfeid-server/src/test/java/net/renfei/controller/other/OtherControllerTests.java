package net.renfei.controller.other;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class OtherControllerTests extends ApplicationTests {
    @Test
    public void qrCodeTest() throws Exception {
        this.mockMvc.perform(get("/other/qrcode")
                        .param("content", "abc")
                        .param("size", "12"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/other/qrcode")
                        .param("content", "abc"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void urlRedirectTest() throws Exception {
        this.mockMvc.perform(get("/other/urlredirect"))
                .andDo(print())
                .andExpect(status().isFound());
        this.mockMvc.perform(get("/other/urlredirect?url=aHR0cHM6Ly93d3cucmVuZmVpLm5ldC8="))
                .andDo(print())
                .andExpect(status().isFound());
        this.mockMvc.perform(get("/other/urlredirect?url=aHR0cHM6Ly94aWFuaHVvLm9yZy8="))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
