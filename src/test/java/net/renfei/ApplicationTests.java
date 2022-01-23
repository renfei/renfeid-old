package net.renfei;

import net.renfei.model.auth.SignInVO;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    protected MockHttpSession session;

    @BeforeEach
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        if (session == null) {
//            // 走登陆接口
//            session = new MockHttpSession();
//            SignInVO signInVO = new SignInVO();
//            signInVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
//            signInVO.setUserName("demo");
//            signInVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
//            signInVO.setReCAPTCHAToken("03AGdBq26c-0yL1qQkAXinEUyCsN24-FMSKQFUzWe0VpxS0Uy4odaoaM-0j5_bSekK2RPleQp7mtrReoqh-JDBCCcziTOebXeQ7McgJYbsb4qQJFWJywBtJerDIdGqjojB91WHk5VfLWlLvL1I90rnDw_BIoKAdy4K60bnCvBGBF8W_vj9vLsn5cXSrF_fyYsSbb2OHS4H1TKbEKZXtyz8ByTm174RsOCItupc4JXRIeYGJUG41bhdwBnkIwNX4R9FdLes_0Ah7n_13W9b82fJ20001O2bOLTofcYXhf0jjQytdj0olW2HfCK4-sq2GLOn1GTWu0IIb870MuW3JWAByDc-jdoAeNEXQFL_E-feDfm9Y5aBgb_sbI0Id-Nn8wVOrgJZr1GEGuoTNshiV9v_yNQDfc3Gv5kof2FdphH_nkt9CqJXVVrheK1724ZFPt0ZVPu9iN0fJYGhBwLGTJhFvyuL6XVVdjEMjQ");
//            signInVO.setUseVerCode(false);
//            signInVO.settOtp("");
//            this.mockMvc.perform(post("/-/api/auth/signIn")
//                            .session(session)
//                            .with(csrf())
//                            .contentType(MediaType.APPLICATION_JSON_VALUE)
//                            .content(JacksonUtil.obj2String(signInVO)))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.code").value(200));
//        }
//        UserDetail userDetails = new UserDetail(new UserDomain("demo"));
//        UsernamePasswordAuthenticationToken
//                authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, null,
//                userDetails.getAuthorities()
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        ApplicationContextUtil.setApplicationContext(applicationContext);
    }

    @Test
    void getActive() throws Exception {
        this.mockMvc.perform(get("/api/server/dateTime"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}
