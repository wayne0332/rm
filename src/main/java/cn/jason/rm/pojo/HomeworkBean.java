package cn.jason.rm.pojo;

import cn.jason.rm.constant.HomeworkStatus;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-19
 */
public class HomeworkBean
{
	private Integer id = null;
	private Integer taskId = null;
	private String remark = null;
	private HomeworkStatus status = null;
	private String score = null;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public HomeworkStatus getStatus()
	{
		return status;
	}

	public void setStatus(HomeworkStatus status)
	{
		this.status = status;
	}

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}
}
