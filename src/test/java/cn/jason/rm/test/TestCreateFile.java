package cn.jason.rm.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-6
 */
public class TestCreateFile
{
	@Test
	public void testCreateFile() throws UnsupportedEncodingException
	{
		System.out.println("测试");
		File file = null;
		try
		{
			file = new File(new String("/Users/Jason/test/测试.txt".getBytes(), "utf-8"));
			file = new File("/Users/Jason/test/测试.txt");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		File folder = new File("/Users/Jason/test/");
		for (File innerFile : folder.listFiles())
		{
			System.out.println(new String(innerFile.getName().getBytes("CP936"), "utf8"));
		}
	}
}