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

import com.zhang.model.AuthorityUser;
import com.zhang.service.AuthorityService;
import com.zhang.service.impl.AuthorityServiceImpl;
import com.zhang.util.Common;

/**
 * Servlet implementation class AuthorityServlet
 */
@WebServlet("/AuthorityServlet")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("checkAuthority".equals(method)) {
			checkAuthority(request,response);
		}else if("addAuthority".equals(method)) {
			addAuthority(request,response);
		}else if("deleteAuthority".equals(method)) {
			deleteAuthority(request,response);
		}else if("gotoAuthority".equals(method)) {
			gotoAuthority(request,response);
		}else if("updateAuthority".equals(method)) {
			updateAuthority(request,response);
		}else {
			findAuthority(request,response);
		}
	}

	//�鿴Ȩ���б�
	private void findAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorityService authorityService=new AuthorityServiceImpl();
		String obj=request.getParameter("authority");
		Object[] parameters = new Object[1];
		if(obj!=null && !"".equals(obj)) {
			int authority=Integer.parseInt(obj);
			parameters[0] = authority;
		}
		//2.����ҵ���߼����еĲ�ѯ����
		//2.1��ȡ���ϲ�ѯ�������ܼ�¼��
		int rows =authorityService.getRows(parameters);
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
		List<Map<String,Object>> authoritys=authorityService.findAuthority(currentpage, Common.PAGERECORDS, parameters);
		//3.����ѯ���(���϶���)��ŵ�request������
		request.setAttribute("authoritys", authoritys);
		request.setAttribute("obj", obj);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/authority.jsp").forward(request, response);
	}	
	
	//У��Ȩ��
	private void checkAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AuthorityService authorityService=new AuthorityServiceImpl();
		int authority=Integer.parseInt(request.getParameter("authority"));
		String adminName=request.getSession().getAttribute("adminName").toString();
		//�����û�Ȩ��
		int authorityUser=authorityService.getAuthority(adminName);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		if(authority==authorityUser || authorityUser==4) {
			out.print("true");
		}else {
			out.print("false");
		}
		out.close();
		out.flush();
	}
	
	//���Ȩ����Ա
	private void addAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorityService authorityService=new AuthorityServiceImpl();	
		String authorityName=request.getParameter("productName");
		String authorityPwd=request.getParameter("productPrice");
		int authority=Integer.parseInt(request.getParameter("radioAuthority"));
		AuthorityUser authorityUser=new AuthorityUser();
		authorityUser.setAdminName(authorityName);
		authorityUser.setAdminPwd(authorityPwd);
		authorityUser.setAuthority(authority);
		boolean flag=authorityService.addAuthority(authorityUser);
		if(flag) {
			findAuthority(request, response);
		}
	} 
	
	//ɾ��Ȩ����Ա
	private void deleteAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorityService authorityService=new AuthorityServiceImpl();
		int authority=Integer.parseInt(request.getParameter("authorityId"));
		boolean flag=authorityService.daleteAuthority(authority);
		if(flag) {
			findAuthority(request, response);
		}
	}
	
	//��ת���޸Ľ���
	private void gotoAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int authorityId=Integer.parseInt(request.getParameter("authority"));
		String authorityName=request.getParameter("authorityName");
		request.setAttribute("authorityId", authorityId);
		request.setAttribute("authorityName", authorityName);
		request.getRequestDispatcher("/updateAuthority.jsp").forward(request, response);
	}
	
	//�޸�Ȩ����Ա
	private void updateAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorityService authorityService=new AuthorityServiceImpl();
		int authorityId=Integer.parseInt(request.getParameter("authorityId"));
		int authority=Integer.parseInt(request.getParameter("radioAuthority"));
		boolean flag=authorityService.updateAuthority(authorityId, authority);
		if(flag) {
			findAuthority(request, response);
		}
	}
}
