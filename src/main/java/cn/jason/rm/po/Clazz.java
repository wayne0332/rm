package cn.jason.rm.po;
// default package

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Clazz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "class"
		, catalog = "rm"
)

public class Clazz implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Subject subject;
	private Year year;
	private String name;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Student> students = new HashSet<Student>(0);

	// Constructors

	/**
	 * default constructor
	 */
	public Clazz()
	{
	}

	public Clazz(Integer id)
	{
		this.id = id;
	}

	/**
	 * minimal constructor
	 */
	public Clazz(String name)
	{
		this.name = name;
	}

	/**
	 * full constructor
	 */
	public Clazz(Subject subject, Year year, String name, Set<Task> tasks, Set<Student> students)
	{
		this.subject = subject;
		this.year = year;
		this.name = name;
		this.tasks = tasks;
		this.students = students;
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
	@JoinColumn(name = "subject_id")

	public Subject getSubject()
	{
		return this.subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "year_id")

	public Year getYear()
	{
		return this.year;
	}

	public void setYear(Year year)
	{
		this.year = year;
	}

	@Column(name = "name", nullable = false, length = 45)

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "clazzs")

	public Set<Task> getTasks()
	{
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks)
	{
		this.tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clazz")

	public Set<Student> getStudents()
	{
		return this.students;
	}

	public void setStudents(Set<Student> students)
	{
		this.students = students;
	}

}