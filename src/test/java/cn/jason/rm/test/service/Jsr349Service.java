package cn.jason.rm.test.service;

import cn.cafebabe.autodao.util.Assert;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-24
 */
@Service
@Validated
public class Jsr349Service
{
	static int i = 0;

	public void test(@NotNull Object test)
	{
		doSomething();
	}

	public void test2(Object test)
	{
		doSomething();
	}

	public void test3(Object test)
	{
		Assert.notNull(test);
		doSomething();
	}

	public void testLenght(@Length(min = 3, max = 3) String test)
	{
		System.out.println(test);
	}

	private void doSomething()
	{
		if (true)
		{
			++i;
		}
	}
}
