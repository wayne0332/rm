package cn.jason.rm.service;

import cn.cafebabe.websupport.service.BaseService;
import cn.cafebabe.websupport.util.CollectionUtil;
import cn.cafebabe.websupport.util.DateUtil;
import cn.jason.rm.constant.Config;
import cn.jason.rm.po.Clazz;
import cn.jason.rm.po.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-8
 */
@Service
@Validated
public class ClassService extends BaseService
{
	@SuppressWarnings(value = "unchecked")
	public Map<Integer, List<Clazz>> findBySubject(Subject subject)
	{
		Optional.of(subject).ifPresent(o -> Assert.notNull(o.getId()));
		List<Clazz> clazzs = (List<Clazz>) dao.executeHql("from Clazz where year.id in " + CollectionUtil.collectionToQuery(calculateInSchoolGrade()) + (subject == null ? "" : " and subject.id=" + subject.getId()));
		return clazzs.stream().collect(Collectors.groupingBy(o -> o.getYear().getId()));
	}

	private List<Integer> calculateInSchoolGrade()
	{
		return Stream.iterate(DateUtil.currentYear(), integer -> integer - 1).limit(Config.LENGTH_OF_SCHOOLING).collect(Collectors.toList());
	}
}
