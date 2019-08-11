package com.zhang.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorityFilter
 */

public class AuthorityFilter implements Filter {

	String filterPattern = "";
    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		Object object =session.getAttribute("adminName");
		String requestUri =req.getRequestURI();
		String path = requestUri.substring(req.getContextPath().length());
		
		if(path.matches(filterPattern)){
			if(object==null && !("/Login.jsp".equals(path)) 
					&& (!("/AdminLoginServlet".equals(path)))){
				//重定向操作
				resp.sendRedirect(req.getContextPath() + "/Login.jsp");
			}else{
				//控制权向后传递			
				chain.doFilter(request, response);
			}
		}else{
			//控制权向后传递			
			chain.doFilter(request, response);
		}		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterPattern = fConfig.getInitParameter("filterpattern");
	}

}
