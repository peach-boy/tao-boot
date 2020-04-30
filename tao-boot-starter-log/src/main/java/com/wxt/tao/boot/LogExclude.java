package com.wxt.tao.boot;

import java.lang.annotation.*;

/**
 * 排除打印注解
 *
 * @author xiantao.wu
 * @email xiantao.wu@guanaitong.com
 * @create 2020/4/3016:08
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogExclude {


}



