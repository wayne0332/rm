package cn.jason.rm.controller;

import cn.cafebabe.websupport.service.BaseService;
import cn.jason.rm.annotation.Auth;
import cn.jason.rm.annotation.VisitHistory;
import cn.jason.rm.constant.Config;
import cn.jason.rm.constant.Role;
import cn.jason.rm.constant.ShareType;
import cn.jason.rm.constant.TaskStatus;
import cn.jason.rm.po.Teacher;
import cn.jason.rm.service.TaskService;
import cn.jason.rm.service.ShareService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Jason Lin on 14-2-11.
 */
@RestController
@SessionAttributes(value = {Config.ParamNames.TEACHER})
@RequestMapping(Config.Domain.TEACHER)
@Validated
@Auth(Role.TEACHER)
public class TeacherController extends BaseController
{
	private final static Log log = LogFactory.getLog(TeacherController.class);

	@Resource
	private TaskService taskService;

	@Resource
	private ShareService shareService;

	@RequestMapping(value = Config.Domain.Url.MAIN, method = RequestMethod.GET)
	@VisitHistory
	public ModelAndView main(Teacher teacher)
	{
		request.setAttribute(Config.ParamNames.SHARES, shareService.findAll(ShareType.ARTICLES, BaseService.DEFAULT_PAGE));
		request.setAttribute(Config.ParamNames.MY_TASKS, taskService.findAll(teacher, BaseService.DEFAULT_PAGE));
		request.setAttribute(Config.ParamNames.NEW_TASKS, taskService.findAll(teacher, BaseService.DEFAULT_PAGE, TaskStatus.NEW));
		request.setAttribute(Config.ParamNames.BEGIN_TASKS, taskService.findAll(teacher, BaseService.DEFAULT_PAGE, TaskStatus.STAR, TaskStatus.SUBMIT));
		request.setAttribute(Config.ParamNames.WAIT_CHECK_TASKS, taskService.findAll(teacher, BaseService.DEFAULT_PAGE, TaskStatus.CHECK));
		request.setAttribute(Config.ParamNames.FINISH_TASKS, taskService.findAll(teacher, BaseService.DEFAULT_PAGE, TaskStatus.END));
		return createModelAndView(Config.Domain.Url.MAIN);
	}

	@Override
	protected String domain()
	{
		return Config.Domain.TEACHER;
	}
}
