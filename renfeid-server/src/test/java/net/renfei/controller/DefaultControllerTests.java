package net.renfei.controller;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

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
                .andExpect(status().isOk());
    }

    @Test
    public void getGoogleAdsTest() throws Exception {
        this.mockMvc.perform(get("/ads.txt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSiteMapTest() throws Exception {
        this.mockMvc.perform(get("/sitemap.xml"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/sitemap.xsl"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getFeedTest() throws Exception {
        this.mockMvc.perform(get("/feed"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
