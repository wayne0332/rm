/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package cn.jason.rm.annotation;

import javax.persistence.Inheritance;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 把方法参数中的空字符串""过滤为null
 * 
 * @author zhaolei
 * @created 2012-4-23
 * 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inheritance
public @interface FilterString2Null
{

}
