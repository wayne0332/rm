package cn.jason.rm.test;

import cn.jason.rm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestAsync {
	@Autowired
	private UserService userService;

	@Test
	public void test()
	{
	}
}
