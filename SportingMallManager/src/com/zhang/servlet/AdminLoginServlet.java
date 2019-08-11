package com.zhang.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhang.model.AuthorityUser;
import com.zhang.service.AuthorityService;
import com.zhang.service.impl.AuthorityServiceImpl;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("logout".equals(method)){
			Logout(request,response);
		}else{
			Login(request,response);
		}
	}
	
	//登录
	private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AuthorityService authorityService=new AuthorityServiceImpl();
		HttpSession session = request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		AuthorityUser au=new AuthorityUser();
		au.setAdminName(username);
		au.setAdminPwd(password);
		int count=authorityService.checkUser(au);
		if(count>0){
			session.setAttribute("adminName", username);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else{
			request.setAttribute("error", "账号或者密码错误!");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}		
	}
	
	//退出
	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//立即销毁session对象
		request.getSession().invalidate();
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}
}
