package com.hsr.yxw.ws.chat.common;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AliasName {
    String name();
}
