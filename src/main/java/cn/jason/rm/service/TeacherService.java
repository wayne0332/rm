package cn.jason.rm.service;

import cn.cafebabe.websupport.service.BaseService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-22
 */
@Service
public class TeacherService extends BaseService
{
	public void test(@NotNull Object o)
	{
		System.out.println(o);
	}
}
