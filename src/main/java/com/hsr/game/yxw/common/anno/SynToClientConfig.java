package com.hsr.game.yxw.common.anno;

import java.lang.annotation.*;

/**
 * 需要发送给客户端的配置
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SynToClientConfig {

    String name() default "";
}
