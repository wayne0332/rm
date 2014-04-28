package cn.jason.rm.interceptor.controller;

import cn.cafebabe.websupport.util.ReflectUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-25
 */
@Aspect
@Order(20)
@Component
public class FilterStringInterceptor extends BaseControllerInterceptor
{
	private static final Log log = LogFactory.getLog(FilterStringInterceptor.class);
	/**
	 * 过滤参数.让参数中的空字符串("")变成null
	 *
	 * @param joinPoint
	 * @author zhaolei
	 * @created 2012-4-24
	 */
	@Before(ALL_METHOD_POINT_CUT + " && @annotation(cn.jason.rm.annotation.FilterString2Null)")
	public void filterArgs(JoinPoint joinPoint)
	{
		Object[] args = joinPoint.getArgs();
		if (args != null)
		{
			for (Object arg : args)
			{
				filterEmptyString2Null(arg);
			}
		}
	}

	/**
	 * 过滤空字符为null
	 *
	 * @param arg
	 * @author zhaolei
	 * @created 2012-6-1
	 */
	private void filterEmptyString2Null(Object arg)
	{
		if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse)
		{
			return;
		}
		if (arg != null && !(arg instanceof Enum))
		{
			if (arg.getClass().getPackage() != null
					&& arg.getClass().getPackage().getName().startsWith("com.sankuai.meituan"))
			{
				Method[] methods = ReflectUtil.getPublicGetMethods(arg.getClass());
				for (Method m : methods)
				{
					try
					{
						Object value = m.invoke(arg);
						if (value != null && value instanceof String && value.equals(""))
						{
							Method setMethod = ReflectUtil
									.getSetMethod4GetMethod(m, arg.getClass());
							if (setMethod != null)
							{
								setMethod.invoke(arg, new Object[]{null});
							}
						}
						else if (value != null)
						{
							filterEmptyString2Null(value);
						}
					}
					catch (Exception e)
					{
						log.error(e.getMessage(), e);
					}
				}
			}
			else if (arg instanceof String)
			{
				if (((String) arg).trim().equals(""))
				{
					arg = null;
				}
			}
		}
	}
}
