package cn.jason.rm.service;

import cn.cafebabe.websupport.service.BaseService;
import cn.cafebabe.websupport.util.DateUtil;
import cn.cafebabe.websupport.util.FileUtil;
import cn.jason.rm.constant.Config;
import cn.jason.rm.po.Resource;
import cn.jason.rm.po.Teacher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-4
 */
@Service
@Validated
public class UploadService extends BaseService
{
	private final static Log log = LogFactory.getLog(ShareService.class);

	//TODO 文件名乱码
	@Transactional(propagation = Propagation.REQUIRED)
	public Resource saveFile(@NotNull Teacher teacher,@NotNull MultipartFile file)
	{
		Resource resource = new Resource();
		resource.setName(file.getOriginalFilename());
		resource.setPath(new StringBuilder().append(Config.Domain.UPLOAD).append(Config.Domain.TEACHER).append(Config.Domain.DIVIDE).append(teacher.getNumber()).append(Config.Domain.DIVIDE).append(DateUtil.getDate().getTime()).append(Config.Domain.DIVIDE).append(file.getOriginalFilename()).toString());
		resource.setSize(file.getSize());
		try (InputStream fileInputStream = file.getInputStream())
		{
			FileUtil.copyFile(fileInputStream, Config.findServerPath() + resource.getPath());
			return resource;
		}
		catch (Exception e)
		{
			log.error("上传失败", e);
			return null;
		}
	}

	public Resource getResourceById(@NotNull Integer resourceId)
	{
		return dao.findById(Resource.class, resourceId);
	}
}
