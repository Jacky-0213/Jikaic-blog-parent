package top.jiakaic.blog.common.cache;

import java.lang.annotation.*;

/**
 * @author JK
 * @date 2021/7/28 -20:45
 * @Description
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    long expire() default 1 * 60 * 1000;

    String name() default "";
}
