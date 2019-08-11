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

	//��ѯ����
	private void findComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		CommentService commentService = new CommentServiceImpl();
		//2.��ȡ��ѯ����
		String commentId=request.getParameter("commentId");
		String productId=request.getParameter("productId");
		Object[] parameters = new Object[2];
		//1.2���ò�ѯ����
		if(commentId!=null && !"".equals(commentId)){
			parameters[0] = commentId;
		}
		if(productId!=null && !"".equals(productId)){
			parameters[1] = productId;
		}
		//2.����ҵ���߼����еĲ�ѯ����
		//2.1��ȡ���ϲ�ѯ�������ܼ�¼��
		int rows =commentService.getRows(parameters);
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
		List<Map<String,Object>> comments=commentService.findComment(currentpage, Common.PAGERECORDS, parameters);
		//3.����ѯ���(���϶���)��ŵ�request������
		request.setAttribute("comments", comments);
		request.setAttribute("productId", productId);
		request.setAttribute("commentId", commentId);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/comment.jsp").forward(request, response);		
	} 
	
	//ɾ����Ʒ����
	private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		CommentService commentService = new CommentServiceImpl();
		int commentId=Integer.parseInt(request.getParameter("commentId"));
		boolean flag=commentService.deleteComment(commentId);
		if(flag){
			findComment(request,response);
		}
	} 
	
}
