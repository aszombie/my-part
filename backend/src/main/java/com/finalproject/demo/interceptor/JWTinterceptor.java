package com.finalproject.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import com.finalproject.demo.util.JsonWebTokenUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTinterceptor implements HandlerInterceptor {
	@Autowired
	private JsonWebTokenUtility jsonWebTokenUtility;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		String method =request.getMethod();
		if(!"OPTIONS".equals(method)) {
			String auth =request.getHeader("Authirization");
			JSONObject member =processAuthirizationHeader(auth);
			if(member==null||member.length()==0) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Headers", "*");
				return false;
			}
		}
	return true;
	}	
	
	private JSONObject processAuthirizationHeader(String auth) throws JSONException {
		if(auth!=null&&auth.length()!=0) {
			String token=auth.substring(7);
			String data=jsonWebTokenUtility.validateEncryptedToken(token);
			if(data!=null&&data.length()!=0) {
				return new JSONObject(data);
			}
		}return null;
		
	}
}
