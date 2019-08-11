package com.zhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.Product;
import com.zhang.service.ProductService;
import com.zhang.service.ProductTypeService;
import com.zhang.service.impl.ProductServiceImpl;
import com.zhang.service.impl.ProductTypeServiceImpl;
import com.zhang.util.Common;



/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("update".equals(method)){
			update(request,response);
		}else if("updateStatus".equals(method)){
			updateStatus(request,response);
		}else if("recoverStatus".equals(method)){
			recoverStatus(request,response);
		}else if("datils".equals(method)){
			datils(request,response);
		}else if("changeDatils".equals(method)){
			changeDatils(request,response);
		}else if("addDatils".equals(method)){
			addDatils(request,response);
		}else if("deleteDatils".equals(method)){
			deleteDatils(request,response);
		}else{
			findProducts(request,response);
		}
	}

	//查询商品
	private void findProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.创建业务逻辑层对象
		ProductService productService = new ProductServiceImpl();
		//2.获取查询条件
		String productId=request.getParameter("productId");
		String productName=request.getParameter("productName");
		Object[] parameters = new Object[2];
		//1.2设置查询条件
		if(productId!=null && !"".equals(productId)){
			parameters[0] = productId;
		}
		if(productName!=null && !"".equals(productName)){
			parameters[1] = productName;
		}
		//2.调用业务逻辑层中的查询方法
		//2.1获取符合查询条件的总记录数
		int rows =productService.getRows(parameters);
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
		List<Map<String,Object>> products=productService.FindProducts(currentpage, Common.PAGERECORDS, parameters);
		//3.将查询结果(集合对象)存放到request对象中
		request.setAttribute("products", products);
		request.setAttribute("productId", productId);
		request.setAttribute("productName", productName);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.跳转到查询页面
		request.getRequestDispatcher("/product-manager.jsp").forward(request, response);
	}
	
	//修改商品信息
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		ProductService productService = new ProductServiceImpl();
		ProductTypeService productTypeService=new ProductTypeServiceImpl();
		String productid=request.getParameter("productid");
		Product p=productService.findById(productid);
		String FirstTypeName=productTypeService.findById(p.getFirstTypeId()).toString();
		String SecondTypeName=productTypeService.findBySecondId(p.getSecondTypeId()).toString();
		request.setAttribute("productid", productid);
		request.setAttribute("p", p);
		request.setAttribute("FirstTypeName", FirstTypeName);
		request.setAttribute("SecondTypeName", SecondTypeName);
		//跳转到修改页面
		request.getRequestDispatcher("/productChange.jsp").forward(request, response);
	}
	
	//修改商品状态
	private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		ProductService productService = new ProductServiceImpl();	
		String productid=request.getParameter("productid");
		boolean flag=productService.updateStatus(productid);
		if(flag){
			findProducts(request,response);
		}
	}
	
	//恢复商品状态
	private void recoverStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		ProductService productService = new ProductServiceImpl();	
		String productid=request.getParameter("productid");
		boolean flag=productService.recoverStatus(productid);
		if(flag){
			findProducts(request,response);
		}
	}
	
	//查看商品详情
	private void datils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		ProductService productService = new ProductServiceImpl();	
		String productid=request.getParameter("productid");
		String productName=request.getParameter("productName");
		//2.获取查询条件
		String color=request.getParameter("color");
		String clothingsize=request.getParameter("clothingsize");
		String shotsize=request.getParameter("shotsize");
		Object[] parameters = new Object[3];
		//1.2设置查询条件
		if(color!=null && !"".equals(color)){
			parameters[0] = color;
		}
		if(clothingsize!=null && !"".equals(clothingsize)){
			parameters[1] = clothingsize;
		}
		if(shotsize!=null && !"".equals(shotsize)){
			parameters[2] = shotsize;
		}
		//2.调用业务逻辑层中的查询方法
		//2.1获取符合查询条件的总记录数
		int rows =productService.getDatilsRows(productid, parameters);
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
		List<Map<String,Object>> products=productService.FindDatils(currentpage,Common.PAGERECORDS, productid, parameters);
		int colors=productService.getColor(productid);
		int clothingsizes=productService.getClothing(productid);
		int shotsizes=productService.getShot(productid);
		//3.将查询结果(集合对象)存放到request对象中
		request.setAttribute("colors", colors);
		request.setAttribute("clothingsizes", clothingsizes);
		request.setAttribute("shotsizes", shotsizes);
		request.setAttribute("products", products);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("productid", productid);
		request.setAttribute("productName", productName);
		request.setAttribute("color", color);
		request.setAttribute("clothingsize", clothingsize);
		request.setAttribute("shotsize", shotsize);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.跳转到查询页面
		request.getRequestDispatcher("/product_datils.jsp").forward(request, response);		
	}
	
	//修改商品详情
	private void changeDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String productid=request.getParameter("productid");
		String datilsId=request.getParameter("datilsId");
		String productname=request.getParameter("productName");
		String color=request.getParameter("color");
		String clothingsize=request.getParameter("clothingsize");
		String shotsize=request.getParameter("shotsize");
		String picture=request.getParameter("picture");
		request.setAttribute("productid", productid);
		request.setAttribute("datilsId", datilsId);
		request.setAttribute("productName", productname);
		request.setAttribute("color", color);
		request.setAttribute("clothingsize", clothingsize);
		request.setAttribute("shotsize", shotsize);
		request.setAttribute("picture", picture);
		//跳转至修改界面
		request.getRequestDispatcher("/changeDatils.jsp").forward(request, response);
	}
	
	//添加商品详情
	private void addDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String productid=request.getParameter("productid");
		String productName=request.getParameter("productname");
		String picture=request.getParameter("picture");
		request.setAttribute("productid", productid);
		request.setAttribute("productName", productName);
		request.setAttribute("picture", picture);
		//跳转至添加界面
		request.getRequestDispatcher("/addDatils.jsp").forward(request, response);
	}
	
	//下架商品详情
	private void deleteDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.创建业务逻辑层对象
		ProductService productService = new ProductServiceImpl();
		String productid=request.getParameter("productid");
		String productName=request.getParameter("productName");
		String datilsId=request.getParameter("datilsId");
		boolean flag=productService.deleteDatils(datilsId);
		productName = URLEncoder.encode(productName,"UTF-8");
		if(flag) {
			response.sendRedirect(request.getContextPath()+"/ProductServlet?method=datils&productid="+productid+"&productName="+productName);
		}	
	}
}
