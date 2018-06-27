package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class DB {
	private String className;     //储存数据库驱动类路径
	private String url;           //储存数据库URL
	private String username;      //储存登录数据库的用户名
	private String password;      //储存登录数据库的密码
	private Connection conn = null;
	private ResultSet rs;
	private PreparedStatement  ps;
	public DB(){                  //通过构造方法为属性赋值
		className = "com.mysql.jdbc.Driver";     
		url = "jdbc:mysql://localhost:3306/msz?useUnicode=true&amp;characterEncoding=utf-8";
		username = "root";
		password = "2113";
	}
	/*
	 * @功能 加载数据库驱动程序
	 */
	public void loadDrive(){
		try{
			Class.forName(className);      //加载数据库驱动程序
		}catch(ClassNotFoundException e){
			System.out.println("加载驱动程序失败！");
			e.printStackTrace();           //向控制台输出提示信息
		}
	}
	/*
	 * @功能 获取数据库的连接
	 */
	public void getCon(){
		loadDrive();                 //加载驱动程序
		try{
			conn = DriverManager.getConnection(url,username,password);  //获取连接
		}catch(Exception e){
			System.out.println("连接数据库失败！");
			e.printStackTrace();
		}
	}
	public void closed(){
		try{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		}catch(Exception e){
			System.out.println("关闭数据库失败！");
			e.printStackTrace();
		}
	}
	public int  excuteUpdate(String prepSql,String[] param){
			int num=0;
			//处理SQL，执行SQL
			 getCon();
			 try {
				ps= (PreparedStatement) conn.prepareStatement(prepSql);//得到PreparedStatement对象
				if(param!=null){
				for(int i=0;i<param.length;i++){
					ps.setString(i+1, param[i]);
				}
				System.out.println("DB 已存入"+param.length+"个属性的值");
				}
				//执行SQL语句
				 num=ps.executeUpdate();
			} catch (SQLException e) {
					e.printStackTrace();
			}finally{
		 		closed();
		 	}
			return num;
		}
	public ResultSet excuteQuery(String prepSql,String[] param){
		 getCon();
		 try {
			 ps= conn.prepareStatement(prepSql);//得到PreparedStatement对象
			 if(param!=null){
					for(int i=0;i<param.length;i++){
						ps.setString(i+1, param[i]);
					}
				}
			 System.out.println("DB - sql:"+ps.toString());
			 rs=ps.executeQuery();		 
		 	} catch (SQLException e) {
				e.printStackTrace();
		 	}
		return rs;
	}
	
	public ResultSet excuteQueryPage(String prepSql,String[] param){
		 getCon();
		 try {
			 System.out.println("DB - paramLength:"+param.length);
			 ps= conn.prepareStatement(prepSql);//得到PreparedStatement对象
			 if(param!=null){
				 	int i=1;
					while(i <= (param.length - 2)){
						System.out.println("DB - param["+(i-1)+"]:"+param[i-1]);
						ps.setString(i, param[i-1]);
						i += 1;
					}
					ps.setInt(i,Integer.parseInt(param[i-1]));
				 	ps.setInt(i + 1, Integer.parseInt(param[i]));
				}
			 System.out.println("DB - sqlPage:"+ps.toString());
			 rs=ps.executeQuery();		 
		 	} catch (SQLException e) {
				e.printStackTrace();
		 	}
		return rs;
	}
}