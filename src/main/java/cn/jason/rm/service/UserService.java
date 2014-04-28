package cn.jason.rm.service;

import cn.cafebabe.websupport.service.BaseService;
import cn.jason.rm.po.Student;
import cn.jason.rm.po.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-1
 */
@Service
@Validated
public class UserService extends BaseService
{
	public Student studentLogin(@NotNull Student student)
	{
		return getFist(dao.findByExample(student, ONE_RECORD));
	}

	public Teacher teacherLogin(@NotNull Teacher teacher)
	{
		return getFist(dao.findByExample(teacher, ONE_RECORD));
	}
}
