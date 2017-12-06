package com.bjpowernode.cms.cms.home.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD }) // 目录注解可以作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 目录注解运行时有效
// 期望 在页面元素发生变化的方法上做这个标记
public @interface AutoStatic {

}
