package cn.edu.zzti.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查过滤器
 * @author nowto
 *
 */
public class LoginCheckFilter implements Filter {
	/**
	 * 以,分隔的需要放过的请求
	 */
	private String[] urls;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 执行过滤
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		
		String requestURI = request.getRequestURI();
		/**
		 * 检查请求的URL是否是不需要拦截的
		 */
		if(requestURI.endsWith(".js") || requestURI.endsWith(".css") || requestURI.endsWith(".html") || requestURI.endsWith(".jpg") || requestURI.endsWith(".png") || requestURI.endsWith(".gif")) {
			chain.doFilter(request, response);
			return;
		}
		
		for (String url : urls) {
			String throughURI = request.getContextPath() + "/" + url;
			if(requestURI.equals(throughURI)){
				chain.doFilter(req, resp);
				return;
			}
		}
		
//		if(request.get)
		if(request.getSession().getAttribute("username") != null && !request.getSession().getAttribute("username").equals("")){
			if(request.getServletPath().startsWith("/admin")){
				response.sendRedirect(request.getContextPath() + "/morekid.do");
				return;
			}
			chain.doFilter(request, response);
			return;
		}
		if(request.getSession().getAttribute("admin") != null && !request.getSession().getAttribute("admin").equals("")){
			if(request.getServletPath().startsWith("/admin")){
				chain.doFilter(request, response);
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/login.do");
				return;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/login.do");
	}

	/**
	 * 获取配置的througURl
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		urls = config.getInitParameter("througUrl").split(",");
	}

}
