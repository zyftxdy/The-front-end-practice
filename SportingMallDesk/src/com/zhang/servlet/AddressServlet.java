package com.zhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.Address;
import com.zhang.service.UserService;
import com.zhang.serviceImpl.UserServiceImpl;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("addNewAddress".equals(method)) {
			addNewAddress(request,response);
		}else if("deleteAddress".equals(method)) {
			deleteAddress(request,response);
		}
	}
	
	//添加新的收货地址
	private void addNewAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建逻辑层访问对象
		UserService userService=new UserServiceImpl();	
		String userId=request.getSession().getAttribute("userid").toString();
		String addressName=request.getParameter("addressName");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String addressDatils=request.getParameter("addressDatils");
		String tel=request.getParameter("tel");
		Address address=new Address();
		address.setAddressname(addressName);
		address.setProvince(province);
		address.setCity(city);
		address.setAddressdatils(addressDatils);
		address.setTelephone(tel);
		boolean flag=userService.addAddress(address, userId);
		if(flag) {
			List<Address> listdata=userService.findAddress(userId);
			JSONArray listJson = JSONArray.fromObject(listdata);
			//4.响应客户端异步请求发送商品信息(json格式)
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(listJson.toString());
			out.flush();
			out.close();
		}
	}

	//添加新的收货地址
	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层访问对象
		UserService userService=new UserServiceImpl();	
		String userId=request.getSession().getAttribute("userid").toString();
		String addressId=request.getParameter("addressId");
		boolean flag=userService.deleteAddress(addressId);
		if(flag) {
			List<Address> listdata=userService.findAddress(userId);
			JSONArray listJson = JSONArray.fromObject(listdata);
			//4.响应客户端异步请求发送商品信息(json格式)
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(listJson.toString());
			out.flush();
			out.close();
		}
	}
}
