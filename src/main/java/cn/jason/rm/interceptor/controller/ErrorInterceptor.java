package cn.jason.rm.interceptor.controller;

import cn.jason.rm.constant.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-3
 */
@Component
@Aspect
@Order(1)
public class ErrorInterceptor extends BaseControllerInterceptor
{
	private final static Log log = LogFactory.getLog(ErrorInterceptor.class);

	@Around(ALL_METHOD_POINT_CUT)
	public Object errorCheck(ProceedingJoinPoint proceedingJoinPoint)
	{
		try
		{
			return proceedingJoinPoint.proceed();
		}
		catch (ConstraintViolationException validatorException)
		{
			for (ConstraintViolation<?> constraintViolation : validatorException.getConstraintViolations())
			{
				log.error(constraintViolation.getMessage());
			}
			log.error("校验错误", validatorException);
			return new ModelAndView(Config.Domain.Url.ERROR);
		}
		catch (Throwable throwable)
		{
			log.error(throwable.getMessage(), throwable);
			return new ModelAndView(Config.Domain.Url.ERROR);
		}
	}
}
