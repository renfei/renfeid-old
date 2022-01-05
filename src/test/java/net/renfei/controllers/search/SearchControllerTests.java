package net.renfei.controllers.search;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
public class SearchControllerTests extends ApplicationTests {

    @Test
    public void searchTest() throws Exception {
        this.mockMvc.perform(get("/search")
                        .param("type","ALL")
                        .param("w","测试"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSearchXmlTest() throws Exception {
        this.mockMvc.perform(get("/search/search.xml"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
