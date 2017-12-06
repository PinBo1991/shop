package com.bjpowernode.cms.sys.param.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义标记：让有这个标记的方法被执行时 去调用参数初始化方法

@Target({ ElementType.METHOD }) // 作用的方法
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface InitParam {

}
