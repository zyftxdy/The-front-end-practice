package com.zhang.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhang.model.Product;
import com.zhang.service.ProductService;
import com.zhang.service.impl.ProductServiceImpl;



/**
 * Servlet implementation class AddUpdateServlet
 */
@WebServlet("/AddUpdateServlet")
public class AddUpdateServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.����DiskFileItemFactory����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2.���� ServletFileUpload���Ķ���
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("UTF-8");
		fileUpload.setFileSizeMax(1024 * 1024 * 3);// ��ʾ3M��С
		// 3.ʵ���ļ����ϴ�����
		// 3.1 ͨ��ServletFileUpload����List<FileItem>����(3. ʹ�ý�����������request����)
		try {
			//3.2����Map���϶�������Ʒ��������������ֵ
			Map<String,Object> productAttributes= new HashMap<String,Object>();
			List<FileItem> list = fileUpload.parseRequest(request);			
			// 3.3�������form���ύ��Ԫ��
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					// ��ǰԪ��Ϊ��ͨ����
					processFormFiled(fileItem,productAttributes);
				} else {
					// �ļ���
					processFormFile(fileItem,productAttributes);
				}
			}
			//3.4 ����Map���ϣ����������Ʒ�Ķ���	
			Product product = new Product();
			product.setFirstTypeId(productAttributes.get("firstType").toString());
			product.setSecondTypeId(productAttributes.get("secondType").toString());
			product.setProductName(productAttributes.get("productName").toString());
			product.setProductDesc(productAttributes.get("productDesc").toString());
			product.setPicture(productAttributes.get("picture").toString());
			//3.5����ҵ���߼������ʵ����Ӳ���
			ProductService productService = new ProductServiceImpl();
			String methodName = productAttributes.get("method").toString();
			if("addproduct".equals(methodName)){
				//3.5.1���
				productService.addProduct(product);
			}else if("updateproduct".equals(methodName)){
				String productid=productAttributes.get("productId").toString();
				product.setProductId(productid);
				productService.updateProduct(product);
			}
			//3.6ʵ�ֲ�ѯ�������鿴����Ƿ�ɹ�
			response.sendRedirect(request.getContextPath()+"/ProductServlet");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ʵ���ļ��ϴ�����
	 * @param fileItem
	 */
	private void processFormFile(FileItem fileItem,Map<String,Object> productAttributes){
		try {
			//1.��ȡ�ļ�������
			InputStream is =fileItem.getInputStream();
			//2.����File����
			String directoryRealPath = 
					this.getServletContext().getRealPath("/upload");
			
			//2.1��ȡ�ļ���
			String fileFieldName = fileItem.getFieldName();
			String fileName =fileItem.getName();
			String saveFileName = UUID.randomUUID()+"_"+fileName;
			File file = new File("d:/images",saveFileName);
			//3.�������������ʵ��д�ļ�����
			FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.close();
			is.close();
			//4.����ǰ�ļ���·��+�ļ�����ŵ�Map������
			productAttributes.put(fileFieldName, saveFileName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ��ͨ�����Ԫ������
	 * @param fileItem
	 */
	private void processFormFiled(FileItem fileItem,Map<String,Object> productAttributes){
		try {
			String fildName = fileItem.getFieldName();
			String fildValue = fileItem.getString("utf-8");			
			productAttributes.put(fildName, fildValue);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
