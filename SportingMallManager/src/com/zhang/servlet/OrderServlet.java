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

	//�鿴����
	private void findOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�������ݿ����ʶ���
		OrderService orderDao=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");
		//2.����ҵ���߼����еĲ�ѯ����
		//2.1��ȡ���ϲ�ѯ�������ܼ�¼��
		int rows =orderDao.getRows(orderNo);
		//2.2������ʾ��Ʒ��Ϣ����ҳ��
		int totalPages = rows / Common.PAGERECORDS;
		if(rows % Common.PAGERECORDS != 0){
			totalPages ++ ;
		}
		//2.3��ǰҳ��(Ĭ��ֵΪ1)
		int currentpage = 1;
		//2.4��ȡ�ͻ����ύ�ĵ�ǰҳ
		String result =request.getParameter("currentpage");
		if(result!=null && !"".equals(result)){
			currentpage = Integer.parseInt(result);
		}
		//2.����ҵ���߼����еĲ�ѯ����
		List<Map<String,Object>> order=orderDao.findOrder(currentpage,Common.PAGERECORDS, orderNo);
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("order", order);
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/order-manager.jsp").forward(request, response);
	}
	
	//�鿴��������
	private void findDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�������ݿ����ʶ���
		OrderService orderDao=new OrderServiceImpl();
		String orderNo=request.getParameter("orderNo");	
		OrderAddress orderAddress=orderDao.findOrderAddress(orderNo);
		List<Map<String,Object>> orderDatils=orderDao.findOrderDatilsByON(orderNo);
		request.setAttribute("orderAddress", orderAddress);
		request.setAttribute("orderDatils", orderDatils);
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/order-datils.jsp").forward(request, response);		
	}
	
	//����
	private void deliverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�������ݿ����ʶ���
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
