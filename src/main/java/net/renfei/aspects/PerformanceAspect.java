package net.renfei.aspects;

import net.renfei.model.PageView;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: ControllerInterceptor</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Aspect
@Component
public class PerformanceAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);
    /**
     * 开始时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * 记录方法执行次数
     */
    ThreadLocal<Map<String, Long>> execCount = new ThreadLocal<>();
    /**
     * 记录方法执行时间
     */
    ThreadLocal<Map<String, Long>> execTime = new ThreadLocal<>();

    @Pointcut(value = "execution(* net.renfei.*..*(..)) " +
            "&& !execution(* net.renfei.filter..*(..)) " +
            "&& !execution(* net.renfei.aspects..*(..)) " +
            "&& !execution(* net.renfei.config..*(..))")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //初始化 一次
        if (execCount.get() == null) {
            execCount.set(new HashMap<>());

        }
        if (execTime.get() == null) {
            execTime.set(new HashMap<>());
        }
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            if (result == null) {
                //如果切到了 没有返回类型的void方法，这里直接返回
                return null;
            }
            long end = System.nanoTime();
            String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Long total = end - start;
            if (execCount.get() != null) {
                if (execCount.get().containsKey(methodName)) {
                    Long count = execCount.get().get(methodName);
                    //先移除，在增加
                    execCount.get().remove(methodName);
                    execCount.get().put(methodName, count + 1);

                    count = execTime.get().get(methodName);
                    execTime.get().remove(methodName);
                    execTime.get().put(methodName, count + total);
                } else {
                    execCount.get().put(methodName, 1L);
                    execTime.get().put(methodName, total);
                }
            }
            if (tragetClassName.startsWith("net.renfei.controllers.")) {
                // 到了 controller 基本就结束了，清空 ThreadLocal
                execCount.remove();
                execTime.remove();
                startTime.remove();
            }
            return result;

        } catch (Throwable e) {
            long end = System.nanoTime();
            logger.error("====around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
                    + e.getMessage());
            throw e;
        }
    }

    /**
     * 对Controller下面的方法执行前进行切入，初始化开始时间
     *
     * @param jp
     */
    @Before(value = "execution(* net.renfei.controllers.*.*(..))")
    public void beforMehhod(JoinPoint jp) {
        startTime.set(System.nanoTime());
    }

    /**
     * 对Controller下面的方法执行后进行切入，统计方法执行的次数和耗时情况
     * 注意，这里的执行方法统计的数据不止包含Controller下面的方法，也包括环绕切入的所有方法的统计信息
     *
     * @param jp
     */
    @AfterReturning(returning = "obj", value = "execution(* net.renfei.controllers.*..*(..))")
    public void afterMehhod(JoinPoint jp, Object obj) {
        if (obj != null && obj.getClass().equals(ModelAndView.class)) {
            long end = System.nanoTime();
            long execTimeTotal = end - startTime.get();
            long execCountTotal = 0;
            for (Long time : execTime.get().values()
            ) {
                execTimeTotal += time;
            }
            for (Long count : execCount.get().values()) {
                execCountTotal += count;
            }
            double timed = execTimeTotal / 1000000000D;
            DecimalFormat df = new DecimalFormat("######0.000000");
            ModelAndView mv = (ModelAndView) obj;
            if (mv.getViewName() != null && (mv.getViewName().startsWith("redirect:") || mv.getViewName().startsWith("/searchitem"))) {
            } else {
                mv.addObject("performance_execTimeTotal", df.format(timed));
                mv.addObject("performance_execCountTotal", execCountTotal);
                Object object = mv.getModel().get("pageView");
                if (object instanceof PageView) {
                    PageView pageView = (PageView) object;
                    pageView.getPageFooter().setPerformanceExecTime(df.format(timed));
                    pageView.getPageFooter().setPerformanceExecCount(execCountTotal + "");
                }
            }
        }
    }
}
