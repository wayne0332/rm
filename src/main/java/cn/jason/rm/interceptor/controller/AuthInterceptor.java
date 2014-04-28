package cn.jason.rm.interceptor.controller;

import cn.jason.rm.annotation.Auth;
import cn.jason.rm.constant.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-22
 */
@Component
@Aspect
@Order(10)
public class AuthInterceptor extends BaseControllerInterceptor
{
	private final static Log log = LogFactory.getLog(AuthInterceptor.class);

	@Around(ALL_METHOD_POINT_CUT)
	public Object authCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		Auth controllerAuth = proceedingJoinPoint.getTarget().getClass().getAnnotation(Auth.class);
		Auth actionAuth = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(Auth.class);
		Auth auth = actionAuth != null ? actionAuth : controllerAuth;
		if (auth != null)
		{
			switch (auth.value())
			{
				case STUDENT:
					if (!isStudentLogin())
					{
						return new ModelAndView(new RedirectView(Config.Domain.Url.WELCOME));
					}
					break;
				case TEACHER:
					if (!isTeacherLogin())
					{
						return new ModelAndView(new RedirectView(Config.Domain.Url.WELCOME));
					}
					break;
				case ANY:
					if (!isStudentLogin() && !isTeacherLogin())
					{
						return new ModelAndView(new RedirectView(Config.Domain.Url.WELCOME));
					}
					break;
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
