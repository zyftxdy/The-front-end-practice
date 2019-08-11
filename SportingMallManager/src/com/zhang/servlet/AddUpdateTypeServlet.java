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

import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;
import com.zhang.service.impl.ProductTypeServiceImpl;

/**
 * Servlet implementation class AddUpdateTypeServlet
 */
@WebServlet("/AddUpdateTypeServlet")
public class AddUpdateTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.创建DiskFileItemFactory对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2.创建 ServletFileUpload核心对象
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("UTF-8");
		fileUpload.setFileSizeMax(1024 * 1024 * 3);// 表示3M大小
		// 3.实现文件的上传操作
		// 3.1 通过ServletFileUpload创建List<FileItem>对象(3. 使用解析器来解析request对象)
		try {
			//3.2创建Map集合对象存放商品的属性名和属性值
			Map<String,Object> productAttributes= new HashMap<String,Object>();
			List<FileItem> list = fileUpload.parseRequest(request);			
			// 3.3逐个遍历form表单提交的元素
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					// 当前元素为普通表单项
					processFormFiled(fileItem,productAttributes);
				} else {
					// 文件域
					processFormFile(fileItem,productAttributes);
				}
			}
			//3.4 遍历Map集合，创建添加商品的对象	
			String TypeName=productAttributes.get("name").toString();
			String picture=productAttributes.get("picture").toString();
			String firstTypeId= productAttributes.get("firstTypeId").toString();
			//3.5创建业务逻辑层对象，实现添加操作
			ProductTypeService productTypeService = new ProductTypeServiceImpl();
			String methodName = productAttributes.get("method").toString();
			if("addType".equals(methodName)){
				productTypeService.addSecondTypes(TypeName, firstTypeId, picture);
			}else if("updateType".equals(methodName)){
				String typeId=productAttributes.get("typeId").toString();
				ProductType productType = new ProductType();
				productType.setTypeId(typeId);
				productType.setTypeName(TypeName);
				productType.setPicture(picture);
				productTypeService.updateSecondType(productType);
			}
			//3.6实现查询操作，查看添加是否成功
			response.sendRedirect(request.getContextPath()+"/ProductTypeServlet?method=secondType&firstTypeId="+firstTypeId);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 实现文件上传操作
	 * @param fileItem
	 */
	private void processFormFile(FileItem fileItem,Map<String,Object> productAttributes){
		try {
			//1.获取文件输入流
			InputStream is =fileItem.getInputStream();
			//2.创建File对象
			String directoryRealPath = 
					this.getServletContext().getRealPath("/upload");
			
			//2.1获取文件名
			String fileFieldName = fileItem.getFieldName();
			String fileName =fileItem.getName();
			String saveFileName = UUID.randomUUID()+"_"+fileName;
			File file = new File("d:/images",saveFileName);
			//3.创建输出流对象实现写文件操作
			FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.close();
			is.close();
			//4.将当前文件的路径+文件名存放到Map集合中
			productAttributes.put(fileFieldName, saveFileName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取普通表单项的元素内容
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
