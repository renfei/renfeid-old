package net.renfei.service.leaf;

import net.renfei.config.SystemConfig;
import net.renfei.services.LeafService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author renfei
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeafServiceTests {
    @Autowired
    private LeafService leafService;
    @Autowired
    private SystemConfig SYSTEM_CONFIG;

    @Test
    public void getId() {
        System.out.println("leafService.getId: " + leafService.getId());
        System.out.println("leafService.getId(key): " + leafService.getId(SYSTEM_CONFIG.getLeafKey()));
    }
}
