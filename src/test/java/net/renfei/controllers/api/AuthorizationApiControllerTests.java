package net.renfei.controllers.api;

import net.renfei.ApplicationTests;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.auth.SignUpActivationVO;
import net.renfei.model.auth.SignUpVO;
import net.renfei.utils.JacksonUtil;
import net.renfei.utils.RSAUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

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
public class AuthorizationApiControllerTests extends ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationApiControllerTests.class);

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
        logger.info("publicKey:" + publicKey);
        logger.info("keyUuid:" + keyUuid);
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
        logger.info("result:" + result);
    }

    @Test
    public void doSignUpTest() throws Exception {
        SignUpVO signUpVO = new SignUpVO();
        signUpVO.setUserName("unittest");
        signUpVO.setEmail("unittesting@163.com");
        signUpVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
        signUpVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
        signUpVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
        this.mockMvc.perform(post("/-/api/auth/signUp")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(signUpVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void doSignUpActivationTest() throws Exception {
        SignUpActivationVO signUpActivationVO = new SignUpActivationVO();
        signUpActivationVO.setEmailOrPhone("i@renfei.net");
        signUpActivationVO.setCode("1234");
        signUpActivationVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
        this.mockMvc.perform(post("/-/api/auth/signUp/activation")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JacksonUtil.obj2String(signUpActivationVO)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
