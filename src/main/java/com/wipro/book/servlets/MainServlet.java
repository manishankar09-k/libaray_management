package com.wipro.book.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Handles direct URL access (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Load main page
        RequestDispatcher rd = request.getRequestDispatcher("Main.html");
        rd.forward(request, response);
    }

    // Handles form submission (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String result = addBook(request);

            if ("SUCCESS".equals(result)) {
                response.sendRedirect("Main.html");
            } else if ("INVALID".equals(result)) {
                response.sendRedirect("Invalid.html");
            } else {
                response.sendRedirect("Failure.html");
            }

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean bookBean = viewBook(isbn);

            if (bookBean == null) {
                response.sendRedirect("Invalid.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", bookBean);

                RequestDispatcher rd =
                        request.getRequestDispatcher("viewServlet");
                rd.forward(request, response);
            }
        }
    }

    // Add book logic
    private String addBook(HttpServletRequest request) {

        String isbn = request.getParameter("isbn");
        String bookName = request.getParameter("bookName");
        String bookType = request.getParameter("bookType");
        String authorName = request.getParameter("authorName");
        String cost = request.getParameter("cost");

        if (isbn == null || bookName == null || bookType == null ||
            authorName == null || cost == null) {
            return "INVALID";
        }

        BookBean bookBean = new BookBean();
        bookBean.setIsbn(isbn);
        bookBean.setBookName(bookName);
        bookBean.setBookType(bookType.charAt(0));
        bookBean.setCost(Float.parseFloat(cost));
        bookBean.setAuthor(new AuthorDAO().getAuthor(authorName));

        Administrator admin = new Administrator();
        return admin.addBook(bookBean);
    }

    // View book logic
    private BookBean viewBook(String isbn) {
        Administrator admin = new Administrator();
        return admin.viewBook(isbn);
    }
}
