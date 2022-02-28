package net.renfei.controller.docs;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class DocsControllerTests extends ApplicationTests {

    @Test
    public void getDocsTest() throws Exception {
        this.mockMvc.perform(get("/docs"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/docs/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
        this.mockMvc.perform(get("/docs/Java/JavaSE/8u281/en"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
