package cn.jason.rm.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Resource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resource"
		, catalog = "rm"
)

public class Resource implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Share share;
	private String path;
	private String name;
	private Integer type;
	private Long size;

	// Constructors

	/**
	 * default constructor
	 */
	public Resource()
	{
	}

	/**
	 * minimal constructor
	 */
	public Resource(Share share, String path)
	{
		this.share = share;
		this.path = path;
	}

	/**
	 * full constructor
	 */
	public Resource(Share share, String path, String name, Integer type, Long size)
	{
		this.share = share;
		this.path = path;
		this.name = name;
		this.type = type;
		this.size = size;
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
	@JoinColumn(name = "share_id", nullable = false)

	public Share getShare()
	{
		return this.share;
	}

	public void setShare(Share share)
	{
		this.share = share;
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

	@Column(name = "name", length = 45)

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "type")

	public Integer getType()
	{
		return this.type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	@Column(name = "size")

	public Long getSize()
	{
		return this.size;
	}

	public void setSize(Long size)
	{
		this.size = size;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Resource))
		{
			return false;
		}

		Resource resource = (Resource) o;

		if (id != null ? !id.equals(resource.id) : resource.id != null)
		{
			return false;
		}
		if (name != null ? !name.equals(resource.name) : resource.name != null)
		{
			return false;
		}
		if (path != null ? !path.equals(resource.path) : resource.path != null)
		{
			return false;
		}
		if (share != null ? !share.equals(resource.share) : resource.share != null)
		{
			return false;
		}
		if (size != null ? !size.equals(resource.size) : resource.size != null)
		{
			return false;
		}
		if (type != null ? !type.equals(resource.type) : resource.type != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (share != null ? share.hashCode() : 0);
		result = 31 * result + (path != null ? path.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (size != null ? size.hashCode() : 0);
		return result;
	}
}