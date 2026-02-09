package com.wipro.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.book.bean.BookBean;

@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		BookBean bookBean =(BookBean)session.getAttribute("book");
		out.print("<html><body>");
		out.print("Book title: " +bookBean.getBookName() + "<br><br>");
		out.print("Author Name : "+bookBean.getAuthor().getAuthorName() +"<br><br>");
		out.print("Author Contact :" +bookBean.getAuthor().getContactNo() +"<br><br>");
		out.print("Book Price: "+bookBean.getCost() +"<br><br>");
		out.print("Book ISBN: " +bookBean.getIsbn() +"<br><br>");
		out.print("</body></html>");
		
	}

}
