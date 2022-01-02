package net.renfei.controllers.api;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
        this.mockMvc.perform(post("/-/api/comments/BLOG/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("author", "任霏")
                        .param("email", "unittesting@163.com")
                        .param("content", "这是一条评论消息")
                        .param("reply", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(post("/-/api/comments/BLOG/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("author", "UnitTest")
                        .param("content", "UnitTest")
                        .param("reply", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(100));
    }

    @Test
    public void getPostInfoByPasswordTest() throws Exception {
        this.mockMvc.perform(post("/-/api/blog/1/byPassword")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("testPassword"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void weiboThumbsUpTest() throws Exception {
        this.mockMvc.perform(post("/-/api/weibo/1/thumbsUp")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void weiboThumbsDownTest() throws Exception {
        this.mockMvc.perform(post("/-/api/weibo/1/thumbsDown")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
