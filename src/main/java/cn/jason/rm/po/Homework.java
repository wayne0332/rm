package cn.jason.rm.po;

import cn.jason.rm.constant.HomeworkStatus;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Homework entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homework"
		, catalog = "rm"
)

public class Homework implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Student student;
	private Year year;
	private Task task;
	private String remark;
	private String score;
	private HomeworkStatus status;
	private Timestamp editTime;
	private String path;
	private Integer semester;

	// Constructors

	/**
	 * default constructor
	 */
	public Homework()
	{
	}

	/**
	 * minimal constructor
	 */
	public Homework(Student student, Year year, Task task, HomeworkStatus status, Timestamp editTime, String path, Integer semester)
	{
		this.student = student;
		this.year = year;
		this.task = task;
		this.status = status;
		this.editTime = editTime;
		this.path = path;
		this.semester = semester;
	}

	/**
	 * full constructor
	 */
	public Homework(Student student, Year year, Task task, String remark, String score, HomeworkStatus status, Timestamp editTime, String path, Integer semester)
	{
		this.student = student;
		this.year = year;
		this.task = task;
		this.remark = remark;
		this.score = score;
		this.status = status;
		this.editTime = editTime;
		this.path = path;
		this.semester = semester;
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
	@JoinColumn(name = "student_number", nullable = false)

	public Student getStudent()
	{
		return this.student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "year_id", nullable = false)

	public Year getYear()
	{
		return this.year;
	}

	public void setYear(Year year)
	{
		this.year = year;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_id", nullable = false)

	public Task getTask()
	{
		return this.task;
	}

	public void setTask(Task task)
	{
		this.task = task;
	}

	@Column(name = "remark", length = 2000)

	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name = "score", length = 45)

	public String getScore()
	{
		return this.score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	@Column(name = "status", nullable = false)
	@Enumerated
	public HomeworkStatus getStatus()
	{
		return this.status;
	}

	public void setStatus(HomeworkStatus status)
	{
		this.status = status;
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

	@Column(name = "path", nullable = false, length = 200)

	public String getPath()
	{
		return this.path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	@Column(name = "semester", nullable = false)

	public Integer getSemester()
	{
		return this.semester;
	}

	public void setSemester(Integer semester)
	{
		this.semester = semester;
	}

}