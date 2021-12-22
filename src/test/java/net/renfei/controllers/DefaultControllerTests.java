package net.renfei.controllers;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 网站根目录的单元测试
 *
 * @author renfei
 */
public class DefaultControllerTests extends ApplicationTests {

    @Test
    public void indexTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }

    @Test
    public void getRobotsTxtTest() throws Exception {
        this.mockMvc.perform(get("/robots.txt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE));
    }

    @Test
    public void getGoogleAdsTest() throws Exception {
        this.mockMvc.perform(get("/ads.txt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE));
    }
}
