package net.renfei;

import net.renfei.domain.UserDomain;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.system.UserDetail;
import net.renfei.utils.JacksonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    protected static final String SUPER_USER = "demo";
    protected static final String SYS_ADMIN = "sysa";
    protected static final String SYS_SSO = "sso";
    protected static final String SYS_AUD = "saa";
    protected static final String USER_NAME = "testunit";
    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    protected MockHttpSession session;

    @BeforeEach
    public void before() throws Exception {
//        ApplicationContextUtil.setApplicationContext(applicationContext);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        if (session == null) {
            // 走登陆接口
            session = new MockHttpSession();
            SignInVO signInVO = new SignInVO();
            signInVO.setKeyUuid("a3c2a92a-08ce-4598-80ef-55e0366b4484");
            signInVO.setUserName(SUPER_USER);
            signInVO.setPassword("QSZv+HMaiJLT6YFS8yZrlg==");
            signInVO.setUseVerCode(false);
            signInVO.settOtp("");
            this.mockMvc.perform(post("/-/api/auth/signIn")
                            .session(session)
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(JacksonUtil.obj2String(signInVO)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        }
        UserDetail userDetails = new UserDetail(new UserDomain("demo"));
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void getActive() throws Exception {
        this.mockMvc.perform(get("/api/server/dateTime"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}
