package cn.jason.rm.controller;

import cn.cafebabe.websupport.util.AssembleUtil;
import cn.jason.rm.annotation.Auth;
import cn.jason.rm.annotation.FilterString2Null;
import cn.jason.rm.annotation.VisitHistory;
import cn.jason.rm.constant.Config;
import cn.jason.rm.constant.Role;
import cn.jason.rm.constant.ShareAccessLevel;
import cn.jason.rm.po.Share;
import cn.jason.rm.po.Teacher;
import cn.jason.rm.pojo.ShareBean;
import cn.jason.rm.service.ShareService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-6
 */
@RestController
@RequestMapping(Config.Domain.SHARE)
@SessionAttributes({Config.ParamNames.TEACHER})
@Auth
public class ShareController extends BaseController
{
	@Resource
	private ShareService shareService;

	@Auth(Role.TEACHER)
	@RequestMapping(value = Config.Domain.Url.ADD_INPUT, method = RequestMethod.GET)
	@VisitHistory
	public ModelAndView addInput()
	{
		return createModelAndView(Config.Domain.Url.ADD_INPUT);
	}

	@Auth(Role.TEACHER)
	@RequestMapping(value = Config.Domain.Url.ADD, method = RequestMethod.POST)
	@FilterString2Null
	public ModelAndView add(ShareBean shareBean, Teacher teacher)
	{
		Share share = AssembleUtil.assemble(shareBean, new Share());
		share.setTeacher(teacher);
		share.setAccessLevel(ShareAccessLevel.PUBLIC);
		shareService.add(share, findResources());
		findResources().clear();
		return new ModelAndView(new RedirectView(Config.Domain.TEACHER + Config.Domain.Url.MAIN));
	}

	@RequestMapping(Config.Domain.Url.SHOW + Config.Domain.Url.ID)
	@VisitHistory
	public ModelAndView show(@PathVariable Integer id)
	{
		request.setAttribute(Config.ParamNames.SHARE, shareService.find(id));
		return createModelAndView(Config.Domain.Url.SHOW);
	}

	@Override
	protected String domain()
	{
		return Config.Domain.SHARE;
	}
}
