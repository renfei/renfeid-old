package net.renfei.utils;

import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renfei
 */
@Slf4j
public class JwtUtilsTests extends ApplicationTests {
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void test() {
        List<String> roles = new ArrayList<String>() {{
            this.add("testerRole");
        }};
        List<String> authorities = new ArrayList<String>() {{
            this.add("testerAuthorities");
        }};
        String jwt = jwtUtils.createJWT("tester", roles, authorities, "subject");
        log.info("createJwtTest: " + jwt);
        log.info("parseJWT: " + jwtUtils.parseJWT(jwt));
    }
}
