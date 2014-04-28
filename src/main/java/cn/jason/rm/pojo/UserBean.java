package cn.jason.rm.pojo;

import cn.jason.rm.constant.Role;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-5
 */
public class UserBean
{
	private Integer number = null;
	private String password = null;
	private Role role = null;

	public Integer getNumber()
	{
		return number;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}
}
