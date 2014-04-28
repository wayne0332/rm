package cn.jason.rm.controller;

import cn.cafebabe.websupport.util.AssembleUtil;
import cn.jason.rm.annotation.Auth;
import cn.jason.rm.annotation.FilterString2Null;
import cn.jason.rm.constant.Config;
import cn.jason.rm.exception.ControllerException;
import cn.jason.rm.po.Student;
import cn.jason.rm.po.Teacher;
import cn.jason.rm.pojo.UserBean;
import cn.jason.rm.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * Created by Jason Lin on 14-2-11.
 */
@RestController
@SessionAttributes({Config.ParamNames.STUDENT, Config.ParamNames.TEACHER})
public class MainController extends BaseController
{
	private final static Log log = LogFactory.getLog(MainController.class);

	@Resource
	private UserService userService;

	@RequestMapping({Config.Domain.Url.WELCOME, Config.Domain.Url.FAIL_WELCOME})
	public ModelAndView welcome()
	{
		return new ModelAndView(Config.Domain.Url.WELCOME);
	}

	//TODO 传参校验
	@RequestMapping(value = Config.Domain.Url.LOGIN, method = RequestMethod.POST)
	@FilterString2Null
	public ModelAndView login(UserBean userBean)
	{
		switch (userBean.getRole())
		{
			case STUDENT:
				Student student = null;
				if ((student = userService.studentLogin(AssembleUtil.assemble(userBean, new Student()))) != null)
				{
					return new ModelAndView(new RedirectView(Config.Domain.Url.BACK)).addObject(Config.ParamNames.TEACHER, new Teacher()).addObject(Config.ParamNames.STUDENT, student);
				}
				break;
			case TEACHER:
				Teacher teacher = null;
				if ((teacher = userService.teacherLogin(AssembleUtil.assemble(userBean, new Teacher()))) != null)
				{
					return new ModelAndView(new RedirectView(Config.Domain.Url.BACK)).addObject(Config.ParamNames.STUDENT, new Student()).addObject(Config.ParamNames.TEACHER, teacher);
				}
				break;
			default:
				throw new ControllerException("登陆时角色不对");
		}
		return new ModelAndView(new RedirectView(Config.Domain.Url.FAIL_WELCOME));
	}

	@Auth
	@RequestMapping(Config.Domain.Url.LOGOUT)
	public ModelAndView logout(SessionStatus sessionStatus)
	{
		sessionStatus.setComplete();
		return new ModelAndView(new RedirectView(Config.Domain.Url.WELCOME));
	}

	@RequestMapping(Config.Domain.Url.BACK)
	public ModelAndView back()
	{
//		synchronized (session)
//		{
//			Object visitHistoryQueue = session.getAttribute(Config.ParamNames.VISIT_HISTORY);
//			if (visitHistoryQueue != null && visitHistoryQueue instanceof LinkedList)
//			{
//				LinkedList<String> visitHistory = (LinkedList<String>) visitHistoryQueue;
//				Object currentUrlObject = session.getAttribute(Config.ParamNames.CURRENT_URL);
//				String currentUrl = currentUrlObject != null ? currentUrlObject.toString() : null, backUrl = null;
//				while ((backUrl = visitHistory.removeLast()) != null)
//				{
//					if (currentUrl == null || !backUrl.equals(currentUrl))
//					{
//						return new ModelAndView(new RedirectView(backUrl));
//					}
//				}
//			}
//		}
		if (isStudentLogin())
		{
			return new ModelAndView(new RedirectView(Config.Domain.STUDENT + Config.Domain.Url.MAIN));
		}
		else if (isTeacherLogin())
		{
			return new ModelAndView(new RedirectView(Config.Domain.TEACHER + Config.Domain.Url.MAIN));
		}
		else
		{
			return new ModelAndView(new RedirectView(Config.Domain.Url.LOGIN));
		}
	}

	@Override
	protected String domain()
	{
		return "";
	}
}
