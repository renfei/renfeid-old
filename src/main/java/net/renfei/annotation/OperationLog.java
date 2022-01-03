package net.renfei.annotation;

import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;

import java.lang.annotation.*;

/**
 * 操作日志记录注解
 *
 * @author renfei
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    /**
     * 日志等级
     *
     * @return
     */
    LogLevelEnum leve() default LogLevelEnum.INFO;

    /**
     * 子系统类型
     *
     * @return
     */
    SystemTypeEnum module();

    /**
     * 操作类型
     *
     * @return
     */
    OperationTypeEnum operation() default OperationTypeEnum.RETRIEVE;

    /**
     * 详细信息
     *
     * @return
     */
    String desc() default "";
}
