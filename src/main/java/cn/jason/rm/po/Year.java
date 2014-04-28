package cn.jason.rm.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Year entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="year"
    ,catalog="rm"
)

public class Year  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Set<Clazz> clazzs = new HashSet<Clazz>(0);
     private Set<Student> students = new HashSet<Student>(0);
     private Set<Homework> homeworks = new HashSet<Homework>(0);


    // Constructors

    /** default constructor */
    public Year() {
    }

    
    /** full constructor */
    public Year(Set<Clazz> clazzs, Set<Student> students, Set<Homework> homeworks) {
        this.clazzs = clazzs;
        this.students = students;
        this.homeworks = homeworks;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="year")

    public Set<Clazz> getClazzs() {
        return this.clazzs;
    }
    
    public void setClazzs(Set<Clazz> clazzs) {
        this.clazzs = clazzs;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="year")

    public Set<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="year")

    public Set<Homework> getHomeworks() {
        return this.homeworks;
    }
    
    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }
   








}