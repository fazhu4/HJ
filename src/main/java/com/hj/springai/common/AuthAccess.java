package com.hj.springai.common;

import java.lang.annotation.*;

/**
 * @FileName AuthAccess
 * @Description
 * @Author fazhu
 * @date 2024-12-11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}
