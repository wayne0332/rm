package cn.jason.rm.interceptor.controller;

import cn.jason.rm.annotation.VisitHistory;
import cn.jason.rm.constant.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-9
 */
//@Component
//@Aspect
//@Order(5)
@Deprecated
public class VisitHistoryInterceptor extends BaseControllerInterceptor
{
	private final static Log log = LogFactory.getLog(VisitHistoryInterceptor.class);

	@Around(ALL_METHOD_POINT_CUT)
	public Object visitHistory(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		VisitHistory visitHistoryTag = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(VisitHistory.class);
		if (visitHistoryTag != null)
		{
			session.setAttribute(Config.ParamNames.CURRENT_URL, request.getRequestURI());
			String requestUrl = request.getRequestURI();
			Object visitHistoryList = session.getAttribute(Config.ParamNames.VISIT_HISTORY);
			synchronized (session)
			{
				if (visitHistoryList != null && visitHistoryList instanceof ConcurrentLinkedQueue)
				{
					LinkedList<String> visitHistory = (LinkedList<String>) visitHistoryList;
					if (visitHistory.size() >= Config.VISIT_HISTORY_COUNT)
					{
						visitHistory.removeLast();
					}
					visitHistory.add(requestUrl);
				}
				else
				{
					LinkedList<String> visitHistory = new LinkedList<>();
					visitHistory.add(requestUrl);
					session.setAttribute(Config.ParamNames.VISIT_HISTORY, visitHistory);
				}
			}
		}
		else
		{
			session.setAttribute(Config.ParamNames.CURRENT_URL, null);
		}
		return proceedingJoinPoint.proceed();
	}
}
