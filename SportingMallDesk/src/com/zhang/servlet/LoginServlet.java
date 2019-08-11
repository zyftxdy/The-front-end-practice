package com.zhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.User;
import com.zhang.service.UserService;
import com.zhang.serviceImpl.UserServiceImpl;
import com.zhang.util.MD5Util;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//编码
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("CheckUser".equals(method)){
			CheckUser(request,response);
		}else if("addUser".equals(method)){
			addUser(request,response);
		}else if("checkCode".equals(method)){
			checkCode(request,response);
		}else if("checkUserName".equals(method)){
			checkUserName(request,response);
		}else if("logout".equals(method)){
			Logout(request,response);
		}else if("findQuestion".equals(method)){
			findQuestion(request,response);
		}else if("checkAnswer".equals(method)){
			checkAnswer(request,response);
		}else if("changePassword".equals(method)){
			changePassword(request,response);
		}
	}

	//校验会员用户
	private void CheckUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Object[] isUserCookie=request.getParameterValues("isUserCookie");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
		//创建业务逻辑层对象
		UserService userService=new UserServiceImpl();
		user.setUsername(username);
		user.setPassword(MD5Util.getMD5String(password));
		if(isUserCookie!=null){
			//创建cookie对象
			Cookie uname=new Cookie("username",username);
			Cookie pwd=new Cookie("password",password);
			uname.setMaxAge(60*60*24*7);
			pwd.setMaxAge(60*60*24*7);
			response.addCookie(uname);
			response.addCookie(pwd);
			int count=userService.checkUser(user);
			if(count>0){
				String status=userService.checkStatus(username);
				String userid=userService.findUserId(username);
				if("正常".equals(status)){
					request.getSession().setAttribute("userid",userid);
					request.getSession().setAttribute("username",username);
					request.getSession().setAttribute("password",password);
					response.sendRedirect(request.getContextPath()+"/ProductTypeServlet");
				}else{
					request.setAttribute("error", "账号异常,已被限制登录!");
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("error", "账号或者密码不正确!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		}else{
			//创建cookie对象
			Cookie uname=new Cookie("username",username);
			Cookie pwd=new Cookie("password",password);
			uname.setMaxAge(0);
			pwd.setMaxAge(0);
			response.addCookie(uname);
			response.addCookie(pwd);
			int count=userService.checkUser(user);
			
			if(count>0){
				String userid=userService.findUserId(username);
				String status=userService.checkStatus(username);
				if("正常".equals(status)){
					request.getSession().setAttribute("userid",userid);
					request.getSession().setAttribute("username",username);
					request.getSession().setAttribute("password",password);
					response.sendRedirect(request.getContextPath()+"/ProductTypeServlet");
				}else{
					request.setAttribute("error", "账号异常,已被限制登录!");
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("error", "账号或者密码不正确!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
			
		}
	}
	
	//检验验证码是否正确
	private void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String code=request.getParameter("code").toUpperCase();	
		Object obj=request.getSession().getAttribute("checkcode");
		response.setCharacterEncoding("utf-8");		
		PrintWriter out = response.getWriter();
		if(code.equals(obj.toString())){
			String error="{\"error\":\"验证码正确\"}";
			JSONObject listJson = JSONObject.fromObject(error);
			out.print(listJson.toString());
		}else{
			String error="{\"error\":\"验证码不正确\"}";
			JSONObject listJson = JSONObject.fromObject(error);
			out.print(listJson.toString());
		}
		out.flush();
		out.close();
	}	
	
	//检测用户名是否存在
	private void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		UserService userService=new UserServiceImpl();
		String username=request.getParameter("username");
		int count=userService.checkUserName(username);
		response.setCharacterEncoding("utf-8");		
		PrintWriter out = response.getWriter();
		if(count>0){
			String error="{\"error\":\"用户名已存在\"}";
			JSONObject listJson = JSONObject.fromObject(error);
			out.print(listJson.toString());
		}else{
			String error="{\"error\":\"用户名可用\"}";
			JSONObject listJson = JSONObject.fromObject(error);
			out.print(listJson.toString());
		}
		out.flush();
		out.close();
	}
	
	//添加会员用户
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		UserService userService=new UserServiceImpl();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String telephone=request.getParameter("tel");
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		User user=new User();
		user.setUsername(username);
		user.setPassword(MD5Util.getMD5String(password));
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setQuestion(question);
		user.setAnswer(answer);
		boolean flag=userService.addUsers(user);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/register-success.jsp");
		}
	}
	
	//退出
	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//立即销毁session对象
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/ProductTypeServlet");
	}
	
	//查询密保问题
	private void findQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		UserService userService=new UserServiceImpl();		
		String username=request.getParameter("username");
		String question=userService.checkQueston(username);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		out.print(question.toString());
		out.flush();
		out.close();
	}
	
	//查询密保答案
	private void checkAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		UserService userService=new UserServiceImpl();		
		String username=request.getParameter("username");
		String Answer=request.getParameter("answer");
		String answer=userService.checkAnswer(username);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		if(Answer.equals(answer)){
			out.print("true");
		}else{
			out.print("false");
		}
		out.flush();
		out.close();
	}	
	
	//修改密码
	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		UserService userService=new UserServiceImpl();		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String MDPD=MD5Util.getMD5String(password);
		User user=new User();
		user.setUsername(username);
		user.setPassword(MDPD);
		boolean flag=userService.updatePwd(user);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/pass-reset-success.jsp");
		}
	}
}
