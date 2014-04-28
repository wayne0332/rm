package cn.jason.rm.service;

import cn.cafebabe.websupport.service.BaseService;
import cn.jason.rm.po.Clazz;
import cn.jason.rm.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test.xml")
public class TaskServiceTest
{
	@Resource
	private TaskService taskService;

	@Test
	public void testFindAll() throws Exception
	{
		taskService.findAll(new Student(null, null, new Clazz(1), null, null, null, null, null), BaseService.DEFAULT_PAGE);
	}
}
