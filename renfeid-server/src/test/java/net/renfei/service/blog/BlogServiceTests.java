package net.renfei.service.blog;

import net.renfei.ApplicationTests;
import net.renfei.services.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author renfei
 */
public class BlogServiceTests extends ApplicationTests {
    @Autowired
    private BlogService blogService;

    @Test
    public void updatePageRankTest() {
        blogService.updatePageRank();
    }
}
