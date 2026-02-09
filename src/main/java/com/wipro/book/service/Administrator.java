package com.wipro.book.service;


import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.dao.BookDAO;

public class Administrator {
AuthorDAO a = new AuthorDAO();
BookDAO b = new BookDAO();
public String addBook(BookBean bookBean)
{
	if(bookBean==null || bookBean.getBookName().isEmpty() || bookBean.getIsbn().isEmpty()
			|| (bookBean.getBookType()!=' '
			&& bookBean.getBookType()!='G' && bookBean.getBookType()!='T')
			|| bookBean.getCost()==0 || bookBean.getAuthor() == null || bookBean.getAuthor().getAuthorName().isEmpty()) {
			return "INVALID";
			}

    int result = b.createBook(bookBean);

    if(result == 1)
        return "SUCCESS";

    return "FAILURE";
}
public BookBean viewBook(String isbn)
{
	BookBean result = b.fetchBook(isbn);
	return result;
}
	
}
