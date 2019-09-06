package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Bean.News;
import Bean.NewsAuthor;
import jdbcUtil.*;




public class Servlet_sql extends HttpServlet {

	
	/**
		 * Constructor of the object.
		 */
	public Servlet_sql() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		String  method = request.getParameter("method");
		if("login".equals(method)){
			login(request,response);
		}else if("register".equals(method)){
			register(request,response);
		}else if("index".equals(method)){
			index(request,response);
		}else if("detail".equals(method)){
			detail(request,response);
		}else if("myNews".equals(method)){
			myNews(request,response);
		}else if("publish".equals(method)){
			publish(request,response);
		}else if("delete".equals(method)){
			delete(request,response);
		}else if("select".equals(method)){
			select(request,response);
		}else if("update".equals(method)){
			update(request,response);
		}else if("search".equals(method)){
			search(request,response);
		}
		
		
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String  searchdata = request.getParameter("searchdata");
		System.out.println(searchdata);
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			String sql = "select * from news_table where title like '%"+searchdata+"%'" ;
			ResultSet sel=(ResultSet) sta.executeQuery(sql);
			List<News> newslist = new ArrayList<News>();
			while(sel.next())
			{
				News news = new News();
				news.setNews_id( Integer.parseInt(sel.getString("news_id")) );
				news.setUser_id( Integer.parseInt(sel.getString("user_id")) );
				news.setTitle(sel.getString("title"));
				news.setType(sel.getString("type"));
				news.setContent(sel.getString("content"));
				news.setPost_time(sel.getString("post_time"));
				newslist.add(news);
			}
			
			JSONArray json = JSONArray.fromObject(newslist);
			String strJson=json.toString();
			
			data = strJson;
            
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

	private void select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String  news_id = request.getParameter("news_id");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			String sql = "select * from news_table where news_id = '" + news_id +"'"; 
			ResultSet sel=(ResultSet) sta.executeQuery(sql);
			List<News> newslist = new ArrayList<News>();
			News news = new News();
			while(sel.next())
			{
				news.setNews_id( Integer.parseInt(sel.getString("news_id")) );
				news.setUser_id( Integer.parseInt(sel.getString("user_id")) );
				news.setTitle(sel.getString("title"));
				news.setType(sel.getString("type"));
				news.setContent(sel.getString("content"));
				news.setPost_time(sel.getString("post_time"));
			}
			newslist.add(news);	
			JSONArray json = JSONArray.fromObject(newslist);
			String strJson=json.toString();
			
			data = strJson;
            
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String  news_id = request.getParameter("news_id");
		String  title = request.getParameter("title");
		String  type = request.getParameter("type");
		String  content = request.getParameter("content");
		String  post_time = request.getParameter("post_time");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			String sql= "update news_table set title = '"+ title + "',type='" + type + "',content='" + content+ "',post_time='" + post_time +"' where news_id= " + news_id;
			int ins =  sta.executeUpdate(sql);
			if(ins != -1){
				data = "success";
			}else{
				data = "error";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String  news_id = request.getParameter("news_id");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			String sql= "delete from news_table where news_id= '" + news_id + "'" ;
			int ins =  sta.executeUpdate(sql);
			if(ins != -1){
				data = "success";
			}else{
				data = "error";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

	private void publish(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String  user_id = request.getParameter("user_id");
		String  title = request.getParameter("title");
		String  type = request.getParameter("type");
		String  content = request.getParameter("content");
		String  post_time = request.getParameter("post_time");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			String sql= "insert into news_table values(null, '" + user_id + "' , '" + title + "' , '"  + type + "' , '" + content + "' , " + post_time + ")" ;
			int ins =  sta.executeUpdate(sql);
			if(ins != -1){
				data = "success";
			}else{
				data = "error";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

	private void myNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String  user_id = request.getParameter("user_id");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			ResultSet sel=(ResultSet) sta.executeQuery("select * from news_table where user_id = '" + user_id +"'");
			List<News> newslist = new ArrayList<News>();
			while(sel.next())
			{
				News news = new News();
				news.setNews_id( Integer.parseInt(sel.getString("news_id")) );
				news.setUser_id( Integer.parseInt(sel.getString("user_id")) );
				news.setTitle(sel.getString("title"));
				news.setType(sel.getString("type"));
				news.setContent(sel.getString("content"));
				news.setPost_time(sel.getString("post_time"));
				newslist.add(news);	
			}
			JSONArray json = JSONArray.fromObject(newslist);
			String strJson=json.toString();
			
			data = strJson;
            
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
		
		
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String  news_id = request.getParameter("news_id");
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			String sql = "select n.news_id,n.user_id,n.title,n.type,n.content,n.post_time,u.name from news_table n right join user_table u on n.user_id = u.user_id where n.news_id = '" + news_id +"'"; 
			ResultSet sel=(ResultSet) sta.executeQuery(sql);
			List<NewsAuthor> newslist = new ArrayList<NewsAuthor>();
			NewsAuthor news = new NewsAuthor();
			while(sel.next())
			{
				news.setNews_id( Integer.parseInt(sel.getString("news_id")) );
				news.setUser_id( Integer.parseInt(sel.getString("user_id")) );
				news.setTitle(sel.getString("title"));
				news.setType(sel.getString("type"));
				news.setContent(sel.getString("content"));
				news.setPost_time(sel.getString("post_time"));
				news.setUser_name(sel.getString("name"));
			}
			newslist.add(news);	
			JSONArray json = JSONArray.fromObject(newslist);
			String strJson=json.toString();
			
			data = strJson;
            
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String data=null;
		try {
			Connection conn=(Connection) jdbcUtil.getConn();
			Statement sta=(Statement) conn.createStatement(); 
			ResultSet sel=(ResultSet) sta.executeQuery("select * from news_table");
			List<News> newslist = new ArrayList<News>();
			while(sel.next())
			{
				News news = new News();
				news.setNews_id( Integer.parseInt(sel.getString("news_id")) );
				news.setUser_id( Integer.parseInt(sel.getString("user_id")) );
				news.setTitle(sel.getString("title"));
				news.setType(sel.getString("type"));
				news.setContent(sel.getString("content"));
				news.setPost_time(sel.getString("post_time"));
				newslist.add(news);
			}
			
			JSONArray json = JSONArray.fromObject(newslist);
			String strJson=json.toString();
			
			data = strJson;
            
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		out.print(data);
		out.flush();
		out.close();
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
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
	

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
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

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
