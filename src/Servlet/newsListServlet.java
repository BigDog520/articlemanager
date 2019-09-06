package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import jdbcUtil.jdbcUtil;

public class newsListServlet extends HttpServlet {

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

		String data=null;
		String  name = request.getParameter("name");
		String  password = request.getParameter("password");
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement();  
			ResultSet re=(ResultSet) sta.executeQuery("select * from user_table where name = '" + name + "' and password = '" + password + "'");
			
			if(re.next()){
				String id = re.getString("user_id");
				data=id;
			}else{
				data="error";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		
		out.flush();
		out.close();
	}

}
