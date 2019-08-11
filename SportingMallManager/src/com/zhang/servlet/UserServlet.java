package com.zhang.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.User;
import com.zhang.service.UserService;
import com.zhang.service.impl.UserServiceImpl;
import com.zhang.util.Common;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("updateStatus".equals(method)){
			updateStatus(request,response);
		}else if("recoverStatus".equals(method)){
			recoverStatus(request,response);
		}else{
			findUsers(request,response);
		}
	}

	//查询会员用户
	private void findUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.创建业务逻辑层对象
		UserService userService = new UserServiceImpl();
		//2.获取查询条件
		String userid=request.getParameter("userid");
		Object[] parameters = new Object[1];
		//1.2设置查询条件
		if(userid!=null && !"".equals(userid)){
			parameters[0] = userid;
		}
		//2.调用业务逻辑层中的查询方法
		//2.1获取符合查询条件的总记录数
		int rows =userService.getRows(parameters);
		//2.2计算显示商品信息的总页数
		int totalPages = rows / Common.PAGERECORDS;
		if(rows % Common.PAGERECORDS != 0){
			totalPages ++ ;
		}
		//2.3当前页码(默认值为1)
		int currentpage = 1;
		//2.4获取客户端提交的当前页
		String result =request.getParameter("currentpage");
		if(result!=null && !"".equals(result)){
			currentpage = Integer.parseInt(result);
		}
		//2.调用业务逻辑层中的查询方法
		List<Map<String,Object>> users=userService.findUsers(currentpage, Common.PAGERECORDS, parameters);
		//3.将查询结果(集合对象)存放到request对象中
		request.setAttribute("users", users);
		request.setAttribute("userid", userid);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.跳转到查询页面
		request.getRequestDispatcher("/user-manager.jsp").forward(request, response);		
	}
	
	//更改会员登录权限
	private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		UserService userService = new UserServiceImpl();
		String userid=request.getParameter("userid");
		boolean flag=userService.updateStatus(userid);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/UserServlet");
		}
	}
	
	//允许会员用户登录
	private void recoverStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		UserService userService = new UserServiceImpl();
		String userid=request.getParameter("userid");
		boolean flag=userService.recoverStatus(userid);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/UserServlet");
		}
	}
}
