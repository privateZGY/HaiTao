package com.automatic.AutomaticControl.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典注解
 * @author zgy
 * @since 2021-12-09
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
// 加上@interface就代表这是一个注解
public @interface Dict {

    /**
     * 字典表里面的code，不是字典明细表里面的code
     * @return
     */
    String dicCode();

    /**
     * 字典Text
     * @return
     */
    String dicText() default "";

    /**
     * 字典Table
     * @return
     */
    String dictTable() default "";

}
