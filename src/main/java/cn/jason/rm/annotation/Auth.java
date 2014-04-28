package cn.jason.rm.annotation;

import cn.jason.rm.constant.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-22
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth
{
	Role value() default Role.ANY;
}
