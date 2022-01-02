package net.renfei.controllers.api;

import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.utils.JacksonUtil;
import net.renfei.utils.RSAUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 认证接口单元测试
 *
 * @author renfei
 */
@Slf4j
public class AuthorizationApiControllerTests extends ApplicationTests {
    /**
     * 秘钥交换逻辑单元测试
     *
     * @throws Exception
     */
    @Test
    public void SecretKeyExchangeTest() throws Exception {
        // 向服务器申请服务器公钥
        String result = this.mockMvc.perform(get("/-/api/auth/secretKey"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200))
                .andReturn()
                .getResponse()
                .getContentAsString();
        APIResult apiResult = JacksonUtil.string2Obj(result, APIResult.class);
        // 得到服务器的公钥和公钥编号
        String keyUuid = apiResult.getMessage(),
                publicKey = apiResult.getData().toString();
        log.info("publicKey:" + publicKey);
        log.info("keyUuid:" + keyUuid);
        // 上报客户端公钥，并下发AES秘钥
        ReportPublicKeyVO reportPublicKey = new ReportPublicKeyVO();
        reportPublicKey.setSecretKeyId(keyUuid);
        // 生成客户端秘钥对
        Map<Integer, String> map = RSAUtils.genKeyPair(1024);
        // 使用服务器公钥加密客户端公钥
        reportPublicKey.setPublicKey(RSAUtils.encrypt(map.get(0), publicKey));
        result = this.mockMvc.perform(post("/-/api/auth/secretKey")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(reportPublicKey)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.code").value(200))
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info("result:" + result);
    }
}
