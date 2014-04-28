package cn.jason.rm.service;

import cn.cafebabe.autodao.pojo.Page;
import cn.cafebabe.websupport.service.BaseService;
import cn.jason.rm.constant.ShareAccessLevel;
import cn.jason.rm.constant.ShareType;
import cn.jason.rm.po.Resource;
import cn.jason.rm.po.Share;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-4
 */
@Service
@Validated
public class ShareService extends BaseService
{
	private final static Log log = LogFactory.getLog(ShareService.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(@NotNull final Share share, @NotNull Set<Resource> resources)
	{
		dao.persist(share);
		resources.stream().peek(o -> o.setShare(share)).forEach(dao::persist);
	}

	public List<Share> findAll(@NotNull ShareType shareType, @NotNull Page page)
	{
		return dao.findByExample(new Share(null, null, null, ShareAccessLevel.PUBLIC, shareType), page, "editTime desc");
	}

	public Share find(@NotNull Integer shareId)
	{
		return dao.findById(Share.class, shareId);
	}
}
