package cn.jason.rm.constant;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-12
 */
public enum TaskStatus implements PrintableEnum
{
	NEW("新建"), STAR("开始"), SUBMIT("提交"), CHECK("批改"), END("结束");
	private final String name;

	TaskStatus(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}
}
