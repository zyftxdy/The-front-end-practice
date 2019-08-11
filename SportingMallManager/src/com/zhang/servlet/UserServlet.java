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

	//��ѯ��Ա�û�
	private void findUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.����ҵ���߼������
		UserService userService = new UserServiceImpl();
		//2.��ȡ��ѯ����
		String userid=request.getParameter("userid");
		Object[] parameters = new Object[1];
		//1.2���ò�ѯ����
		if(userid!=null && !"".equals(userid)){
			parameters[0] = userid;
		}
		//2.����ҵ���߼����еĲ�ѯ����
		//2.1��ȡ���ϲ�ѯ�������ܼ�¼��
		int rows =userService.getRows(parameters);
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
		List<Map<String,Object>> users=userService.findUsers(currentpage, Common.PAGERECORDS, parameters);
		//3.����ѯ���(���϶���)��ŵ�request������
		request.setAttribute("users", users);
		request.setAttribute("userid", userid);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/user-manager.jsp").forward(request, response);		
	}
	
	//���Ļ�Ա��¼Ȩ��
	private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		UserService userService = new UserServiceImpl();
		String userid=request.getParameter("userid");
		boolean flag=userService.updateStatus(userid);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/UserServlet");
		}
	}
	
	//�����Ա�û���¼
	private void recoverStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		UserService userService = new UserServiceImpl();
		String userid=request.getParameter("userid");
		boolean flag=userService.recoverStatus(userid);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/UserServlet");
		}
	}
}
