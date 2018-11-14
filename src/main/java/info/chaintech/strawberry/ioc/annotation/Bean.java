package info.chaintech.strawberry.ioc.annotation;

import java.lang.annotation.*;

/**
 * 声明 Bean 的注解
 *
 * Created by shniu on 2018/11/11.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    String value() default "";
}
