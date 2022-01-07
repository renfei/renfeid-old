package net.renfei;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 启动类启动测试
 *
 * @author renfei
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StartUpApplicationTest {
    @Test
    public void test() {
        Application.main(new String[]{});
    }
}
