package cn.jason.rm.test;

import cn.jason.rm.test.service.Jsr349Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-23
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring-config-test.xml"})
public class TestJsr349
{
	final static int TIMES = 100000;
	@Resource
	private Jsr349Service jsr349Service;

	@Test
	public void testValidator()
	{
		testSpeed();
	}

	private void testSpeed()
	{
		doLoop(new DoLoop()
		{
			@Override
			public void deal()
			{
				jsr349Service.test(null);
			}

			@Override
			public String name()
			{
				return "test1";
			}
		});
		doLoop(new DoLoop()
		{
			@Override
			public void deal()
			{
				jsr349Service.test2(null);
			}

			@Override
			public String name()
			{
				return "test2";
			}
		});
		doLoop(new DoLoop()
		{
			@Override
			public void deal()
			{
				jsr349Service.test3(null);
			}

			@Override
			public String name()
			{
				return "test3";
			}
		});
	}

	interface DoLoop
	{
		void deal();

		String name();
	}

	private void doLoop(DoLoop doLoop)
	{
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < TIMES; i++)
		{
			try
			{
				doLoop.deal();
			}
			catch (Exception e)
			{
				continue;
			}
		}
		System.out.println(System.currentTimeMillis() - beginTime + doLoop.name());
	}
}
