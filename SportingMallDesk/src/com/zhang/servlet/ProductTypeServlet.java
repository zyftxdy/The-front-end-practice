package com.zhang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;
import com.zhang.serviceImpl.ProductTypeServiceImpl;

/**
 * Servlet implementation class ProductTypeServlet
 */
@WebServlet("/ProductTypeServlet")
public class ProductTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		findType(request,response);
	}

	//查询商品分类
	protected void findType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//创建逻辑层访问对象
		ProductTypeService productTypeService=new ProductTypeServiceImpl();
		//查询一级分类
		List<ProductType> FirstType=productTypeService.findFirstType();
		//查询二级分类
		List<ProductType> SecondType=productTypeService.findSecondType();
		
		request.setAttribute("FirstType", FirstType);
		request.setAttribute("SecondType", SecondType);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
