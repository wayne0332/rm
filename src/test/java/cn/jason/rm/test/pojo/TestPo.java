package cn.jason.rm.test.pojo;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-23
 */
public class TestPo
{
	@NotNull
	@Min(1)
	private Integer id = null;

	@NotEmpty
	private String name = null;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
