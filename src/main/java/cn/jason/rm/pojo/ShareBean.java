package cn.jason.rm.pojo;

import cn.jason.rm.constant.ShareType;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-4
 */
public class ShareBean
{
	protected String title = null;
	protected String content = null;
	protected ShareType type = null;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public ShareType getType()
	{
		return type;
	}

	public void setType(ShareType type)
	{
		this.type = type;
	}
}
