package net.renfei.controllers.test;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.model.APIResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 临时测试端点，用于各种临时测试和验证使用
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @GetMapping("active")
    public APIResult<String> getActive() {
        assert SYSTEM_CONFIG != null;
        return new APIResult<>(SYSTEM_CONFIG.getActive());
    }
}
