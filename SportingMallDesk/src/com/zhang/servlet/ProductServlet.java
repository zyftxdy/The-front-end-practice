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

import com.zhang.model.Tb_cart;
import com.zhang.service.CartService;
import com.zhang.service.ProductService;
import com.zhang.serviceImpl.CartServiceImpl;
import com.zhang.serviceImpl.ProductServiceImpl;
import com.zhang.util.Common;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
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
		}else if("find".equals(method)){
			findProducts(request,response);
		}else if("findDatilsByCd".equals(method)){
			findDatilsByCd(request,response);
		}else if("checkCount".equals(method)){
			checkCount(request,response);
		}else if("buyProduct".equals(method)){
			buyProduct(request,response);
		}
	}
	
	//查询商品列表
	private void findProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建逻辑层访问对象
		ProductService productService=new ProductServiceImpl();
		String productName=request.getParameter("productName");
		String firstTypeId=request.getParameter("firstTypeId");
		String secondTypeId=request.getParameter("secondTypeId");
		Object[] parameters= new Object[3];
		if(firstTypeId!=null && !"".equals(firstTypeId)){
			parameters[0]=firstTypeId;
		}
		if(secondTypeId!=null && !"".equals(secondTypeId)){
			parameters[1]=secondTypeId;
		}
		if(productName!=null && !"".equals(productName)){
			parameters[2]=productName;
		}
		int rows=productService.getRows(parameters);
		int totalPages=rows/Common.PAGERECORDS;
		if(rows % Common.PAGERECORDS!=0){
			totalPages++;
		}
		//2.3当前页码(默认值为1)
		int currentpage = 1;
		//2.4获取客户端提交的当前页
		String result =request.getParameter("currentpage");
		if(result!=null && !"".equals(result)){
			currentpage = Integer.parseInt(result);
		}
		//默认排序方式
		int Sorting = 1;
		String Sortings=request.getParameter("Sorting");
		if(Sortings!=null && !"".equals(Sortings)){
			Sorting = Integer.parseInt(Sortings);
		}
		List<Map<String,Object>> products =productService.findProducts(currentpage,Common.PAGERECORDS,Sorting,parameters);
		//3.将查询结果(集合对象)存放到request对象中
		request.setAttribute("productName", productName);
		request.setAttribute("firstTypeId", firstTypeId);
		request.setAttribute("secondTypeId", secondTypeId);
		request.setAttribute("products", products);
		request.setAttribute("Sorting", Sorting);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.（转发）跳转到查询页面
		request.getRequestDispatcher("/productList.jsp").forward(request, response);		
	}

	//查询商品详情
	private void findDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		ProductService productService=new ProductServiceImpl();
		//获取查询条件
		String productId=request.getParameter("productId");
		String productName=request.getParameter("productName");
		String productDesc=request.getParameter("productDesc");
		String picture=request.getParameter("picture");
		//查询商品最低价格
		double minPrice=productService.getMinPrice(productId);
		//查询商品最高价格
		double maxPrice=productService.getMaxPrice(productId);
		//查询商品总库存
		int nums=productService.getNums(productId);
		//查询商品颜色列表
		List<String> colours=productService.getColour(productId);
		//查询商品尺寸列表
		List<String> cloths=productService.getClothing(productId);
		//查询商品鞋码列表
		List<String> shots=productService.getShot(productId);
		//查询评论总数
		int rows=productService.getCommentRows(productId);
		//查询商品评论详情
		List<Map<String,Object>> comments=productService.findComment(1, Common.PAGERECORDS, productId);
		//3.将查询结果(集合对象)存放到request对象中
		request.setAttribute("productId",productId);
		request.setAttribute("productName",productName);
		request.setAttribute("productDesc",productDesc);
		request.setAttribute("picture",picture);
		request.setAttribute("minPrice",minPrice);
		request.setAttribute("maxPrice",maxPrice);
		request.setAttribute("nums",nums);
		request.setAttribute("colours",colours);
		request.setAttribute("cloths",cloths);
		request.setAttribute("shots",shots);
		request.setAttribute("rows",rows);
		request.setAttribute("comments",comments);
		//跳转
		request.getRequestDispatcher("/productDatils.jsp").forward(request, response);
	}
	
	//根据条件重新查询商品详情
	private void findDatilsByCd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		ProductService productService=new ProductServiceImpl();
		//获取查询条件
		String productId=request.getParameter("productId");
		String color=request.getParameter("color");
		String shotsize=request.getParameter("shotsize");
		String clothingsize=request.getParameter("clothingsize");
		//System.out.println(color+shotsize+clothingsize);
		Object[] parameters = new Object[2];
		//1.2设置查询条件
		if(shotsize!=null && !"".equals(shotsize)){
			parameters[0] = shotsize;
		}
		if(clothingsize!=null && !"".equals(clothingsize)){
			parameters[1] = clothingsize;
		}
		//2.调用业务逻辑层中的查询方法
		double price=productService.getPriceByCd(productId, color, parameters);
		int nums=productService.getNumsByCd(productId, color, parameters);
		String picture=productService.getPictureByCd(productId, color, parameters);
		String _price=""+price;
		String _nums=""+nums;
		//3.响应客户端的异步请求
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		StringBuffer sb=new StringBuffer();	
		sb.append(_price).append("#");
		sb.append(_nums).append("#");
		sb.append(picture);
		out.println(sb.toString());
		out.flush();
		out.close();
	}
	
	//根据条件重新查询商品详情是否存在
	private void checkCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		ProductService productService=new ProductServiceImpl();
		//获取查询条件
		String productId=request.getParameter("productId");
		String color=request.getParameter("color");
		String shotsize=request.getParameter("shotsize");
		String clothingsize=request.getParameter("clothingsize");
		//System.out.println(color+shotsize+clothingsize);
		Object[] parameters = new Object[2];
		//1.2设置查询条件
		if(shotsize!=null && !"".equals(shotsize)){
			parameters[0] = shotsize;
		}
		if(clothingsize!=null && !"".equals(clothingsize)){
			parameters[1] = clothingsize;
		}	
		int count=productService.CheckCount(productId, color, parameters);
		//3.响应客户端的异步请求
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		if(count>0){
			out.print("true");
		}else{
			out.print("false");
		}
		out.flush();
		out.close();		
	}	
	
	//添加商品进临时购物车
	private void buyProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建业务逻辑层对象
		ProductService productService=new ProductServiceImpl();	
		CartService cartService=new CartServiceImpl();
		Object objs=request.getSession().getAttribute("userid");
		if(objs==null){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else {
			String productId=request.getParameter("productId");
			String productName=request.getParameter("productName");
			String color=request.getParameter("color");
			String shotsize=request.getParameter("shot");
			String clothingsize=request.getParameter("cloth");
			int number=Integer.parseInt(request.getParameter("number"));
			String userid=objs.toString();
			Object[] parameters = new Object[2];
			//1.2设置查询条件
			if(shotsize!=null && !"".equals(shotsize)){
				parameters[0] = shotsize;
			}
			if(clothingsize!=null && !"".equals(clothingsize)){
				parameters[1] = clothingsize;
			}
			//查询商品详情ID
			String datilsId=productService.findDatilsId(productId, color, parameters);
			Tb_cart cart=new Tb_cart();
			cart.setDatilsid(datilsId);
			cart.setUserid(userid);
			cart.setProductname(productName);
			cart.setNumber(number);
			boolean flag;
			int count=cartService.getCount(cart);
			if(count>0){
				flag=cartService.updateCart(cart);
				if(flag){
					response.sendRedirect(request.getContextPath()+"/cart-success.jsp");
				}
			}else{
				flag=cartService.addCart(cart);
				if(flag){
					response.sendRedirect(request.getContextPath()+"/cart-success.jsp");
				}
			}
		}	
	}	
}
