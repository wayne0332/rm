package cn.jason.rm.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="teacher"
    ,catalog="rm"
)

public class Teacher  implements java.io.Serializable {


    // Fields    

     private String number;
     private Subject subject;
     private String name;
     private String password;
     private Integer authLevel;
     private String email;
     private Set<Task> tasks = new HashSet<Task>(0);
     private Set<Student> students = new HashSet<Student>(0);
     private Set<Share> shares = new HashSet<Share>(0);


    // Constructors

    /** default constructor */
    public Teacher() {
    }

	/** minimal constructor */
    public Teacher(String name, String password, Integer authLevel) {
        this.name = name;
        this.password = password;
        this.authLevel = authLevel;
    }
    
    /** full constructor */
    public Teacher(Subject subject, String name, String password, Integer authLevel, String email, Set<Task> tasks, Set<Student> students, Set<Share> shares) {
        this.subject = subject;
        this.name = name;
        this.password = password;
        this.authLevel = authLevel;
        this.email = email;
        this.tasks = tasks;
        this.students = students;
        this.shares = shares;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="number", unique=true, nullable=false, length=45)

    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="subject_id")

    public Subject getSubject() {
        return this.subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    @Column(name="name", nullable=false, length=45)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="password", nullable=false, length=45)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="auth_level", nullable=false)

    public Integer getAuthLevel() {
        return this.authLevel;
    }
    
    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }
    
    @Column(name="email", length=45)

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="teacher")

    public Set<Task> getTasks() {
        return this.tasks;
    }
    
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="student_has_teacher", catalog="rm", joinColumns = { 
        @JoinColumn(name="teacher_number", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="student_number", nullable=false, updatable=false) })

    public Set<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="teacher")

    public Set<Share> getShares() {
        return this.shares;
    }
    
    public void setShares(Set<Share> shares) {
        this.shares = shares;
    }
   








}