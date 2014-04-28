package cn.jason.rm.controller;

import cn.cafebabe.websupport.service.BaseService;
import cn.jason.rm.annotation.Auth;
import cn.jason.rm.annotation.VisitHistory;
import cn.jason.rm.constant.Config;
import cn.jason.rm.constant.Role;
import cn.jason.rm.constant.ShareType;
import cn.jason.rm.po.Student;
import cn.jason.rm.service.ShareService;
import cn.jason.rm.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@SessionAttributes(value = {Config.ParamNames.STUDENT})
@RequestMapping(Config.Domain.STUDENT)
@Auth(Role.STUDENT)
public class StudentController extends BaseController
{
	private final static Log log = LogFactory.getLog(StudentController.class);

	@Resource
	private ShareService shareService;

	@Resource
	private TaskService taskService;

	@RequestMapping(value = Config.Domain.Url.MAIN, method = RequestMethod.GET)
	@VisitHistory
	public ModelAndView main(Student student)
	{
		request.setAttribute(Config.ParamNames.SHARES, shareService.findAll(ShareType.ARTICLES, BaseService.DEFAULT_PAGE));
		request.setAttribute(Config.ParamNames.FINISH_TASKS, taskService.findFinish(student, BaseService.DEFAULT_PAGE));
		request.setAttribute(Config.ParamNames.NOT_FINISH_TASKS, taskService.findNotFinish(student, BaseService.DEFAULT_PAGE));
		return createModelAndView(Config.Domain.Url.MAIN);
	}

	@Override
	protected String domain()
	{
		return Config.Domain.STUDENT;
	}
}
