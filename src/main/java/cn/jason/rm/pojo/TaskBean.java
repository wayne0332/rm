package cn.jason.rm.pojo;

import cn.jason.rm.constant.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-8
 */
public class TaskBean extends ShareBean
{
	protected List<Integer> clazzs = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date starTime = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date endTime = null;
	protected TaskStatus status = null;

	public List<Integer> getClazzs()
	{
		return clazzs;
	}

	public void setClazzs(List<Integer> clazzs)
	{
		this.clazzs = clazzs;
	}

	public Date getStarTime()
	{
		return starTime;
	}

	public void setStarTime(Date starTime)
	{
		this.starTime = starTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public TaskStatus getStatus()
	{
		return status;
	}

	public void setStatus(TaskStatus status)
	{
		this.status = status;
	}
}
