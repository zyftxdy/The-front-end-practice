package com.zhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;
import com.zhang.service.impl.ProductTypeServiceImpl;

import net.sf.json.JSONArray;

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
		request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("findFirstType".equals(method)){
			FindFirstType(request,response);
		}else if("secondType".equals(method)){
			FindSecondType(request,response);
		}else if("changeName1".equals(method)){
			ChangeName1(request,response);
		}else if("changeName2".equals(method)){
			ChangeName2(request,response);
		}else if("changeTypeName1".equals(method)){
			changeTypeName1(request,response);
		}else if("checkfirstType".equals(method)){
			checkfirstType(request,response);
		}else if("checksecondType".equals(method)){
			checksecondType(request,response);
		}else if("TypeChange".equals(method)){
			TypeChange(request,response);
		}else if("deleteType".equals(method)){
			deleteSecondType(request,response);
		}else if("SecondChange".equals(method)){
			SecondChange(request,response);
		}
	}

	//查询一级分类
	private void FindFirstType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		List<ProductType> list=productService.findFirstType();
		request.setAttribute("TypeList", list);
		request.getRequestDispatcher("/type-manager.jsp").forward(request, response);
	}	
	
	//查询一级分类名称
	private void SecondChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		String firstTypeId=request.getParameter("firstType");
		List<ProductType> listdata=productService.findSecondType(firstTypeId);
		JSONArray listJson = JSONArray.fromObject(listdata);
		//4.响应客户端异步请求发送商品信息(json格式)
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(listJson.toString());
		out.flush();
		out.close();
	}
	
	//查询二级分类名称
	private void TypeChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		List<ProductType> listdata=productService.findFirstType();
		JSONArray listJson = JSONArray.fromObject(listdata);
		//4.响应客户端异步请求发送商品信息(json格式)
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(listJson.toString());
		out.flush();
		out.close();
	}	
		
	//查询二级分类
	private void FindSecondType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		String firstTypeId=request.getParameter("firstTypeId");
		List<ProductType> list=productService.findSecondType(firstTypeId);
		request.setAttribute("TypeList", list);
		request.setAttribute("firstTypeId", firstTypeId);
		request.getRequestDispatcher("/secondType-manager.jsp").forward(request, response);
	}
	
	//查询一级分类名称
	private void checkfirstType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		String firstTypeName=request.getParameter("typeName");
		int count=Integer.parseInt(productService.FirstTypeName(firstTypeName).toString());
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		if(count>0){
			out.print("false");
		}else{
			out.print("true");
		}
		out.flush();
		out.close();		
	}
	
	//查询二级分类名称
	private void checksecondType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		String secondTypeName=request.getParameter("typeName");
		int count=Integer.parseInt(productService.SecondTypeName(secondTypeName).toString());
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		if(count>0){
			out.print("false");
		}else{
			out.print("true");
		}
		out.flush();
		out.close();		
	}	
	
	//修改一级分类名称
	private void ChangeName1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstTypeId=request.getParameter("firstTypeId");
		String firstTypeName=request.getParameter("firstTypeName");
		request.setAttribute("firstTypeName", firstTypeName);
		request.setAttribute("firstTypeId", firstTypeId);
		request.getRequestDispatcher("/FirstType-change.jsp").forward(request, response);
	}
	private void changeTypeName1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();
		String firstTypeId=request.getParameter("typeId");
		String firstTypeName=request.getParameter("typeName");
		ProductType p=new ProductType();
		p.setTypeId(firstTypeId);
		p.setTypeName(firstTypeName);
		boolean flag=productService.updateFirstType(p);
		if(flag){
			FindFirstType(request,response);
		}
	}
	
	//修改二级分类名称
	private void ChangeName2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();		
		String secondTypeId=request.getParameter("secondTypeId");
		String secondTypeName=request.getParameter("secondTypeName");
		String firstTypeId=request.getParameter("firstTypeId");
		String picture=productService.findPicture(secondTypeId).toString();
		request.setAttribute("secondTypeName", secondTypeName);		
		request.setAttribute("secondTypeId", secondTypeId);
		request.setAttribute("firstTypeId",firstTypeId);
		request.setAttribute("picture", picture);
		request.getRequestDispatcher("/secondType-change.jsp").forward(request, response);
	}

	
	//删除第二品类
	private void deleteSecondType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//创建逻辑层对象
		ProductTypeService productService=new ProductTypeServiceImpl();	
		String firstTypeId=request.getParameter("firstTypeId");
		String secondTypeId=request.getParameter("secondTypeId");
		boolean flag=productService.deleteSecondType(secondTypeId);
		if(flag){
			List<ProductType> list=productService.findSecondType(firstTypeId);
			request.setAttribute("TypeList", list);
			request.setAttribute("firstTypeId", firstTypeId);
			request.getRequestDispatcher("/secondType-manager.jsp").forward(request, response);				
		}
	}
}
