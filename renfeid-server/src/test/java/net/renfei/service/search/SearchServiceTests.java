package net.renfei.service.search;

import net.renfei.ApplicationTests;
import net.renfei.services.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 搜索引擎测试
 *
 * @author renfei
 */
public class SearchServiceTests extends ApplicationTests {
    @Autowired
    private SearchService searchService;

    @Test
    public void createIndexTest() {
        searchService.deleteIndex();
        searchService.createIndex();
    }
}
