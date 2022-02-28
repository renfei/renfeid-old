package net.renfei.apisync;

import net.renfei.ApplicationTests;
import net.renfei.repositories.SysApiListMapper;
import net.renfei.repositories.model.SysApiList;
import net.renfei.repositories.model.SysApiListExample;
import net.renfei.utils.JacksonUtil;
import net.renfei.utils.ListUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * API接口同步
 * 注意这不是单元测试！
 *
 * @author renfei
 */
public class ApiSync extends ApplicationTests {
    @Autowired
    private SysApiListMapper sysApiListMapper;

    /**
     * 将代码中的API同步到数据库表
     * 注意这不是单元测试！
     * 方便管理员给用户角色授权API接口
     * 如果数据库中存在则会更新，如果不存在则会新建
     *
     * @throws Exception
     */
    @Test
    public void sync() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/v3/api-docs/InternalApi")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        String json = response.getContentAsString();
        Map apiDom = JacksonUtil.string2Obj(json, Map.class);
        apiDom = (Map) apiDom.get("paths");

        for (Object key : apiDom.keySet()
        ) {
            Map urlMap = (Map) apiDom.get(key);
            for (Object urlKey : urlMap.keySet()
            ) {
                Map method = (Map) urlMap.get(urlKey);
                String urlPath = key.toString();
                String methodName = urlKey.toString();
                String description = method.get("description") == null ? "[未知描述]" : method.get("description").toString();
                String summary = method.get("summary") == null ? "[未知概要]" : method.get("summary").toString();
                urlPath = urlPath.replaceAll("\\{[^\\}]+\\}", "*");
                // 查询是否存在
                SysApiListExample example = new SysApiListExample();
                example.createCriteria()
                        .andUrlPathEqualTo(urlPath)
                        .andMethodNameEqualTo(methodName);
                SysApiList sysApiList = ListUtils.getOne(sysApiListMapper.selectByExample(example));
                if (sysApiList == null) {
                    sysApiList = new SysApiList();
                    sysApiList.setUrlPath(urlPath);
                    sysApiList.setMethodName(methodName);
                    sysApiList.setSummary(summary);
                    sysApiList.setDescription(description);
                    sysApiListMapper.insert(sysApiList);
                } else {
                    sysApiList.setSummary(summary);
                    sysApiList.setDescription(description);
                    sysApiListMapper.updateByPrimaryKey(sysApiList);
                }
            }
        }
    }
}
