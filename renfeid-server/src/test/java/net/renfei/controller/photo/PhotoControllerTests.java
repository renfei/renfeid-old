package net.renfei.controller.photo;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PhotoControllerTests extends ApplicationTests {

    @Test
    public void getAllPhotoListTest() throws Exception {
        this.mockMvc.perform(get("/photo"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/photo/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
        this.mockMvc.perform(get("/photo?page=1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/photo?page=abc"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPhotoByIdTest() throws Exception {
        this.mockMvc.perform(get("/photo/7"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
