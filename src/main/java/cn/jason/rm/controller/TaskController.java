package cn.jason.rm.controller;

import cn.cafebabe.websupport.util.AssembleUtil;
import cn.jason.rm.annotation.Auth;
import cn.jason.rm.annotation.FilterString2Null;
import cn.jason.rm.annotation.VisitHistory;
import cn.jason.rm.constant.Config;
import cn.jason.rm.constant.Role;
import cn.jason.rm.constant.ShareAccessLevel;
import cn.jason.rm.po.*;
import cn.jason.rm.pojo.HomeworkBean;
import cn.jason.rm.pojo.TaskBean;
import cn.jason.rm.service.ClassService;
import cn.jason.rm.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-8
 */
@RestController
@RequestMapping(Config.Domain.TASK)
@SessionAttributes({Config.ParamNames.TEACHER, Config.ParamNames.STUDENT})
@Auth
public class TaskController extends BaseController
{
	private final static Log log = LogFactory.getLog(TaskController.class);

	@Resource
	private ClassService classService;

	@Resource
	private TaskService taskService;

	@RequestMapping(Config.Domain.Url.ADD_INPUT)
	@Auth(Role.TEACHER)
	@VisitHistory
	public ModelAndView addInput(Teacher teacher)
	{
		request.setAttribute(Config.ParamNames.CLASS_MAP, mapKeyToString(classService.findBySubject(teacher.getSubject())));
		return createModelAndView(Config.Domain.Url.ADD_INPUT);
	}

	@RequestMapping(value = Config.Domain.Url.ADD, method = RequestMethod.POST)
	@Auth(Role.TEACHER)
	@FilterString2Null
	public ModelAndView add(TaskBean taskBean, Teacher teacher)
	{
		Share share = AssembleUtil.assemble(taskBean, new Share());
		share.setTeacher(teacher);
		share.setAccessLevel(ShareAccessLevel.PUBLIC);
		Task task = AssembleUtil.assemble(taskBean, new Task());
		task.setShare(share);
		task.setTeacher(teacher);
		taskService.add(task, taskBean.getClazzs(), findResources());
		findResources().clear();
		return new ModelAndView(new RedirectView(Config.Domain.TEACHER + Config.Domain.Url.MAIN));
	}

	@RequestMapping(Config.Domain.Url.SHOW + Config.Domain.Url.ID)
	public ModelAndView show(@PathVariable Integer id)
	{
		request.setAttribute(Config.ParamNames.TASK, taskService.find(id));
		return createModelAndView(Config.Domain.Url.SHOW);
	}

	@RequestMapping(Config.Domain.Url.DO_IT_INPUT + Config.Domain.Url.ID)
	@Auth(Role.STUDENT)
	public ModelAndView doItInput(@PathVariable Integer id, Student student)
	{
		Task task = taskService.find(id);
		request.setAttribute(Config.ParamNames.TASK, task);
		request.setAttribute(Config.ParamNames.HOMEWORK, taskService.findHomework(task, student));
		return createModelAndView(Config.Domain.Url.DO_IT_INPUT);
	}

	@RequestMapping(value = Config.Domain.Url.DO_IT, method = RequestMethod.POST)
	@Auth(Role.STUDENT)
	@FilterString2Null
	public ModelAndView doIt(@RequestParam MultipartFile file, HomeworkBean homeworkBean, Student student)
	{
		Homework homework = AssembleUtil.assemble(homeworkBean, new Homework());
		homework.setStudent(student);
		homework.setTask(new Task(homeworkBean.getTaskId()));
		taskService.addHomework(homework, file, student);
		return createRedirectModelAndView(Config.Domain.Url.SHOW + "/" + String.valueOf(homeworkBean.getTaskId()));
	}

	@RequestMapping(Config.Domain.Url.CHECK_INPUT + Config.Domain.Url.ID)
	@Auth(Role.TEACHER)
	public ModelAndView checkInput(@PathVariable Integer id)
	{
		Task task = taskService.findAndFetch(id);
		request.setAttribute(Config.ParamNames.TASK, task);
		request.setAttribute(Config.ParamNames.TOTAL_STUDENT_NUMBER, task.getClazzs().stream().mapToInt(clazz -> clazz.getStudents().size()).sum());
		Map<String, List<Homework>> homeworks = task.getHomeworks().stream().collect(Collectors.groupingBy(homework -> String.valueOf(homework.getStudent().getClazz().getYear().getId()) + "_" + homework.getStudent().getClazz().getName()));
		Map<String, List<Student>> notSubmits = taskService.findNotSubmit(id).stream().collect(Collectors.groupingBy(student -> student.getClazz().getYear().getId() + "_" + student.getClazz().getName()));
		request.setAttribute(Config.ParamNames.HOMEWORKS, homeworks);
		request.setAttribute(Config.ParamNames.NOT_SUBMITS, notSubmits);
		return createModelAndView(Config.Domain.Url.CHECK_INPUT);
	}

	@RequestMapping(Config.Domain.Url.CHECK_HOMEWORK + Config.Domain.Url.ID)
	@Auth(Role.TEACHER)
	public ModelAndView checkHomeworkInput(@PathVariable Integer id)
	{
		request.setAttribute(Config.ParamNames.HOMEWORK, taskService.findHomework(id));
		return createModelAndView(Config.Domain.Url.CHECK_HOMEWORK);
	}

	@RequestMapping(value = Config.Domain.Url.CHECK, method = RequestMethod.POST)
	@Auth(Role.TEACHER)
	public ModelAndView check(HomeworkBean homeworkBean)
	{
		taskService.check(AssembleUtil.assemble(homeworkBean, new Homework()));
		return createRedirectModelAndView(Config.Domain.Url.CHECK_INPUT + "/" + String.valueOf(homeworkBean.getTaskId()));
	}

	@Override
	protected String domain()
	{
		return Config.Domain.TASK;
	}
}
