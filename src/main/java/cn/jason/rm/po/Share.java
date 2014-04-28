package cn.jason.rm.po;

import cn.jason.rm.constant.ShareAccessLevel;
import cn.jason.rm.constant.ShareType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Share entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share"
		, catalog = "rm"
)

public class Share implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Teacher teacher;
	private String title;
	private String content;
	private Timestamp editTime;
	private ShareAccessLevel accessLevel;
	private ShareType type;
	private Set<Resource> resources = new HashSet<Resource>(0);
	private Set<Task> tasks = new HashSet<Task>(0);

	// Constructors

	/**
	 * default constructor
	 */
	public Share()
	{
	}

	/**
	 * minimal constructor
	 */
	public Share(Teacher teacher, String title, Timestamp editTime, ShareAccessLevel accessLevel, ShareType type)
	{
		this.teacher = teacher;
		this.title = title;
		this.editTime = editTime;
		this.accessLevel = accessLevel;
		this.type = type;
	}

	/**
	 * full constructor
	 */
	public Share(Teacher teacher, String title, String content, Timestamp editTime, ShareAccessLevel accessLevel, ShareType type, Set<Resource> resources, Set<Task> tasks)
	{
		this.teacher = teacher;
		this.title = title;
		this.content = content;
		this.editTime = editTime;
		this.accessLevel = accessLevel;
		this.type = type;
		this.resources = resources;
		this.tasks = tasks;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacher_number", nullable = false)

	public Teacher getTeacher()
	{
		return this.teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	@Column(name = "title", nullable = false, length = 45)

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Column(name = "content", length = 200)

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
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

	@Column(name = "access_level", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public ShareAccessLevel getAccessLevel()
	{
		return this.accessLevel;
	}

	public void setAccessLevel(ShareAccessLevel accessLevel)
	{
		this.accessLevel = accessLevel;
	}

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public ShareType getType()
	{
		return this.type;
	}

	public void setType(ShareType type)
	{
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "share")

	public Set<Resource> getResources()
	{
		return this.resources;
	}

	public void setResources(Set<Resource> resources)
	{
		this.resources = resources;
	}

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "share")

	public Set<Task> getTasks()
	{
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks)
	{
		this.tasks = tasks;
	}

	@Override
	public String toString()
	{
		return "Share{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", editTime=" + editTime +
				", accessLevel=" + accessLevel +
				", type=" + type +
				'}';
	}
}