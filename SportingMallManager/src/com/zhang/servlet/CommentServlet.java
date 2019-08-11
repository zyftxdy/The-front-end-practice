package com.zhang.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.service.CommentService;
import com.zhang.service.impl.CommentServiceImpl;
import com.zhang.util.Common;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("deleteComment".equals(method)){
			deleteComment(request,response);
		}else{
			findComment(request,response);
		}
	}

	//查询评论
	private void findComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		CommentService commentService = new CommentServiceImpl();
		//2.获取查询条件
		String commentId=request.getParameter("commentId");
		String productId=request.getParameter("productId");
		Object[] parameters = new Object[2];
		//1.2设置查询条件
		if(commentId!=null && !"".equals(commentId)){
			parameters[0] = commentId;
		}
		if(productId!=null && !"".equals(productId)){
			parameters[1] = productId;
		}
		//2.调用业务逻辑层中的查询方法
		//2.1获取符合查询条件的总记录数
		int rows =commentService.getRows(parameters);
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
		List<Map<String,Object>> comments=commentService.findComment(currentpage, Common.PAGERECORDS, parameters);
		//3.将查询结果(集合对象)存放到request对象中
		request.setAttribute("comments", comments);
		request.setAttribute("productId", productId);
		request.setAttribute("commentId", commentId);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.跳转到查询页面
		request.getRequestDispatcher("/comment.jsp").forward(request, response);		
	} 
	
	//删除商品评论
	private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		CommentService commentService = new CommentServiceImpl();
		int commentId=Integer.parseInt(request.getParameter("commentId"));
		boolean flag=commentService.deleteComment(commentId);
		if(flag){
			findComment(request,response);
		}
	} 
	
}
