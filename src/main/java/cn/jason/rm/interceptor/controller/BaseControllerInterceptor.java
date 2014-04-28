package cn.jason.rm.interceptor.controller;

import cn.jason.rm.controller.BaseController;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-9
 */
public class BaseControllerInterceptor extends BaseController
{
	protected static final String ALL_METHOD_POINT_CUT = "execution(public * cn.jason.rm.controller.*.*(..))";

	@Override
	protected String domain()
	{
		return "/";
	}
}
