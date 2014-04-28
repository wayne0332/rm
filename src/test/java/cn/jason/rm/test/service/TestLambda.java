package cn.jason.rm.test.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-14
 */
public class TestLambda
{
	@Test
	public void testStream()
	{
		List<Integer> list = Arrays.asList(1,2,3,4);
		list.stream().peek(o -> System.out.println("1   " + o.toString())).forEach(o ->  System.out.println("2   " + o.toString()));
	}
}
