package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {

	public AuthorBean getAuthor(int authorCode)
	{
		String query = "SELECT * FROM AUTHOR_TBL WHERE AUTHOR_CODE =?";
		try
		{
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps =con.prepareStatement(query);
			ps.setInt(1, authorCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				AuthorBean ab = new AuthorBean();
				ab.setAuthorCode(rs.getInt(1));
				ab.setAuthorName(rs.getString(2));
				ab.setContactNo(rs.getInt(3));
			return ab;
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
 public AuthorBean getAuthor(String authorName)
 {
		String query = "SELECT * FROM AUTHOR_TBL WHERE AUTHOR_NAME =?";
		try
		{
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps =con.prepareStatement(query);
			ps.setString(1, authorName);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				AuthorBean ab = new AuthorBean();
				ab.setAuthorCode(rs.getInt(1));
				ab.setAuthorName(rs.getString(2));
				ab.setContactNo(rs.getInt(3));
			return ab;
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
 }

	
}
