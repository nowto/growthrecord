package cn.edu.zzti.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.ForwardBean;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.service.KidService;
import cn.edu.zzti.util.LoginNameUtils;

/**
 * Kid孩子的控制器
 * 
 * @author nowto
 * 
 */
@Controller
public class KidController {
	@Resource
	private KidService kidService;

	/**
	 * 添加孩子的action
	 * 
	 * @return 添加视图
	 */
	@RequestMapping(value = "/addkid", method = RequestMethod.GET)
	public String getAddKid() {
		return "addkid";
	}

	/**
	 * 添加孩子的action
	 * 
	 * @return 已有孩子的视图
	 */
	@RequestMapping(value = "/addkid", method = RequestMethod.POST)
	public String postAddKid(Kid kid, Model model, HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		if (!kidService.add(kid, username)) {
			ForwardBean forward = new ForwardBean("addkid", "首页", "添加孩子");
			model.addAttribute(forward);
			return "forward";
		}
		return "redirect:morekid.do";
	}

	/**
	 * 删除孩子的action
	 * 
	 * @param id
	 *            孩子id
	 * @param writer
	 *            客户端输出流
	 */
	@RequestMapping(value = "/removekid", method = RequestMethod.GET)
	public void removekid(Integer id, PrintWriter writer) {
		if (kidService.remove(id)) {
			writer.write("true");
		} else {
			writer.write("false");
		}
	}

	/**
	 * 查看已有孩子
	 * 
	 * @param model
	 *            供添加查询结构的model
	 * @param session
	 *            会话对象，用户获取当前登录用户的username
	 * @return 视图
	 */
	@RequestMapping(value = "/morekid", method = RequestMethod.GET)
	public String getMoreKid(Model model, HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);

		List<Kid> kids = kidService.get(username);
		model.addAttribute("kids", kids);
		return "morekid";
	}

	/**
	 * 获取更新孩子的表单
	 * 
	 * @return 表单视图
	 */
	@RequestMapping(value = "/updatekid", method = RequestMethod.GET)
	public String getUpdatekid(Integer id, Model model) {
		Kid kid = kidService.get(id);
		if (kid != null) {
			model.addAttribute("kid", kid);
			return "updatekid";
		}
		return "morekid";
	}

	@RequestMapping(value = "/updatekid", method = RequestMethod.POST)
	public String postUpdateKid(Kid kid, Model model) {
		if (kidService.update(kid)) {
			model.addAttribute("result", "修改成功");
		} else {
			model.addAttribute("result", "修改失败");
		}
		model.addAttribute("kid", kid);
		return "updatekid";
	}

	public KidService getKidService() {
		return kidService;
	}

	public void setKidService(KidService kidService) {
		this.kidService = kidService;
	}
}
