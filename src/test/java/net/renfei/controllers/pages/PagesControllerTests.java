package net.renfei.controllers.pages;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class PagesControllerTests extends ApplicationTests {
    @Test
    public void getPageTest() throws Exception {
        this.mockMvc.perform(get("/page/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
