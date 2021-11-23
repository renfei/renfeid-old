package net.renfei.controllers;

import net.renfei.config.SystemConfig;
import net.renfei.domain.user.User;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller 基类
 *
 * @author renfei
 */
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
    public void modelAttribute(ModelAndView mv){
    }

    protected void noHandlerFoundException() throws NoHandlerFoundException {
        HttpHeaders headers = new HttpHeaders();
        throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
    }

    protected User getSignUser(){
        // TODO
        return null;
    }
}
