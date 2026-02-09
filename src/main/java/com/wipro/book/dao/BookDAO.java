package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {


public int createBook(BookBean bookbean) {
   Connection connection = DBUtil.getDBConnection();
   String insertBook  = "INSERT INTO Book_Tbl VALUES(?,?,?,?,?)";
   String checkAuthor = "SELECT Author_name FROM Author_Tbl WHERE Author_code = ?";
   String insertAuthor = "INSERT INTO Author_Tbl(Author_code,Author_name,Contact_no) VALUES (?,?,?)";

   try {
       
       int authorCode = bookbean.getAuthor().getAuthorCode();
       PreparedStatement checkQuery = connection.prepareStatement(checkAuthor);
       checkQuery.setInt(1, authorCode);
       ResultSet rs = checkQuery.executeQuery();
       
       if(!rs.next()) {
           PreparedStatement insertQuery = connection.prepareStatement(insertAuthor);
           insertQuery.setInt(1, authorCode);
           insertQuery.setString(2, bookbean.getAuthor().getAuthorName());
           insertQuery.setLong(3, bookbean.getAuthor().getContactNo());
           insertQuery.executeUpdate();
       }
       
       PreparedStatement ps = connection.prepareStatement(insertBook);
       ps.setString(1, bookbean.getIsbn());
       ps.setString(2, bookbean.getBookName());
       ps.setString(3, String.valueOf(bookbean.getBookType()));
       ps.setInt(4, authorCode);                              
       ps.setFloat(5, bookbean.getCost());
       ps.executeUpdate();
       
       return 1;
   } catch (SQLException e) {
       e.printStackTrace();
       return 0;
   }
}
public BookBean fetchBook(String isbn) {

Connection connection = DBUtil.getDBConnection();
String query = "SELECT * FROM Book_Tbl WHERE ISBN = ?";
try {
PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, isbn);
ResultSet rs = ps.executeQuery();
if(rs.next()) {
BookBean bookBean =  new BookBean();
bookBean.setIsbn(rs.getString(1));
bookBean.setBookName(rs.getString(2));
bookBean.setBookType(rs.getString(3).charAt(0));
bookBean.setAuthor(new AuthorDAO().getAuthor(rs.getInt(4)));
bookBean.setCost(rs.getFloat(5));
return bookBean;
}
} catch (SQLException e) {
e.printStackTrace();
return null;
}
return null;
	}
		
		
	}

