package cn.jason.rm.service;

import cn.cafebabe.autodao.pojo.Page;
import cn.cafebabe.websupport.service.BaseService;
import cn.cafebabe.websupport.util.FileUtil;
import cn.jason.rm.constant.Config;
import cn.jason.rm.constant.TaskStatus;
import cn.jason.rm.po.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-12
 */
@Service
@Validated
public class TaskService extends BaseService
{
	private static final Log log = LogFactory.getLog(TaskService.class);

	@javax.annotation.Resource
	private ShareService shareService;

	@javax.annotation.Resource
	private UploadService uploadService;

	@Transactional
	public void add(@NotNull Task task, List<Integer> clazzs, @NotNull Set<Resource> resources)
	{
		shareService.add(task.getShare(), resources);
		task.setClazzs(clazzs.stream().map(Clazz::new).collect(Collectors.<Clazz>toSet()));
		dao.persist(task);
	}

	public List<Task> findAll(@NotNull Teacher teacher, @NotNull Page page)
	{
		return dao.findByExample(new Task(teacher, null, null, null), page, "editTime desc");
	}

	@SuppressWarnings(value = UNCHECKED)
	public List<Task> findAll(@NotNull Teacher teacher, @NotNull Page page, TaskStatus taskStatus)
	{
		String hql = "from Task t where t.teacher.number=? and t.status =? order by t.editTime desc";
		return (List<Task>) dao.executeHql(page, hql, teacher.getNumber(), taskStatus);
	}

	@SuppressWarnings(value = UNCHECKED)
	public List<Task> findAll(@NotNull Teacher teacher, @NotNull Page page, TaskStatus taskStatus_1, TaskStatus taskStatus_2)
	{
		String hql = "from Task t where t.teacher.number=? and (t.status =? or t.status=?) order by t.editTime desc";
		return (List<Task>) dao.executeHql(page, hql, teacher.getNumber(), taskStatus_1, taskStatus_2);
	}

	@SuppressWarnings(value = UNCHECKED)
	public List<Task> findAll(@NotNull Student student, @NotNull Page page)
	{
		String hql = "from Task t join t.clazzs c where c.id=? and t.starTime<? and t.endTime>? order by t.editTime desc";
		Date currentDate = new Date();
		return dao.executeHql(page, hql, student.getClazz().getId(), currentDate, currentDate).stream().map(o -> ((Task) ((Object[]) o)[0])).collect(Collectors.toList());
	}

	@SuppressWarnings(value = UNCHECKED)
	public List<Task> findFinish(@NotNull Student student, @NotNull Page page)
	{
		String hql = "from Task t join t.homeworks h where h.student.number=? and t.starTime<? and t.endTime>? order by t.editTime desc";
		Date currentDate = new Date();
		return dao.executeHql(page, hql, student.getNumber(), currentDate, currentDate).stream().map(o -> ((Task) ((Object[]) o)[0])).collect(Collectors.toList());
	}

	@SuppressWarnings(value = UNCHECKED)
	public List<Task> findNotFinish(@NotNull Student student, @NotNull Page page)
	{
		String hql = "from Task t join t.clazzs c where c.id=? and t.id not in (select t.id from Task t join t.homeworks h where h.student.number=?) and t.starTime<? and t.endTime>? order by t.editTime desc";
		Date currentDate = new Date();
		return dao.executeHql(page, hql, student.getClazz().getId(), student.getNumber(), currentDate, currentDate).stream().map(o -> ((Task) ((Object[]) o)[0])).collect(Collectors.toList());
	}

	public Task find(@NotNull Integer id)
	{
		return dao.findById(Task.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addHomework(Homework homework, MultipartFile file, Student student)
	{
		Assert.isTrue(!StringUtils.isEmpty(file.getOriginalFilename()));
		homework.setPath(new StringBuilder().append(Config.Domain.UPLOAD).append(Config.Domain.TASK).append(Config.Domain.DIVIDE).append(homework.getTask().getId()).append(Config.Domain.STUDENT).append(Config.Domain.DIVIDE).append(student.getNumber()).append(Config.Domain.DIVIDE).append(file.getOriginalFilename()).toString());
		try (InputStream inputStream = file.getInputStream())
		{
			FileUtil.copyFile(inputStream, Config.findServerPath() + homework.getPath());
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		if (homework.getId() == null)
		{
			dao.persist(homework);
			dao.findById(Task.class, homework.getTask().getId()).setStatus(TaskStatus.SUBMIT);
		}
		else
		{
			Homework persistHomework = findHomework(homework.getId());
			persistHomework.setPath(homework.getPath());
			persistHomework.setRemark(homework.getRemark());
		}
	}

	public Homework findHomework(Integer id)
	{
		return dao.findById(Homework.class, id);
	}

	public Homework findHomework(Task task, Student student)
	{
		String hql = "from Homework h join h.task t join h.student s where t.id=? and s.id=?";
		List<Homework> homeworks = dao.executeHql(hql, task.getId(), student.getNumber()).stream().map(o -> ((Homework) ((Object[]) o)[0])).collect(Collectors.toList());
		return homeworks.size() == 1 ? homeworks.get(0) : null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void refreshBegin()
	{
		String hql = "from Task t where t.starTime<? and t.status=?";
		((List<Task>) dao.executeHql(hql, new Date(), TaskStatus.NEW)).forEach(task -> task.setStatus(TaskStatus.STAR));
	}

	public void refreshCheck()
	{
		String hql = "from Task t where t.endTime<=? and (t.status=? or t.status=?)";
		((List<Task>) dao.executeHql(hql, new Date(), TaskStatus.STAR, TaskStatus.SUBMIT)).forEach(task -> task.setStatus(TaskStatus.CHECK));
	}

	public Task findAndFetch(Integer id)
	{
		String hql = "from Task t join fetch t.clazzs c join fetch c.students where t.id=?";
		return getFist((List<Task>)dao.executeHql(hql, id));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void check(Homework homework)
	{
		Homework persistHomework = dao.findById(Homework.class, homework.getId());
		persistHomework.setScore(homework.getScore());
		persistHomework.setStatus(homework.getStatus());
		updateTaskStatus(persistHomework);
	}

	private void updateTaskStatus(Homework persistHomework)
	{
		Task task = dao.findById(Task.class, persistHomework.getTask().getId());
		if (task.getHomeworks().stream().anyMatch(anyHomework -> anyHomework.getScore() != null))
		{
			task.setStatus(TaskStatus.END);
		}
	}

	@SuppressWarnings(value = UNCHECKED)
	public List<Student> findNotSubmit(Integer id)
	{
		String hql = "from Student ss join fetch ss.clazz cc join fetch cc.year where ss.id not in (select s.id from Student s join s.homeworks h where h.task.id=?)";
		return (List<Student>) dao.executeHql(hql, id);
	}
}
