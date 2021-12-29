package net.renfei.controllers.api;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author renfei
 */
public class KitBoxApiControllerTests extends ApplicationTests {
    @Test
    public void getDomainDigTraceTest() throws Exception {
        this.mockMvc.perform(get("/api/dns/dig/www.renfei.net"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        this.mockMvc.perform(get("/api/dns/dig/www.renfei.net/A"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        this.mockMvc.perform(get("/api/dns/dig/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getDomainWhoisTest() throws Exception {
        this.mockMvc.perform(get("/api/dns/whois/renfei.net"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        this.mockMvc.perform(get("/api/dns/whois/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getUuid() throws Exception {
        this.mockMvc.perform(get("/api/uuid")
                        .param("quantity","5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        this.mockMvc.perform(get("/api/uuid")
                        .param("quantity","5")
                        .param("upperCase","false")
                        .param("hyphen","false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getContentByFreeMarkerAndBean() throws Exception {
        String html = "<html>\n" +
                "\t<body>\n" +
                "\t\t ${demo.text}\n" +
                "\t</body>\n" +
                "</html>";
        String beanJson = "{\n" +
                "\t\"demo\":{\n" +
                "\t\t\"text\":\"Hello World\"\n" +
                "\t}\n" +
                "}";
        this.mockMvc.perform(post("/api/freemarker/test")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("ftl", html)
                        .param("beanJson", beanJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
