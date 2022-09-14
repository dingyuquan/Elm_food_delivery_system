package com.neusoft.elm.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.elm.controller.FoodController;
import com.neusoft.elm.controller.UserController;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//中文编码处理
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		//获取客户端请求路径
		String path = request.getServletPath();
		
		//根据请求路径将controller组件类名与方法解析
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/")+1);
		//System.out.println(className);
		//System.out.println(methodName);
		
		PrintWriter out = null;
		
		try {
			Class clazz = Class.forName("com.neusoft.elm.controller." + className);
			Object controller = clazz.newInstance();
			Method method = clazz.getMethod(methodName, new Class[] {HttpServletRequest.class});
			Object result = method.invoke(controller, new Object[] {request});
			
			out = response.getWriter();
			ObjectMapper om = new ObjectMapper();
			out.print(om.writeValueAsString(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DispatcherServlet信息：Servlet请求路径：" + path);
			System.out.println("DispatcherServlet信息：类名" + className + "\t方法名：" + methodName);

		} finally {
			out.close();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
