package top.jiakaic.blog.common.aop;

import java.lang.annotation.*;
import java.security.PrivateKey;

/**
 * @author JK
 * @date 2021/7/28 -9:28
 * @Description
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operation() default "";
}
