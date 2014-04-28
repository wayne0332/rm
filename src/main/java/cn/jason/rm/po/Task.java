package cn.jason.rm.po;

import cn.jason.rm.constant.TaskStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task"
		, catalog = "rm"
)

public class Task implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Teacher teacher;
	private Share share;
	private Timestamp editTime;
	private Date starTime;
	private Date endTime;
	private TaskStatus status;
	private Set<Clazz> clazzs = new HashSet<Clazz>(0);
	private Set<Homework> homeworks = new HashSet<Homework>(0);

	// Constructors

	/**
	 * default constructor
	 */
	public Task()
	{
	}

	public Task(Integer id)
	{
		this.id = id;
	}

	/**
	 * minimal constructor
	 */
	public Task(Teacher teacher, Share share, Timestamp editTime, TaskStatus status)
	{
		this.teacher = teacher;
		this.share = share;
		this.editTime = editTime;
		this.status = status;
	}

	/**
	 * full constructor
	 */
	public Task(Teacher teacher, Share share, Timestamp editTime, Timestamp starTime, Timestamp endTime, TaskStatus status, Set<Clazz> clazzs, Set<Homework> homeworks)
	{
		this.teacher = teacher;
		this.share = share;
		this.editTime = editTime;
		this.starTime = starTime;
		this.endTime = endTime;
		this.status = status;
		this.clazzs = clazzs;
		this.homeworks = homeworks;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_number", nullable = false)

	public Teacher getTeacher()
	{
		return this.teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "share_id", nullable = false)

	public Share getShare()
	{
		return this.share;
	}

	public void setShare(Share share)
	{
		this.share = share;
	}

	@Column(name = "edit_time", nullable = false, length = 19, insertable = false, updatable = false)

	public Timestamp getEditTime()
	{
		return this.editTime;
	}

	public void setEditTime(Timestamp editTime)
	{
		this.editTime = editTime;
	}

	@Column(name = "star_time", length = 19)

	public Date getStarTime()
	{
		return this.starTime;
	}

	public void setStarTime(Date starTime)
	{
		this.starTime = starTime;
	}

	@Column(name = "end_time", length = 19)

	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public TaskStatus getStatus()
	{
		return this.status;
	}

	public void setStatus(TaskStatus status)
	{
		this.status = status;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "task_of_class", catalog = "rm", joinColumns = {
			@JoinColumn(name = "task_id", nullable = false, updatable = false)}, inverseJoinColumns = {
			@JoinColumn(name = "class_id", nullable = false, updatable = false)})

	public Set<Clazz> getClazzs()
	{
		return this.clazzs;
	}

	public void setClazzs(Set<Clazz> clazzs)
	{
		this.clazzs = clazzs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "task")

	public Set<Homework> getHomeworks()
	{
		return this.homeworks;
	}

	public void setHomeworks(Set<Homework> homeworks)
	{
		this.homeworks = homeworks;
	}

}