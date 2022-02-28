package net.renfei.controller.weibo;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 微博栏目的单元测试
 *
 * @author renfei
 */
public class WeiboControllerTests extends ApplicationTests {
    @Test
    public void getWeiboListTest() throws Exception {
        this.mockMvc.perform(get("/weibo"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/weibo/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
        this.mockMvc.perform(get("/weibo?page=1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/weibo?page=abc"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getWeiboByIdTest() throws Exception {
        this.mockMvc.perform(get("/weibo/1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/weibo/1/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }
}
