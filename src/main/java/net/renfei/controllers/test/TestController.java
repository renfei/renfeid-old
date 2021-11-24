package net.renfei.controllers.test;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
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

    @GetMapping("do")
    public String test() {
        return "ok.";
    }
}
