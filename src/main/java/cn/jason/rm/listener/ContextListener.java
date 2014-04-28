package cn.jason.rm.listener;

import cn.cafebabe.autodao.pojo.Page;
import cn.cafebabe.websupport.service.BaseService;
import cn.cafebabe.websupport.util.FileUtil;
import cn.cafebabe.websupport.util.ReflectUtil;
import cn.jason.rm.constant.Config;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-12
 */
@WebListener
public class ContextListener implements ServletContextListener
{
	private static final Log log = LogFactory.getLog(ContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		Page.setDefaultPageSize(10);
		BaseService.DEFAULT_PAGE.setPageSize(8);

		Set<Class<?>> enumSet = ReflectUtil.getClasses("cn.jason.rm.constant");
		File includeFile = FileUtil.createIfNotExist(Config.findServerPath()
				+ "/WEB-INF/web/includeEnum.ftl");
		try
		{
			if (!enumSet.isEmpty())
			{
				StringBuilder content = new StringBuilder();
				enumSet.stream().filter(Class::isEnum).forEach(clazz -> content.append("<#assign ").append(clazz.getSimpleName()).append(" = enum[\"").append(clazz.getName()).append("\"]>\r\n"));
				FileUtils.writeStringToFile(includeFile, content.toString(), "UTF8", false);
			}
		}
		catch (IOException e)
		{
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{

	}
}
