package cn.jason.rm.test;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.sql.Select;
import org.junit.Test;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-15
 */
public class TestSqlBuilder
{
	@Test
	public void testBuildSql()
	{
		Select select = new Select(new MySQL5Dialect()).setSelectClause("count(c.id)").setFromClause("contract c, product p");
		System.out.println(select.toStatementString());
	}
}
