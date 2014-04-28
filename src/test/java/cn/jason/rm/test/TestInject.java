package cn.jason.rm.test;

import cn.jason.rm.test.service.TestInjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test.xml")
public class TestInject
{
	@Resource
	private TestInjectService testInjectService;

	@Test
	public void testInjectClass()
	{
		for (Class aClass : testInjectService.getaClass())
		{
			System.out.println(aClass);
		}
	}
}
