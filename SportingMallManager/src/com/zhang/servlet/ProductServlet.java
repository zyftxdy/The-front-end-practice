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

	//��ѯ��Ʒ
	private void findProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.����ҵ���߼������
		ProductService productService = new ProductServiceImpl();
		//2.��ȡ��ѯ����
		String productId=request.getParameter("productId");
		String productName=request.getParameter("productName");
		Object[] parameters = new Object[2];
		//1.2���ò�ѯ����
		if(productId!=null && !"".equals(productId)){
			parameters[0] = productId;
		}
		if(productName!=null && !"".equals(productName)){
			parameters[1] = productName;
		}
		//2.����ҵ���߼����еĲ�ѯ����
		//2.1��ȡ���ϲ�ѯ�������ܼ�¼��
		int rows =productService.getRows(parameters);
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
		List<Map<String,Object>> products=productService.FindProducts(currentpage, Common.PAGERECORDS, parameters);
		//3.����ѯ���(���϶���)��ŵ�request������
		request.setAttribute("products", products);
		request.setAttribute("productId", productId);
		request.setAttribute("productName", productName);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/product-manager.jsp").forward(request, response);
	}
	
	//�޸���Ʒ��Ϣ
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
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
		//��ת���޸�ҳ��
		request.getRequestDispatcher("/productChange.jsp").forward(request, response);
	}
	
	//�޸���Ʒ״̬
	private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		ProductService productService = new ProductServiceImpl();	
		String productid=request.getParameter("productid");
		boolean flag=productService.updateStatus(productid);
		if(flag){
			findProducts(request,response);
		}
	}
	
	//�ָ���Ʒ״̬
	private void recoverStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		ProductService productService = new ProductServiceImpl();	
		String productid=request.getParameter("productid");
		boolean flag=productService.recoverStatus(productid);
		if(flag){
			findProducts(request,response);
		}
	}
	
	//�鿴��Ʒ����
	private void datils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
		ProductService productService = new ProductServiceImpl();	
		String productid=request.getParameter("productid");
		String productName=request.getParameter("productName");
		//2.��ȡ��ѯ����
		String color=request.getParameter("color");
		String clothingsize=request.getParameter("clothingsize");
		String shotsize=request.getParameter("shotsize");
		Object[] parameters = new Object[3];
		//1.2���ò�ѯ����
		if(color!=null && !"".equals(color)){
			parameters[0] = color;
		}
		if(clothingsize!=null && !"".equals(clothingsize)){
			parameters[1] = clothingsize;
		}
		if(shotsize!=null && !"".equals(shotsize)){
			parameters[2] = shotsize;
		}
		//2.����ҵ���߼����еĲ�ѯ����
		//2.1��ȡ���ϲ�ѯ�������ܼ�¼��
		int rows =productService.getDatilsRows(productid, parameters);
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
		List<Map<String,Object>> products=productService.FindDatils(currentpage,Common.PAGERECORDS, productid, parameters);
		int colors=productService.getColor(productid);
		int clothingsizes=productService.getClothing(productid);
		int shotsizes=productService.getShot(productid);
		//3.����ѯ���(���϶���)��ŵ�request������
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
		//4.��ת����ѯҳ��
		request.getRequestDispatcher("/product_datils.jsp").forward(request, response);		
	}
	
	//�޸���Ʒ����
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
		//��ת���޸Ľ���
		request.getRequestDispatcher("/changeDatils.jsp").forward(request, response);
	}
	
	//�����Ʒ����
	private void addDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String productid=request.getParameter("productid");
		String productName=request.getParameter("productname");
		String picture=request.getParameter("picture");
		request.setAttribute("productid", productid);
		request.setAttribute("productName", productName);
		request.setAttribute("picture", picture);
		//��ת����ӽ���
		request.getRequestDispatcher("/addDatils.jsp").forward(request, response);
	}
	
	//�¼���Ʒ����
	private void deleteDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.����ҵ���߼������
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
