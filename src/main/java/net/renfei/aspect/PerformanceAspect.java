package net.renfei.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {
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

    @Pointcut(value = "execution(* net.renfei.*..*(..))")
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

            log.debug("===================");
            String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
            String MethodName = joinPoint.getSignature().getName();

            Object[] args = joinPoint.getArgs();
            int argsSize = args.length;
            String argsTypes = "";
            String typeStr = joinPoint.getSignature().getDeclaringType().toString().split(" ")[0];
            String returnType = joinPoint.getSignature().toString().split(" ")[0];
            log.debug("类/接口:" + tragetClassName + "(" + typeStr + ")");
            log.debug("方法:" + MethodName);
            log.debug("参数个数:" + argsSize);
            log.debug("返回类型:" + returnType);
            if (argsSize > 0) {
                // 拿到参数的类型
                for (Object object : args) {
                    if (object == null) {
                        argsTypes += "NULL";
                    } else {
                        argsTypes += object.getClass().getTypeName().toString() + " ";
                    }
                }
                log.debug("参数类型：" + argsTypes);
            }

            Long total = end - start;
            double timed = total / 1000000D;
            DecimalFormat df = new DecimalFormat("######0.000000");
            log.debug("耗时: " + df.format(timed) + " ms!");

            if (execCount.get().containsKey(MethodName)) {
                Long count = execCount.get().get(MethodName);
                //先移除，在增加
                execCount.get().remove(MethodName);
                execCount.get().put(MethodName, count + 1);

                count = execTime.get().get(MethodName);
                execTime.get().remove(MethodName);
                execTime.get().put(MethodName, count + total);
            } else {

                execCount.get().put(MethodName, 1L);
                execTime.get().put(MethodName, total);
            }
            if (tragetClassName.startsWith("net.renfei.controller.")) {
                // 到了 controller 基本就结束了，清空 ThreadLocal
                execCount.remove();
                execTime.remove();
                startTime.remove();
            }
            return result;

        } catch (Throwable e) {
            long end = System.nanoTime();
            log.debug("====around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
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
    @AfterReturning(returning = "obj", value = "execution(* net.renfei.controllers.*.*(..))")
    public void afterMehhod(JoinPoint jp, Object obj) {
        long end = System.nanoTime();
        long execTimeTotal = end - startTime.get();
        long execCountTotal = 0;
        String methodName = jp.getSignature().getName();
        for (Long time : execTime.get().values()
        ) {
            execTimeTotal += time;
        }
        for (Long count : execCount.get().values()) {
            execCountTotal += count;
        }
        double timed = execTimeTotal / 1000000000D;
        DecimalFormat df = new DecimalFormat("######0.000000");
        log.debug("连接点方法为：{},执行总耗时为：{}s,方法执行数量：{}", methodName, df.format(timed), execCountTotal);
        if (obj != null && obj.getClass().equals(ModelAndView.class)) {
            ModelAndView mv = (ModelAndView) obj;
            if (mv.getViewName() != null && (mv.getViewName().startsWith("redirect:") || mv.getViewName().startsWith("/searchitem"))) {
            } else {
                mv.addObject("performance_execTimeTotal", df.format(timed));
                mv.addObject("performance_execCountTotal", execCountTotal);
            }
        }
    }
}
