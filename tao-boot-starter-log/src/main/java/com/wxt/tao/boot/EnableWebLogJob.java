/*
 * Copyright 2007-2020, CIIC Guanaitong, Co., Ltd.
 * All rights reserved.
 */

package com.wxt.tao.boot;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebLogAutoConfiguration.class)
public @interface EnableWebLogJob {

}
