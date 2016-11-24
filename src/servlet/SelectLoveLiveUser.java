package servlet;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lovelive")
public class SelectLoveLiveUser extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lovelive";
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "hanxiao";
	
	
	public SelectLoveLiveUser(){
		super();
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
//		String username = request.getParameter("username");
//		System.out.println("cookies:"+request.getCookies());
//		System.out.println("session:"+request.getSession());
//		System.out.println("locale:"+request.getLocale());
//		System.out.println("inputStream:"+request.getInputStream());
//		System.out.println("type:"+request.getAuthType());
//		System.out.println("encode:"+request.getCharacterEncoding());
//		System.out.println("path:"+request.getContextPath());
//		System.out.println("method:"+request.getMethod());
//		System.out.println("pathInfo:"+request.getPathInfo());
//		System.out.println("prot:"+request.getServerPort());
//		System.out.println(username);
		
//		Enumeration headerNames = request.getHeaderNames();
//		while(headerNames.hasMoreElements()){
//			String headerName = (String)headerNames.nextElement();
//			System.out.println(headerName+":"+request.getHeader(headerName));
//		}
		
		Connection conn = null;
		Statement stat = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			stat = conn.createStatement();
			String sql ="select * from loveliveuser";
			
			ResultSet result = stat.executeQuery(sql);
			
			PrintWriter out = response.getWriter();
			String title = "数据库测试";
			String docType = "<!DOCTYPE html>\n";
			out.println(docType + 
					"<html>\n" + 
					"<head><title>" + title + "</title></head>\n" + 
					"</body><h1>lovelive数据</h1>\n");
			while(result.next()){
				int id = result.getInt("id");
				String username =result.getString("username");
				String password = result.getString("password");
//				System.out.println("id:"+id);
//				System.out.println("username:"+username);
//				System.out.println("password:"+password);
				out.println("ID:" + id);
				out.print("<br/>UserName:"+username);
				out.println("<br/>PassWord:"+password+"<br/>");
			}
			out.println("</body></html>");
			
			result.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(stat != null){
					stat.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}

}
