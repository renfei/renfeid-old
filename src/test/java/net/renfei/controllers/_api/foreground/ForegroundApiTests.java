package net.renfei.controllers._api.foreground;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 前台API测试
 *
 * @author renfei
 */
public class ForegroundApiTests extends ApplicationTests {

    @Test
    public void submitCommentsTest() throws Exception {
        this.mockMvc.perform(post("/_api/foreground/comments/BLOG/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("author", "UnitTest")
                        .param("email", "i@renfei.net")
                        .param("content", "UnitTest")
                        .param("reply", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void getPostInfoByPasswordTest() throws Exception {
        this.mockMvc.perform(post("/_api/foreground/blog/1/byPassword")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("testPassword"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void weiboThumbsUpTest() throws Exception {
        this.mockMvc.perform(post("/_api/foreground/weibo/1/thumbsUp")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void weiboThumbsDownTest() throws Exception {
        this.mockMvc.perform(post("/_api/foreground/weibo/1/thumbsDown")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
