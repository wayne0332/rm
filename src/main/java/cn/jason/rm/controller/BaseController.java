package cn.jason.rm.controller;

import cn.jason.rm.constant.Config;
import cn.jason.rm.po.Student;
import cn.jason.rm.po.Teacher;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-10
 */
public abstract class BaseController
{
	public static final String APPLICATION_JSON = "application/json";
	public static final String SUCCESS = "success";
	@Resource
	protected HttpSession session;

	@Resource
	protected HttpServletRequest request;

	public ModelAndView createModelAndView(String path)
	{
		return new ModelAndView(domain() + path);
	}

	public ModelAndView createRedirectModelAndView(String path)
	{
		return new ModelAndView(new RedirectView(domain() + path));
	}

	@SuppressWarnings(value = "unchecked")
	protected Set<cn.jason.rm.po.Resource> findResources()
	{
		if (session.getAttribute(Config.ParamNames.RESOURCES) == null)
		{
			session.setAttribute(Config.ParamNames.RESOURCES, new HashSet<>());
		}
		return (Set<cn.jason.rm.po.Resource>) session.getAttribute(Config.ParamNames.RESOURCES);
	}

	protected <T> Map<String, T> mapKeyToString(Map<?, T> map)
	{
		Map<String, T> result = new TreeMap<>();
		map.forEach((key, value) -> result.put(key.toString(), value));
		return result;
	}

	protected boolean isStudentLogin()
	{
		Student student = (Student) session.getAttribute(Config.ParamNames.STUDENT);
		return student != null && !StringUtils.isEmpty(student.getNumber());
	}

	protected boolean isTeacherLogin()
	{
		Teacher teacher = (Teacher) session.getAttribute(Config.ParamNames.TEACHER);
		return teacher != null && !StringUtils.isEmpty(teacher.getNumber());
	}

	protected abstract String domain();
}
