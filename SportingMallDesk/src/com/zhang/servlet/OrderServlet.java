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

import com.zhang.model.Address;
import com.zhang.model.Comment;
import com.zhang.model.Order;
import com.zhang.model.OrderAddress;
import com.zhang.model.Product;
import com.zhang.service.CartService;
import com.zhang.service.OrderService;
import com.zhang.service.UserService;
import com.zhang.serviceImpl.CartServiceImpl;
import com.zhang.serviceImpl.OrderServiceImpl;
import com.zhang.serviceImpl.UserServiceImpl;
import com.zhang.util.Common;
import com.zhang.util.OrderNumber;

import net.sf.json.JSONArray;

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
		if("pure".equals(method)){
			pure(request,response);
		}else if("order".equals(method)){
			order(request,response);
		}else if("findOrder".equals(method)){
			findOrder(request,response);
		}else if("findOrderDatils".equals(method)){
			findOrderDatils(request,response);
		}else if("cancelOrder".equals(method)){
			cancelOrder(request,response);
		}else if("payOrder".equals(method)){
			payOrder(request,response);
		}else if("cancelOrder".equals(method)){
			cancelOrder(request,response);
		}else if("pay".equals(method)){
			pay(request,response);
		}else if("getOrder".equals(method)){
			getOrder(request,response);
		}else if("comment".equals(method)){
			comment(request,response);
		}else if("addcomment".equals(method)){
			addcomment(request,response);
		}
	}

	//����
	private void pure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		UserService userService=new UserServiceImpl();
		Object objs=request.getSession().getAttribute("userid");
		if(objs==null){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else{
			double total=Double.parseDouble(request.getParameter("total"));
			String userid=objs.toString();
			List<Address> address=userService.findAddress(userid);
			List<Map<String,Object>> product=orderService.findProdcut(userid);
			request.setAttribute("address", address);
			request.setAttribute("product", product);
			request.setAttribute("total", total);
			//��ת������ҳ��
			request.getRequestDispatcher("/confirm.jsp").forward(request, response);
		}
	} 
	
	//�ύ����
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		CartService  cartService=new CartServiceImpl();
		UserService userService=new UserServiceImpl();
		Object objs=request.getSession().getAttribute("userid");
		//�ж��û��Ƿ��¼
		if(objs==null){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else{
			String userid=objs.toString();
			int count=0;
			String productName="";
			double total=Double.parseDouble(request.getParameter("total"));
			String[] datilsid=request.getParameterValues("datilsId");
			String addressid=request.getParameter("shoppingId");
			String OrderNo=OrderNumber.getOrderNumber(userid);
			String[] parameters = new String[datilsid.length];
			for(int i=0;i<datilsid.length;i++){
				parameters[i] = datilsid[i];
				//�޸���Ʒ���
				count=orderService.getQuantity(datilsid[i]);
				orderService.updateNums(datilsid[i], count);
				productName=cartService.getProductName(datilsid[i]);
				//�����Ʒ����
				orderService.addOrderDatils(OrderNo, datilsid[i],userid,productName,count);
			}
			//��order����ֵ
			Order order=new Order();
			order.setUserId(userid);
			order.setOrderNo(OrderNo);
			order.setTotal(total);
			//�ӹ��ﳵ��ɾ����Ʒ
			orderService.deleteCarts(parameters);
			Address address = userService.findAddressByCd(addressid);
			//�����������Ʒ
			orderService.addOrder(order,address);
			//��ת
			request.setAttribute("orderNo", OrderNo);
			request.setAttribute("total", total);
			request.getRequestDispatcher("/pay.jsp").forward(request, response);
		}
	}
	
	//�鿴����
	private void findOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		Object objs=request.getSession().getAttribute("userid");
		//�ж��û��Ƿ��¼
		if(objs==null){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else{
			String userid=objs.toString();
			int rows =orderService.getRows(userid);
			//2.2������ʾ��Ʒ��Ϣ����ҳ��
			int totalPages = rows / Common.PAGEROWS;
			if(rows % Common.PAGEROWS != 0){
				totalPages ++ ;
			}
			//2.3��ǰҳ��(Ĭ��ֵΪ1)
			int currentpage = 1;
			//2.4��ȡ�ͻ����ύ�ĵ�ǰҳ
			String result =request.getParameter("currentpage");
			if(result!=null && !"".equals(result)){
				currentpage = Integer.parseInt(result);
			}
			List<Map<String,Object>> order=orderService.findOrder(currentpage,Common.PAGEROWS,userid);
			List<Map<String,Object>> orderDatils=orderService.findOrderDatils(userid);
			request.setAttribute("totalRecords", rows);
			request.setAttribute("totalpages", totalPages);
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("order", order);
			request.setAttribute("orderDatils", orderDatils);
			//��ת
			request.getRequestDispatcher("/order-form.jsp").forward(request, response);
		}
	}
	
	//�鿴��������
	private void findOrderDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");
		OrderAddress orderAddress=orderService.findOrderAddress(orderNo);
		List<Map<String,Object>> orderDatils=orderService.findOrderDatilsByON(orderNo);
		request.setAttribute("orderAddress", orderAddress);
		request.setAttribute("orderDatils", orderDatils);
		//��ת
		request.getRequestDispatcher("/order-form-details.jsp").forward(request, response);
	}
	
	//ǰ������֧��ҳ��
	private void payOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderNo=request.getParameter("orderNo");
		double total=Double.parseDouble(request.getParameter("total"));
		//��ת
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("total", total);
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
	}

	
	//֧������
	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");
		boolean flag=orderService.payStatus(orderNo);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/pay-success.jsp");
		}
	}
	
	//ȡ������
	private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");	
		boolean flag=orderService.cancelStatus(orderNo);
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

	//ȷ���ջ�
	private void getOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");	
		boolean flag=orderService.getStatus(orderNo);
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
	
	//ǰ������ҳ��
	private void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		String productId=request.getParameter("productId");
		Product product=orderService.findById(productId);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/person-comment.jsp").forward(request, response);
	}
	
	//�����Ʒ����
	private void addcomment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
		OrderService orderService=new OrderServiceImpl();
		String productId=request.getParameter("productId");
		String userId=request.getSession().getAttribute("userid").toString();
		String datils=request.getParameter("commentdatils");
		Comment comment=new Comment();
		comment.setUserid(userId);
		comment.setProductid(productId);
		comment.setDatils(datils);
		boolean flag=orderService.addComment(comment);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/person-center.jsp");
		}
	}

	
}
