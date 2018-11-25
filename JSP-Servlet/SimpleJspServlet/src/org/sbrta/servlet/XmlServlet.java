package org.sbrta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("hlw from XML servlet get method");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("userName");
		
		out.println("Hello "+name);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("hlw from XML servlet post method");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String fullName = request.getParameter("fullName");
		String prof = request.getParameter("prof");
		String food[] = request.getParameterValues("food");
		out.println("Hello "+userName);
		out.println("\n your full name is : "+fullName);
		out.println("\n your profession is : "+prof);
		out.println("choosen food : ");
		for(int i=0; i<food.length; i++){
			out.println(food[i]);
		}
	}
	
	
	
}
