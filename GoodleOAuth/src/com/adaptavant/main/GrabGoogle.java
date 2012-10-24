package com.adaptavant.main;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class GrabGoogle extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException{
		HttpSession session=request.getSession(true);
		response.sendRedirect("https://accounts.google.com/o/oauth2/auth?" +
				"scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&" +
				"redirect_uri=http://appusingspring.appspot.com/getDetail&response_type=code&" +
				"client_id=603804890241.apps.googleusercontent.com");
		
	}

}
