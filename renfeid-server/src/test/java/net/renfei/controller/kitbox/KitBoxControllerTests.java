package net.renfei.controller.kitbox;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KitBoxControllerTests extends ApplicationTests {
    @Test
    public void kitBoxTest() throws Exception {
        this.mockMvc.perform(get("/kitbox"))
                .andDo(print())
                .andExpect(status().isOk());
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
    public void xpathTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/xpathTest"))
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
    public void getIcpTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/icp"))
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
    public void getCliEnvTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/clienv"))
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

    @Test
    public void portNumberListTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/portNumberList"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void randomPasswordTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/randomPassword"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void md5ToolsTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/md5"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shaToolsTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/sha1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/kitbox/sha256"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/kitbox/sha512"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void url16ToolsTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/url16"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void qrCodeTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/qrcode"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void plistTest() throws Exception {
        Map<String, Object> csrf = new HashMap<>();
        csrf.put("parameterName", "_csrf");
        csrf.put("token", "token");
        this.mockMvc.perform(get("/kitbox/plist")
                        .sessionAttr("_csrf", csrf))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(post("/kitbox/plist")
                        .with(csrf())
                        .param("appname", "任霏博客")
                        .param("version", "1.0.2")
                        .param("bundleid", "net.renfei.app")
                        .param("ipa", "https://cdn.renfei.net/app/renfei.ipa")
                        .param("icon", "https://cdn.renfei.net/app/icon.png")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void indexingToolsTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/indexing"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shortUrlToolsTest() throws Exception {
        this.mockMvc.perform(get("/kitbox/ShortUrl"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
