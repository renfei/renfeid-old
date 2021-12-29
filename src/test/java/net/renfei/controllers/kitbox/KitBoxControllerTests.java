package net.renfei.controllers.kitbox;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class KitBoxControllerTests extends ApplicationTests {
    @Test
    public void kitBoxTest() throws Exception {
        this.mockMvc.perform(get("/kitbox"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/kitbox/index.html"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }

    @Test
    public void ipTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/ip"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void freemarkerTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/freemarkerTest"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getDigTraceTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/digtrace"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getDnsQpsTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/dnsqps"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getWhoisTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/whois"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMyIpTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/getmyip"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void strHumpLineConvertTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/strHumpLineConvert"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void byteUnitConversionTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/byteUnitConversion"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void ueditorTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/ueditor"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/kitbox/ueditor/controller")
                        .param("action", "config"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/kitbox/ueditor/controller")
                        .param("action", "test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void wordIkAnalyzeTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/wordIkAnalyze"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
