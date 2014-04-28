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
import javax.persistence.UniqueConstraint;


/**
 * College entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="college"
    ,catalog="rm"
, uniqueConstraints = @UniqueConstraint(columnNames="name")
)

public class College  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Set<Subject> subjects = new HashSet<Subject>(0);


    // Constructors

    /** default constructor */
    public College() {
    }

	/** minimal constructor */
    public College(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public College(String name, Set<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
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
    
    @Column(name="name", unique=true, nullable=false, length=45)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="college")

    public Set<Subject> getSubjects() {
        return this.subjects;
    }
    
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
   








}