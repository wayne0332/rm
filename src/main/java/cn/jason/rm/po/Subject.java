package cn.jason.rm.po;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Subject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="subject"
    ,catalog="rm"
)

public class Subject  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private College college;
     private String name;
     private Set<Student> students = new HashSet<Student>(0);
     private Set<Clazz> clazzs = new HashSet<Clazz>(0);
     private Set<Teacher> teachers = new HashSet<Teacher>(0);


    // Constructors

    /** default constructor */
    public Subject() {
    }

	/** minimal constructor */
    public Subject(College college, String name) {
        this.college = college;
        this.name = name;
    }
    
    /** full constructor */
    public Subject(College college, String name, Set<Student> students, Set<Clazz> clazzs, Set<Teacher> teachers) {
        this.college = college;
        this.name = name;
        this.students = students;
        this.clazzs = clazzs;
        this.teachers = teachers;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="college_id", nullable=false)

    public College getCollege() {
        return this.college;
    }
    
    public void setCollege(College college) {
        this.college = college;
    }
    
    @Column(name="name", nullable=false, length=45)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="subject")

    public Set<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="subject")

    public Set<Clazz> getClazzs() {
        return this.clazzs;
    }
    
    public void setClazzs(Set<Clazz> clazzs) {
        this.clazzs = clazzs;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="subject")

    public Set<Teacher> getTeachers() {
        return this.teachers;
    }
    
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
   








}