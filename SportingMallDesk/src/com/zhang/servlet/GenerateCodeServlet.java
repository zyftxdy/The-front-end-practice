package com.zhang.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成验证码
 * @author Administrator
 *
 */
public class GenerateCodeServlet extends HttpServlet {
	private int length;
	private String codes;
	private int width;
	private int height;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		length = Integer.parseInt(config.getInitParameter("length"));
		codes = config.getInitParameter("code");
		width =Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
	}

	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//1.绘制图片
		Graphics2D g = image.createGraphics();
		//2.绘制矩形框
		//2.1设置矩形框填充颜色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		//2.2绘制黑色的边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		
		//3.绘制字符
		int x = width / length;
		int y = height-4;
		
		
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=length;i++){
			//随机生成一个字符
			String c = String.valueOf((codes.charAt(random.nextInt(codes.length()))));
			//设置字体颜色
			int red = random.nextInt(256);
			int blue = random.nextInt(256);
			int green = random.nextInt(256);
			g.setColor(new Color(red,green,blue));
			//设置字体
			Font f = new Font("Arial",Font.PLAIN,random.nextInt(height-height/2)+height/2);
			g.setFont(f);
			g.drawString(c, (i-1)*x+1, y);
			sb.append(c);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("checkcode", sb.toString());
		
		//4.绘制点
		for(int i=1;i<=600;i++){
			int red = random.nextInt(256);
			int blue = random.nextInt(256);
			int green = random.nextInt(256);
			g.setColor(new Color(red,green,blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		OutputStream out =response.getOutputStream();
		//4.生成图片响应客户端的请求
		JPEGImageEncoder encoder= 
				JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		out.flush();
		out.close();		
	}

}
