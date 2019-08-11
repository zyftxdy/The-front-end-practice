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

import com.zhang.service.CartService;
import com.zhang.serviceImpl.CartServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("findCart".equals(method)){
			findCart(request,response);
		}else if("change1".equals(method)){
			change1(request,response);
		}else if("change2".equals(method)){
			change2(request,response);
		}else if("deleteProduct".equals(method)){
			deleteProduct(request,response);
		}else if("deleteProducts".equals(method)){
			deleteProducts(request,response);
		}else if("showTotal".equals(method)){
			showTotal(request,response);
		}else if("checked".equals(method)){
			checked(request,response);
		}else if("unchecked".equals(method)){
			unchecked(request,response);
		}
	}

	//查看临时购物车
	private void findCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();		
		Object objs=request.getSession().getAttribute("userid");
		if(objs==null){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else{
			String userid=objs.toString();
			List<Map<String,Object>> cart=cartService.findProducts(userid);
			request.setAttribute("cart", cart);
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}
	
	//重新查询临时购物车的商品价格(增加)
	private void change1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();	
		String cid=request.getParameter("cid");
		double price=Double.parseDouble(request.getParameter("price").toString());
		boolean flag=cartService.addQuantity(cid);
		if(flag){
			int number=cartService.getQuantity(cid);
			double allPrice=price*number;
			String AP=""+allPrice;
			PrintWriter out =response.getWriter();
			out.print(AP);
			out.flush();
			out.close();
		}
		
	}
	
	//重新查询临时购物车的商品价格(减少)
	private void change2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();	
		String cid=request.getParameter("cid");
		double price=Double.parseDouble(request.getParameter("price").toString());
		boolean flag=cartService.reduceQuantity(cid);
		if(flag){
			int number=cartService.getQuantity(cid);
			double allPrice=price*number;
			String AP=""+allPrice;
			PrintWriter out =response.getWriter();
			out.print(AP);
			out.flush();
			out.close();
		}
	}
	
	//从临时购物车中删除商品
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();
		String cid=request.getParameter("cid");
		boolean flag=cartService.daleteCart(cid);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/CartServlet?method=findCart");
		}
	}
	
	//从临时购物车中批量删除商品
	private void deleteProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();
		String[] cid=request.getParameterValues("cid");
		String[] parameters = new String[cid.length];
		for(int i=0;i<cid.length;i++ ){
			parameters[i] = cid[i];
		}
		boolean flag=cartService.deleteCarts(parameters);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/CartServlet?method=findCart");
		}
	}
	
	//从临时购物车中批量删除商品
	private void showTotal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();
		String[] cid=request.getParameterValues("cid");
		int count=0;
		double price=0;
		double total=0;
		if(cid!=null){
			for(int i=0;i<cid.length;i++ ){
				count=cartService.getQuantity(cid[i]);
				price=cartService.findPrice(cid[i]);
				total=total+count*price;
			}
		}
		String totalPrice=""+total;
		PrintWriter out =response.getWriter();
		out.print(totalPrice);
		out.flush();
		out.close();
	}
	
	//修改状态为选中
	private void checked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();
		String[] cid=request.getParameterValues("cid");
		String[] parameters = new String[cid.length];
		for(int i=0;i<cid.length;i++ ){
			parameters[i] = cid[i];
		}
		//System.out.println(parameters);
		cartService.checked(parameters);
	}
	
	//修改状态为未选中
	private void unchecked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象	
		CartService cartService=new CartServiceImpl();
		String[] cid=request.getParameterValues("cid");
		String[] parameters = new String[cid.length];
		for(int i=0;i<cid.length;i++ ){
			parameters[i] = cid[i];
		}
		//System.out.println(parameters);
		cartService.unchecked(parameters);
	}
}
