package com.zhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.dao.OrderDao;
import com.zhang.model.OrderAddress;
import com.zhang.service.OrderService;
import com.zhang.service.impl.OrderServiceImpl;
import com.zhang.util.Common;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("findDatils".equals(method)){
			findDatils(request,response);
		}else if("deliverOrder".equals(method)){
			deliverOrder(request,response);
		}else {
			findOrder(request, response);
		}
	}

	//查看订单
	private void findOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建数据库层访问对象
		OrderService orderDao=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");
		//2.调用业务逻辑层中的查询方法
		//2.1获取符合查询条件的总记录数
		int rows =orderDao.getRows(orderNo);
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
		List<Map<String,Object>> order=orderDao.findOrder(currentpage,Common.PAGERECORDS, orderNo);
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("order", order);
		//4.跳转到查询页面
		request.getRequestDispatcher("/order-manager.jsp").forward(request, response);
	}
	
	//查看订单详情
	private void findDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建数据库层访问对象
		OrderService orderDao=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");	
		OrderAddress orderAddress=orderDao.findOrderAddress(orderNo);
		List<Map<String,Object>> orderDatils=orderDao.findOrderDatilsByON(orderNo);
		request.setAttribute("orderAddress", orderAddress);
		request.setAttribute("orderDatils", orderDatils);
		//4.跳转到查询页面
		request.getRequestDispatcher("/order-datils.jsp").forward(request, response);		
	}
	
	//发货
	private void deliverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建数据库层访问对象
		OrderService orderService=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");
		boolean flag=orderService.deliver(orderNo);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		if(flag){
			out.print("true");
		}else{
			out.print("false");
		}
		out.flush();
		out.close();
	}	
}
