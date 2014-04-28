package cn.jason.rm.controller;

import cn.jason.rm.annotation.Auth;
import cn.jason.rm.annotation.FilterString2Null;
import cn.jason.rm.constant.Config;
import cn.jason.rm.constant.Role;
import cn.jason.rm.po.Homework;
import cn.jason.rm.po.Resource;
import cn.jason.rm.po.Teacher;
import cn.jason.rm.service.TaskService;
import cn.jason.rm.service.UploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-15
 */
@RestController
@RequestMapping(Config.Domain.UPLOAD)
@SessionAttributes({Config.ParamNames.TEACHER})
@Auth
public class UploadController extends BaseController
{
	private final static Log log = LogFactory.getLog(UploadController.class);

	@javax.annotation.Resource
	private UploadService uploadService;

	@javax.annotation.Resource
	private TaskService taskService;

	@RequestMapping({Config.Domain.Url.DOWNLOAD_SHARE + Config.Domain.Url.ID})
	public ResponseEntity<byte[]> shareDownload(@PathVariable Integer id)
	{
		Resource resource = uploadService.getResourceById(id);
		HttpHeaders headers = new HttpHeaders();
		if (resource != null)
		{
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", resource.getName());
			try
			{
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(Config.findServerPath() + resource.getPath())),
						headers, HttpStatus.CREATED);
			}
			catch (IOException e)
			{
				log.error("下载失败", e);
			}
		}
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "error.txt");
		return new ResponseEntity<byte[]>("下载失败.".getBytes(),
				headers, HttpStatus.OK);
	}

	@RequestMapping({Config.Domain.Url.DOWNLOAD_HOMEWORK + Config.Domain.Url.ID})
	@FilterString2Null
	public ResponseEntity<byte[]> homeworkDownload(@PathVariable Integer id)
	{
		Homework homework = taskService.findHomework(id);
		HttpHeaders headers = new HttpHeaders();
		if (homework != null)
		{
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", homework.getStudent().getName() + "." + StringUtils.split(homework.getPath(), '.')[1]);
			try
			{
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(Config.findServerPath() + homework.getPath())),
						headers, HttpStatus.CREATED);
			}
			catch (IOException e)
			{
				log.error("下载失败", e);
			}
		}
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "error.txt");
		return new ResponseEntity<byte[]>("下载失败.".getBytes(),
				headers, HttpStatus.OK);
	}

	@Auth(Role.TEACHER)
	@RequestMapping(value = Config.Domain.Url.UPLOAD, produces = APPLICATION_JSON)
	public Map<String, Object> upload(MultipartHttpServletRequest request, Teacher teacher)
			throws IOException
	{
		try
		{
			Set<Resource> resources = findResources();
			request.getFiles(Config.ParamNames.QQFILE).forEach(file -> resources.add(uploadService.saveFile(teacher, file)));
			session.setAttribute(Config.ParamNames.RESOURCES, resources);
		}
		catch (Exception e)
		{
			log.error("上传失败", e);
			return new ModelAndView().addObject(SUCCESS, false).getModel();
		}
		return new ModelAndView().addObject(SUCCESS, true).getModel();
	}

	@Override
	protected String domain()
	{
		return Config.Domain.UPLOAD;
	}
//	@Resource
//	private UploadService uploadService;
//
//	@RequestMapping(value = "/teacherUpload", produces = APPLICATION_JSON)
//	public Map<String, Object> uploadattachment(MultipartHttpServletRequest request, Teacher teacher)
//			throws IOException
//	{
//		try
//		{
//			List<MultipartFile> files = request.getFiles("qqfile");
//			for (MultipartFile file : files)
//			{
//				uploadService.saveFile(teacher, file);
//			}
//		} catch (Exception e)
//		{
//			log.error("上传失败", e);
//			return new ModelAndView().addObject("success", false).getModel();
//		}
//		return new ModelAndView().addObject("success", true).getModel();
//	}
//
//	@Override
//	protected String domain()
//	{
//		return Config.Domain.UPLOAD;
//	}

//	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
//	FileMeta fileMeta = null;
//
//	/**
//	 * ************************************************
//	 * URL: /rest/controller/upload
//	 * upload(): receives files
//	 *
//	 * @param request  : MultipartHttpServletRequest auto passed
//	 * @param response : HttpServletResponse auto passed
//	 * @return LinkedList<FileMeta> as json format
//	 * **************************************************
//	 */
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response)
//	{
//
//		//1. build an iterator
//		Iterator<String> itr = request.getFileNames();
//		MultipartFile mpf = null;
//
//		//2. get each file
//		while (itr.hasNext())
//		{
//
//			//2.1 get next MultipartFile
//			mpf = request.getFile(itr.next());
//			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());
//
//			//2.2 if files > 10 remove the first from the list
//			if (files.size() >= 10)
//				files.pop();
//
//			//2.3 create new fileMeta
//			fileMeta = new FileMeta();
//			fileMeta.setFileName(mpf.getOriginalFilename());
//			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
//			fileMeta.setFileType(mpf.getContentType());
//
//			try
//			{
//				fileMeta.setBytes(mpf.getBytes());
//
//				// copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
//				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("~/Downloads/upload/" + mpf.getOriginalFilename()));
//
//			}
//			catch (IOException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//2.4 add to files
//			files.add(fileMeta);
//		}
//		// result will be like this
//		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
//		return files;
//	}
//
//	/**
//	 * ************************************************
//	 * URL: /rest/controller/get/{value}
//	 * get(): get file as an attachment
//	 *
//	 * @param response : passed by the server
//	 * @param value    : value from the URL
//	 * @return void
//	 * **************************************************
//	 */
//	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
//	public void get(HttpServletResponse response, @PathVariable String value)
//	{
//		FileMeta getFile = files.get(Integer.parseInt(value));
//		try
//		{
//			response.setContentType(getFile.getFileType());
//			response.setHeader("Content-disposition", "attachment; filename=\"" + getFile.getFileName() + "\"");
//			FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
//		}
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
