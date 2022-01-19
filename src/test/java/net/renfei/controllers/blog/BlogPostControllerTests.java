package net.renfei.controllers.blog;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 博客栏目的单元测试
 *
 * @author renfei
 */
public class BlogPostControllerTests extends ApplicationTests {

    @Test
    public void getPostListTest() throws Exception {
        this.mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts/tag/usual"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts/tag/usual"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/cat/posts/default"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
        this.mockMvc.perform(get("/posts/cat/default"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts/cat/default"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
        this.mockMvc.perform(get("/posts?page=1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts?page=abc"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPostByIdTest() throws Exception {
        this.mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/posts/1/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }
}
