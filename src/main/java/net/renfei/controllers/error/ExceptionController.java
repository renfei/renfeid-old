package net.renfei.controllers.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局捕获 Controller 的异常处理
 *
 * @author renfei
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionController {
}
