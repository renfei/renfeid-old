package net.renfei.controllers;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.domain.user.User;
import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageHeader;
import net.renfei.model.PageView;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.SentryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Controller 基类
 *
 * @author renfei
 */
@Slf4j
public abstract class BaseController {
    protected final SystemConfig SYSTEM_CONFIG;
    @Autowired
    protected HttpServletRequest request;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    @ModelAttribute
    public void modelAttribute(ModelAndView mv) {
        mv.addObject("active", SYSTEM_CONFIG.getActive());
        mv.addObject("account", getSignUser());
    }

    protected void noHandlerFoundException() throws NoHandlerFoundException {
        HttpHeaders headers = new HttpHeaders();
        throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
    }

    protected User getSignUser() {
        // TODO
        return null;
    }

    /**
     * 构建一个通用的页面返回对象
     *
     * @param clazz  返回对象子类
     * @param object 数据负载对象
     * @param <T>    子类泛型
     * @return
     */
    protected <T extends PageView> T buildPageView(Class<T> clazz, Object object) {
        Constructor<T> constructor;
        T result;
        try {
            constructor = clazz.getDeclaredConstructor(Object.class);
            result = constructor.newInstance(object);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("构建通用的页面返回对象时出错", e);
            SentryUtils.captureException(e);
            return null;
        }
        result.setPageHead(new PageHead());
        result.setPageHeader(new PageHeader(null));
        result.setPageFooter(new PageFooter());
        return result;
    }
}
