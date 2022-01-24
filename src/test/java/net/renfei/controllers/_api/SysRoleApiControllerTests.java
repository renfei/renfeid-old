package net.renfei.controllers._api;

import com.fasterxml.jackson.core.type.TypeReference;
import net.renfei.ApplicationTests;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.repositories.model.SysRole;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 系统角色与权限接口测试
 *
 * @author renfei
 */
public class SysRoleApiControllerTests extends ApplicationTests {
    /**
     * 系统角色的增删查改测试
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        String testRoleName = "单元测试角色";
        SysRole sysRole = new SysRole();
        sysRole.setBuiltInRole(true);
        sysRole.setId(1L);
        sysRole.setZhName(testRoleName);
        this.mockMvc.perform(post("/_/api/sys/role")
                        .with(csrf())
                        .session(session)
                        .content(JacksonUtil.obj2String(sysRole))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        MockHttpServletResponse response = this.mockMvc.perform(get("/_/api/sys/role")
                        .session(session)
                        .param("pages", "1")
                        .param("rows", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200))
                .andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        APIResult<ListData<SysRole>> apiResult = JacksonUtil.string2Obj(response.getContentAsString(), new TypeReference<APIResult<ListData<SysRole>>>() {
            public int compareTo(TypeReference<APIResult<ListData<SysRole>>> o) {
                return super.compareTo(o);
            }
        });
        for (SysRole sysRoleData : apiResult.getData().getData()
        ) {
            if (sysRoleData.getZhName().equals(testRoleName)) {
                sysRole = sysRoleData;
                break;
            }
        }
        sysRole.setZhName("角色名称修改测试");
        this.mockMvc.perform(put("/_/api/sys/role/" + sysRole.getId())
                        .with(csrf())
                        .session(session)
                        .content(JacksonUtil.obj2String(sysRole))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
        this.mockMvc.perform(delete("/_/api/sys/role/" + sysRole.getId())
                        .with(csrf())
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200));
    }
}
