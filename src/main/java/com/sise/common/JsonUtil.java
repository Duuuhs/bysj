package com.sise.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
public class JsonUtil {
	public static void outPrint(HttpServletResponse response,Object obj){
		Gson gson = new Gson();
		String gsonStr = gson.toJson(obj);   //obj 为要转化为json的java对象
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			response.getWriter().write(gsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void jsoncallback(HttpServletRequest request,HttpServletResponse response,Object obj){
		Gson gson = new Gson();
		String gsonStr = gson.toJson(obj);   //obj 为要转化为json的java对象
		
		response.setContentType("application/json;charset=utf-8"); 		
		//页面输入正常,fireBug也正常
		String jsoncallback = request.getParameter("jsoncallback");
		if(jsoncallback!=null && !jsoncallback.equals("")){
			gsonStr = jsoncallback + "("+gsonStr+")";
		}
		try {
			response.getWriter().write(gsonStr);//通过HttpServletResponser输出Json数据
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
