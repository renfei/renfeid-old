package net.renfei.controllers._api;

import net.renfei.ApplicationTests;
import net.renfei.model.system.QuartzJob;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author renfei
 */
public class QuartzServiceApiControllerTests extends ApplicationTests {

    @Test
    public void test() throws Exception {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobName("testJob");
        quartzJob.setJobGroup("testJobGroup");
        quartzJob.setReference("net.renfei.services.jobs.ExampleJob");
        quartzJob.setCron("0/5 * * * * ? ");
        this.mockMvc.perform(post("/_/api/quartz/job")
                        .with(csrf())
                        .content(JacksonUtil.obj2String(quartzJob))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(put("/_/api/quartz/job/pause")
                        .with(csrf())
                        .param("jobName", "testJob")
                        .param("jobGroup", "testJobGroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(put("/_/api/quartz/job/resume")
                        .with(csrf())
                        .param("jobName", "testJob")
                        .param("jobGroup", "testJobGroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(put("/_/api/quartz/job")
                        .with(csrf())
                        .param("jobName", "testJob")
                        .param("jobGroup", "testJobGroup")
                        .param("cron", "0/1 * * * * ? "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
