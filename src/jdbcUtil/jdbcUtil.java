package jdbcUtil;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;

public class jdbcUtil {
	private static Properties prop=null;
	private jdbcUtil(){
		
	}
	
	public static Connection getConn() throws ClassNotFoundException, SQLException{
	
	/*
	 * 获取连接
	 */  
		//1.注册数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取连接
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/articlemanager?useUnicode=true&characterEncoding=UTF-8", "root", "");
		return con;
	}
	/*
	 * 关闭连接
	 */
	
	public static void getclose(ResultSet rs,Statement sta,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		if(sta!=null){
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				sta=null;
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	}

}
