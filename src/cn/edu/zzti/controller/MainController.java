package cn.edu.zzti.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.InviteForm;
import cn.edu.zzti.dto.InviteCheckResult;
import cn.edu.zzti.dto.UserDto;
import cn.edu.zzti.dto.VaccineNotify;
import cn.edu.zzti.entity.Invite;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.service.UserService;
import cn.edu.zzti.util.LoginNameUtils;

/**
 * 每页都有的，头部页面的action
 * 
 * @author nowto
 * 
 */
@Controller
public class MainController {

	@Resource
	private UserService userService;

	/**
	 * 货物首页视图
	 * 
	 * @return 首页视图
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 *            会话对象，用户获取当前登录用户的username
	 * @return 登录视图
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		session.invalidate();
		return "redirect:login.do";
	}

	/**
	 * 查看个人资料
	 * 
	 * @param model
	 *            用户添加用户资料User对象
	 * @param session
	 *            获取当前登录用户username
	 * @return 个人资料视图
	 */
	@RequestMapping(value = "/personal")
	public String personal(Model model, HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);

		User user = userService.get(username);
		model.addAttribute(user);
		return "personal";
	}

	/**
	 * 用于更新个人资料的方法
	 * 
	 * @param user
	 *            表单提交的user对象
	 * @param model
	 *            用户封装更新后的user的map，同时包含提示信息
	 * @param session
	 *            用户获取username
	 * @return 个人资料视图
	 */
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public String postPersonal(User user, Model model, HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		if (username.equals(user.getUsername()) && userService.update(user)) {
			model.addAttribute("result", "修改成功");
			model.addAttribute("user", userService.get(username));
		} else {
			model.addAttribute("result", "修改失败");
		}
		return "personal";
	}

	/**
	 * 获取修改密码视图
	 * 
	 * @return 修改密码视图
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	public String getChangepwd() {
		return "changepwd";
	}

	/**
	 * 处理修改密码
	 * 
	 * @return 修改密码视图
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public String postChangepwd(@ModelAttribute("user") UserDto user,
			Model model, HttpSession session) {
		// 表单提交的 只有 密码，不包含用户名
		String username = LoginNameUtils.getLoginUsername(session);
		user.setUsername(username);

		if (userService.changepwd(user)) {
			model.addAttribute("result", "修改成功");
		} else {

			model.addAttribute("result", "修改失败");
		}

		return "changepwd";
	}

	/**
	 * 获取邀请用户 组为家庭的表单视图
	 * 
	 * @return 邀请视图
	 */
	@RequestMapping(value = "invite", method = RequestMethod.GET)
	public String getInvite() {
		return "invite";
	}

	/**
	 * 处理邀请请求
	 * @param invite 邀请封装表单
	 * @param model	供添加邀请结果信息
	 * @param session	获取登录用户名
	 * @return	邀请视图
	 */
	@RequestMapping(value = "invite", method = RequestMethod.POST)
	public String postInvite(InviteForm invite, Model model, HttpSession session) {
		String username = invite.getUsername();
		System.out.println(invite);
		String session_username = LoginNameUtils.getLoginUsername(session);
		InviteCheckResult result = userService.invite(invite, session_username);
		int status = result.getStatus();
		String str_result = null;
		
		switch (status) {
		case InviteCheckResult.INVITE_HOME:
			str_result = "您已和“" + result.getUser().getUsername() + "”用户组为了家庭";
			break;
		case InviteCheckResult.INVITE_NO_REPLY:
			str_result = "您邀请的“" + result.getUser().getUsername() + "”还未回复";
			break;
		case InviteCheckResult.INVITE_NOT_EXIST:
			str_result = "您邀请的“" + username + "”用户不存在";
			break;
		case InviteCheckResult.INVITE_OWN:
			str_result = "不能邀请自己";
			break;
		case InviteCheckResult.INVITE_SENTED:
			str_result = "已发出邀请";
			break;
		case InviteCheckResult.INVITE_TO_OTHER:
			str_result = "用户“" + username +"”已与别的用户组为家庭";
			break;
		case InviteCheckResult.INVITE_SAME_SEX:
			str_result = "同性用户“" + username +"”不能邀请";
			break;
		case InviteCheckResult.INVITE_HAVE_BYINVITED:
			str_result = "您已经被人邀请，请先处理通知";
			break;
		default:
			str_result = "未知错误";
			break;
		}
		model.addAttribute("result", str_result);
		return "invite";
	}

	
	/**
	 * 获取邀请人列表
	 * @param model 供添加邀请人
	 * @param session	获取登录用户
	 * @return 视图
	 */
	@RequestMapping(value="moreByInvite", method=RequestMethod.GET)
	public String getMoreByInvite(Model model, HttpSession session){
		String username = LoginNameUtils.getLoginUsername(session);
		List<Invite> list = userService.getByInviteNotify(username);
		model.addAttribute("invites", list);
		return "moreByInvite";
	}
	
	/**
	 * 获取疫苗提醒通知
	 * @param model 供添加通知
	 * @param session 获取登录用户
	 * @return 视图
	 */
	@RequestMapping(value="moreVaccinePlanNotify")
	public String getMoreVaccinePlanNotify(Model model, HttpSession session){
		String username = LoginNameUtils.getLoginUsername(session);
		List<VaccineNotify> list = userService.getVaccineNotify(username);
		model.addAttribute("vaccineNotifies", list);
		return "moreVaccinePlanNotify";
	}
	/**
	 * 同意某人的邀请
	 * @param id 邀请id
	 * @param model 供添加数据
	 * @param session 供获取登录用户
	 * @param request 供减少byInviteNotifyNum-1
	 * @return
	 */
	@RequestMapping(value="together", method=RequestMethod.GET)
	public void  together(Integer id, PrintWriter writer, HttpSession session, HttpServletRequest request){
		
		boolean result = userService.together(id);
		if(result){
			writer.write("true");
		}else {
			writer.write("false");
		}
	}
	
	/**
	 * 拒绝某人的邀请
	 * @param id 邀请id
	 * @param model 供添加数据
	 * @param session 供获取登录用户
	 * @param request 供减少byInviteNotifyNum-1
	 * @return
	 */
	@RequestMapping(value="refuse", method=RequestMethod.GET)
	public String  refuse(Integer id, Model model, HttpSession session, HttpServletRequest request){
		
		boolean result = userService.refuse(id);
		if(result){
			Integer byInviteNotifyNum = (Integer)request.getAttribute("byInviteNotifyNum") - 1;
			request.setAttribute("byInviteNotifyNum", byInviteNotifyNum);
			model.addAttribute("result", "拒绝成功");
			
		}else {
			model.addAttribute("result", "拒绝失败");
		}
		
		this.getMoreByInvite(model, session);
		
		return "moreByInvite";
	}
	
	/**
	 * 查看邀请后对方的回复
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/moreInviteReply", method=RequestMethod.GET)
	public String getMoreInviteReply(Model model, HttpSession session){
		String username = LoginNameUtils.getLoginUsername(session);
		
		List<Invite> invites = userService.getInviteNotify(username);
		model.addAttribute("invites", invites);
		return "moreInviteReply";
	}
	
	/**
	 * 邀请方 标记已读
	 * @return
	 */
	@RequestMapping(value="haveread.do", method=RequestMethod.GET)
	public String haveread(Integer id){
		userService.havereadInvite(id);
		return "redirect:moreInviteReply.do";
	}
	
	/**
	 * 查看爱人（配偶）
	 */
	@RequestMapping(value="morelover")
	public String moreLover(Model model, HttpSession session){
		String username = LoginNameUtils.getLoginUsername(session);
		User user = userService.getLover(username);
		model.addAttribute("lover", user);
		return "morelover";
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
