package cn.edu.zzti.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.InviteMapper;
import cn.edu.zzti.service.UserService;

public class NotifyNumFilter implements Filter{
	private UserService userService;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
//		
		int byInviteNotifyNum = getUserService().getByInviteNotifyNum(username);
		request.setAttribute("byInviteNotifyNum", byInviteNotifyNum);
		
		int vaccineNotifyNum = getUserService().getVaccineNotifyNum(username);
		int inviteNotifyNum = getUserService().getInviteNotifyNum(username);
		request.setAttribute("vaccineNotifyNum", vaccineNotifyNum);
		request.setAttribute("inviteNotifyNum", inviteNotifyNum);
		
		chain.doFilter(request, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		ServletContext context = arg0.getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(context);
		setUserService(appContext.getBean("userService", UserService.class));
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
