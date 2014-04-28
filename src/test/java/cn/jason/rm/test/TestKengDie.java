package cn.jason.rm.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-13
 */
public class TestKengDie
{
	static
	{
		try
		{
			Field f = Boolean.class.getDeclaredField("TRUE");

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);

			f.setAccessible(true);
			f.set(Boolean.class, Boolean.FALSE);

		}
		catch (Exception ex)
		{
		}

		try
		{
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);

			Class<?> cls = Class.forName("java.lang.Integer$IntegerCache");
			Field f = cls.getDeclaredField("cache");
			f.setAccessible(true);

			modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);

			Integer[] array = (Integer[]) f.get(cls);
			array[128 + 21] = new Integer(22);

		}
		catch (Exception ex)
		{
		}
		Field value = null;
	}

	static void youGuess(String s){
		try
		{
			Field value = String.class.getDeclaredField("value");
			value.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(value, value.getModifiers() & ~Modifier.FINAL);
			value.set(s, "高丽峰是sb".toCharArray());
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testChangeBuffer()
	{
		Boolean testBool = true;
		System.out.println(testBool);
		Integer integer = 21;
		System.out.println(integer);
		System.out.format("3 * 7 = %d\n", 21);
		if (Boolean.valueOf("true") == false)
		{
			System.out.println("true == false");
		}
		final String s = "fuck me";
		youGuess(s);
		System.out.println(s);
	}
}
