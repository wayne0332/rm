package cn.jason.rm.interceptor.controller;

import freemarker.ext.beans.BeansWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-17
 */
@Component
@Aspect
@Order(2)
public class EnumSupportInterceptor extends BaseControllerInterceptor
{
	@Around(ALL_METHOD_POINT_CUT)
	public Object after(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		Object modelAndView = proceedingJoinPoint.proceed();
		if (modelAndView != null && modelAndView instanceof ModelAndView) {
			((ModelAndView) modelAndView).addObject("enum", BeansWrapper.getDefaultInstance()
					.getStaticModels());
		}
		return modelAndView;
	}
}
