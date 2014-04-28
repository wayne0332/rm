package cn.jason.rm.po;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student"
		, catalog = "rm"
)

public class Student implements java.io.Serializable
{

	// Fields

	private String number;
	private Subject subject;
	private Year year;
	private Clazz clazz;
	private String name;
	private String password;
	private String email;
	private Set<Homework> homeworks = new HashSet<Homework>(0);
	private Set<Teacher> teachers = new HashSet<Teacher>(0);

	// Constructors

	/**
	 * default constructor
	 */
	public Student()
	{
	}

	/**
	 * minimal constructor
	 */
	public Student(String name, String password)
	{
		this.name = name;
		this.password = password;
	}

	/**
	 * full constructor
	 */
	public Student(Subject subject, Year year, Clazz clazz, String name, String password, String email, Set<Homework> homeworks, Set<Teacher> teachers)
	{
		this.subject = subject;
		this.year = year;
		this.clazz = clazz;
		this.name = name;
		this.password = password;
		this.email = email;
		this.homeworks = homeworks;
		this.teachers = teachers;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "number", unique = true, nullable = false, length = 45)

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "class_id")

	public Clazz getClazz()
	{
		return this.clazz;
	}

	public void setClazz(Clazz clazz)
	{
		this.clazz = clazz;
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

	@Column(name = "password", nullable = false, length = 45)

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "email", length = 45)

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")

	public Set<Homework> getHomeworks()
	{
		return this.homeworks;
	}

	public void setHomeworks(Set<Homework> homeworks)
	{
		this.homeworks = homeworks;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "students")

	public Set<Teacher> getTeachers()
	{
		return this.teachers;
	}

	public void setTeachers(Set<Teacher> teachers)
	{
		this.teachers = teachers;
	}

}