package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import jdbcUtil.jdbcUtil;

public class registerServlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String  name = request.getParameter("username");
		String  password = request.getParameter("password");
		String  sex = request.getParameter("sex");
		String  phone = request.getParameter("phone");
		String  address = request.getParameter("address");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			ResultSet sel=(ResultSet) sta.executeQuery("select * from user_table where name = '" + name + "'");
			if(!sel.next()){
				String sql= "insert into user_table values(null, '" + name + "' , '" + password + "' , '"  + phone + "' , '" + sex + "' , '" + address + "')" ;
				int ins =  sta.executeUpdate(sql);
				data = String.valueOf(ins);
			}else{
				data = "exist";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

}
