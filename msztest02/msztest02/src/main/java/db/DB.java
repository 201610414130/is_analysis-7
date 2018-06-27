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
	private String className;     //�������ݿ�������·��
	private String url;           //�������ݿ�URL
	private String username;      //�����¼���ݿ���û���
	private String password;      //�����¼���ݿ������
	private Connection conn = null;
	private ResultSet rs;
	private PreparedStatement  ps;
	public DB(){                  //ͨ�����췽��Ϊ���Ը�ֵ
		className = "com.mysql.jdbc.Driver";     
		url = "jdbc:mysql://localhost:3306/msz?useUnicode=true&amp;characterEncoding=utf-8";
		username = "root";
		password = "2113";
	}
	/*
	 * @���� �������ݿ���������
	 */
	public void loadDrive(){
		try{
			Class.forName(className);      //�������ݿ���������
		}catch(ClassNotFoundException e){
			System.out.println("������������ʧ�ܣ�");
			e.printStackTrace();           //�����̨�����ʾ��Ϣ
		}
	}
	/*
	 * @���� ��ȡ���ݿ������
	 */
	public void getCon(){
		loadDrive();                 //������������
		try{
			conn = DriverManager.getConnection(url,username,password);  //��ȡ����
		}catch(Exception e){
			System.out.println("�������ݿ�ʧ�ܣ�");
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
			System.out.println("�ر����ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	public int  excuteUpdate(String prepSql,String[] param){
			int num=0;
			//����SQL��ִ��SQL
			 getCon();
			 try {
				ps= (PreparedStatement) conn.prepareStatement(prepSql);//�õ�PreparedStatement����
				if(param!=null){
				for(int i=0;i<param.length;i++){
					ps.setString(i+1, param[i]);
				}
				System.out.println("DB �Ѵ���"+param.length+"�����Ե�ֵ");
				}
				//ִ��SQL���
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
			 ps= conn.prepareStatement(prepSql);//�õ�PreparedStatement����
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
			 ps= conn.prepareStatement(prepSql);//�õ�PreparedStatement����
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