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
			String TypeName=productAttributes.get("name").toString();
			String picture=productAttributes.get("picture").toString();
			String firstTypeId= productAttributes.get("firstTypeId").toString();
			//3.5����ҵ���߼������ʵ����Ӳ���
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
			//3.6ʵ�ֲ�ѯ�������鿴����Ƿ�ɹ�
			response.sendRedirect(request.getContextPath()+"/ProductTypeServlet?method=secondType&firstTypeId="+firstTypeId);
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
