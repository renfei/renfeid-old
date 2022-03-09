package net.renfei.controller.error;

import net.renfei.exception.BusinessException;
import net.renfei.exception.ForbiddenException;
import net.renfei.exception.IP2LocationException;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.utils.SentryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * 全局捕获 RestController 的异常处理
 *
 * @author renfei
 */
@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionController.class);

    /**
     * BusinessException 异常处理
     *
     * @param exception BusinessException
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public final APIResult handleBusinessException(BusinessException exception, WebRequest request) {
        logger.warn(exception.getMessage(), exception);
        return APIResult.builder()
                .code(StateCodeEnum.Failure)
                .message(exception.getLocalizedMessage())
                .build();
    }

    /**
     * NeedU2FException 异常处理
     *
     * @param exception NeedU2FException
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NeedU2FException.class)
    public final APIResult handleBusinessException(NeedU2FException exception, WebRequest request) {
        logger.warn(exception.getMessage(), exception);
        return APIResult.builder()
                .code(StateCodeEnum.NeedTOTP)
                .message(exception.getLocalizedMessage())
                .build();
    }

    /**
     * IP2LocationException 异常处理
     *
     * @param exception IP2LocationException
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(IP2LocationException.class)
    public final APIResult handleIp2LocationException(IP2LocationException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return APIResult.builder()
                .code(StateCodeEnum.Failure)
                .message(exception.getLocalizedMessage())
                .build();
    }

    /**
     * ForbiddenException 异常处理
     *
     * @param exception ForbiddenException
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    public final APIResult handleForbiddenException(ForbiddenException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return APIResult.builder()
                .code(StateCodeEnum.Forbidden)
                .message(exception.getLocalizedMessage())
                .build();
    }

    /**
     * 其他异常处理
     *
     * @param exception
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public final APIResult handleAllExceptions(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        SentryUtils.captureException(exception);
        return APIResult.builder()
                .code(StateCodeEnum.Error)
                .message(exception.getLocalizedMessage())
                .build();
    }
}