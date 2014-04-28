package cn.jason.rm.constant;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-6
 */
public enum ShareType implements PrintableEnum
{
	ARTICLES("文章"), TASK("作业");
	private final String name;

	ShareType(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}
}
