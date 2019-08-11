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
	
	//��ѯ��Ʒ�б�
	private void findProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�����߼�����ʶ���
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
		//2.3��ǰҳ��(Ĭ��ֵΪ1)
		int currentpage = 1;
		//2.4��ȡ�ͻ����ύ�ĵ�ǰҳ
		String result =request.getParameter("currentpage");
		if(result!=null && !"".equals(result)){
			currentpage = Integer.parseInt(result);
		}
		//Ĭ������ʽ
		int Sorting = 1;
		String Sortings=request.getParameter("Sorting");
		if(Sortings!=null && !"".equals(Sortings)){
			Sorting = Integer.parseInt(Sortings);
		}
		List<Map<String,Object>> products =productService.findProducts(currentpage,Common.PAGERECORDS,Sorting,parameters);
		//3.����ѯ���(���϶���)��ŵ�request������
		request.setAttribute("productName", productName);
		request.setAttribute("firstTypeId", firstTypeId);
		request.setAttribute("secondTypeId", secondTypeId);
		request.setAttribute("products", products);
		request.setAttribute("Sorting", Sorting);
		request.setAttribute("totalRecords", rows);
		request.setAttribute("totalpages", totalPages);
		request.setAttribute("currentpage", currentpage);
		//4.��ת������ת����ѯҳ��
		request.getRequestDispatcher("/productList.jsp").forward(request, response);		
	}

	//��ѯ��Ʒ����
	private void findDatils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		ProductService productService=new ProductServiceImpl();
		//��ȡ��ѯ����
		String productId=request.getParameter("productId");
		String productName=request.getParameter("productName");
		String productDesc=request.getParameter("productDesc");
		String picture=request.getParameter("picture");
		//��ѯ��Ʒ��ͼ۸�
		double minPrice=productService.getMinPrice(productId);
		//��ѯ��Ʒ��߼۸�
		double maxPrice=productService.getMaxPrice(productId);
		//��ѯ��Ʒ�ܿ��
		int nums=productService.getNums(productId);
		//��ѯ��Ʒ��ɫ�б�
		List<String> colours=productService.getColour(productId);
		//��ѯ��Ʒ�ߴ��б�
		List<String> cloths=productService.getClothing(productId);
		//��ѯ��ƷЬ���б�
		List<String> shots=productService.getShot(productId);
		//��ѯ��������
		int rows=productService.getCommentRows(productId);
		//��ѯ��Ʒ��������
		List<Map<String,Object>> comments=productService.findComment(1, Common.PAGERECORDS, productId);
		//3.����ѯ���(���϶���)��ŵ�request������
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
		//��ת
		request.getRequestDispatcher("/productDatils.jsp").forward(request, response);
	}
	
	//�����������²�ѯ��Ʒ����
	private void findDatilsByCd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		ProductService productService=new ProductServiceImpl();
		//��ȡ��ѯ����
		String productId=request.getParameter("productId");
		String color=request.getParameter("color");
		String shotsize=request.getParameter("shotsize");
		String clothingsize=request.getParameter("clothingsize");
		//System.out.println(color+shotsize+clothingsize);
		Object[] parameters = new Object[2];
		//1.2���ò�ѯ����
		if(shotsize!=null && !"".equals(shotsize)){
			parameters[0] = shotsize;
		}
		if(clothingsize!=null && !"".equals(clothingsize)){
			parameters[1] = clothingsize;
		}
		//2.����ҵ���߼����еĲ�ѯ����
		double price=productService.getPriceByCd(productId, color, parameters);
		int nums=productService.getNumsByCd(productId, color, parameters);
		String picture=productService.getPictureByCd(productId, color, parameters);
		String _price=""+price;
		String _nums=""+nums;
		//3.��Ӧ�ͻ��˵��첽����
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
	
	//�����������²�ѯ��Ʒ�����Ƿ����
	private void checkCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
		ProductService productService=new ProductServiceImpl();
		//��ȡ��ѯ����
		String productId=request.getParameter("productId");
		String color=request.getParameter("color");
		String shotsize=request.getParameter("shotsize");
		String clothingsize=request.getParameter("clothingsize");
		//System.out.println(color+shotsize+clothingsize);
		Object[] parameters = new Object[2];
		//1.2���ò�ѯ����
		if(shotsize!=null && !"".equals(shotsize)){
			parameters[0] = shotsize;
		}
		if(clothingsize!=null && !"".equals(clothingsize)){
			parameters[1] = clothingsize;
		}	
		int count=productService.CheckCount(productId, color, parameters);
		//3.��Ӧ�ͻ��˵��첽����
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
	
	//�����Ʒ����ʱ���ﳵ
	private void buyProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//����ҵ���߼������
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
			//1.2���ò�ѯ����
			if(shotsize!=null && !"".equals(shotsize)){
				parameters[0] = shotsize;
			}
			if(clothingsize!=null && !"".equals(clothingsize)){
				parameters[1] = clothingsize;
			}
			//��ѯ��Ʒ����ID
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
