package com.zhang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.Address;
import com.zhang.model.User;
import com.zhang.service.UserService;
import com.zhang.serviceImpl.UserServiceImpl;
import com.zhang.util.MD5Util;

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
		if("findUser".equals(method)){
			findUser(request,response);
		}else if("checkUserId".equals(method)){
			checkUserId(request,response);
		}else if("checkPassword".equals(method)){
			checkPassword(request,response);
		}else if("findInfo".equals(method)){
			findInfo(request,response);
		}else if("updateUser".equals(method)){
			updateUser(request,response);
		}else if("findAddress".equals(method)){
			findAddress(request,response);
		}else if("addAddress".equals(method)){
			addAddress(request,response);
		}else if("updateAddress".equals(method)){
			updateAddress(request,response);
		}else if("deleteAddress".equals(method)){
			deleteAddress(request,response);
		}else if("findAddressByCd".equals(method)){
			findAddressByCd(request,response);
		}
	}

	//�����Ƿ��¼
	private void checkUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Object objs=request.getSession().getAttribute("userid");
		if(objs==null){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else{
			response.sendRedirect(request.getContextPath()+"/person-center.jsp");
		}
	}
	
	//���һ�Ա�û���Ϣ
	private void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		String userid=request.getSession().getAttribute("userid").toString();
		User user=userService.findUser(userid);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/person.jsp").forward(request, response);
	}
	
	//���һ�Ա�û���Ϣ
	private void findInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		String userid=request.getSession().getAttribute("userid").toString();
		User user=userService.findUser(userid);
		request.setAttribute("user", user);
		//��ת���޸Ľ���
		request.getRequestDispatcher("/person-change.jsp").forward(request, response);
	}	
	
	//�޸Ļ�Ա�û���Ϣ
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		User user=new User();
		String userid=request.getSession().getAttribute("userid").toString();
		String telephone=request.getParameter("telephone");
		String email=request.getParameter("email");
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		//��user����ֵ
		user.setUserid(userid);
		user.setTelephone(telephone);
		user.setEmail(email);
		user.setQuestion(question);
		user.setAnswer(answer);
		//System.out.println(user.toString());
		boolean flag=userService.updateUser(user);
		if(flag){
			findUser(request,response);
		}
	}
	
	//���û������޸�����
	private void checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		String username=request.getSession().getAttribute("username").toString();
		String password=request.getParameter("password");
		String MDPD=MD5Util.getMD5String(password);
		User user=new User();
		user.setUsername(username);
		user.setPassword(MDPD);
		boolean flag=userService.updatePwd(user);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/password-change.jsp");
		}
	}
	
	//���ݻ�Ա�û�ID����ջ���ַ
	private void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		//��ȡֵ
		String userid=request.getSession().getAttribute("userid").toString();
		String addressname=request.getParameter("addressname");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String addressdatils=request.getParameter("addressdatils");
		String telephone=request.getParameter("telephone");
		Address address=new Address();
		//��address����ֵ
		address.setAddressname(addressname);
		address.setProvince(province);
		address.setProvince(province);
		address.setCity(city);
		address.setAddressdatils(addressdatils);
		address.setTelephone(telephone);
		//System.out.println(address);
		boolean flag=userService.addAddress(address, userid);
		if(flag){
			findAddress(request,response);
		}
		
	}
	
	//���һ�Ա�û��ջ���ַ
	private void findAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		String userid=request.getSession().getAttribute("userid").toString();
		List<Address> address=userService.findAddress(userid);
		request.setAttribute("address", address);
		//��ת���ջ���ַ�б�
		request.getRequestDispatcher("/address-list.jsp").forward(request, response);
	} 
	
	//�����ջ���ַID�޸��ջ���ַ
	private void updateAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		//��ȡֵ
		String addressid=request.getParameter("addressId");
		String addressname=request.getParameter("addressname");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String addressdatils=request.getParameter("addressdatils");
		String telephone=request.getParameter("telephone");
		Address address=new Address();
		//��address����ֵ
		address.setAddressid(addressid);
		address.setAddressname(addressname);
		address.setProvince(province);
		address.setProvince(province);
		address.setCity(city);
		address.setAddressdatils(addressdatils);
		address.setTelephone(telephone);
		//System.out.println(address);
		boolean flag=userService.updateAddress(address);
		if(flag){
			findAddress(request,response);
		}
	}
	
	//�����ջ���ַIDɾ���ջ���ַ
	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		String addressid=request.getParameter("addressId");
		boolean flag=userService.deleteAddress(addressid);
		if(flag){
			findAddress(request,response);
		}
	}
	
	//�����ջ���ַID��ѯ�ջ���ַ
	private void findAddressByCd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		UserService userService=new UserServiceImpl();
		String addressid=request.getParameter("addressId");
		Address address=userService.findAddressByCd(addressid);	
		request.setAttribute("address", address);
		//��ת���޸Ľ���
		request.getRequestDispatcher("/address-change.jsp").forward(request, response);
	}
}
