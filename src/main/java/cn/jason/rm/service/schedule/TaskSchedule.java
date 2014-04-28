package cn.jason.rm.service.schedule;

import cn.jason.rm.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-23
 */
@Service
public class TaskSchedule
{
	private static final Log log = LogFactory.getLog(TaskSchedule.class);
	@Resource
	private TaskService taskService;

	@Scheduled(cron = "0 0 */1 * * *")
	public void refreshBegin()
	{
		taskService.refreshBegin();
	}

	@Scheduled(cron = "1 0 0 * * *")
	public void refreshEnd()
	{
		taskService.refreshCheck();
	}
}
