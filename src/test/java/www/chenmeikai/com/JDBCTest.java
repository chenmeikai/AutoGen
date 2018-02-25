/**   
 * Copyright © 2018 
 * @Package: JDBCTest.java 
 * @author: Administrator   
 * @date: 2018年2月25日 上午9:44:42 
 */
package www.chenmeikai.com;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;

/**
 * @Description:TODO
 * @author: cmk
 * @date: 2018年2月25日 上午9:44:42
 */
public class JDBCTest {
	
	public static void main(String[] args) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.79.35.34:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		String username = "root";
		String password = "chenmeikai2018";
		
		//连接数据库
		Connection conn =getConn(driver,url,username,password);
		
		//遍历表名
		Set<String> names =getTableNameByCon(conn);
		System.out.println("-------遍历表名开始-------");
		for(String name :names) {
			System.out.println(name);
		}
		System.out.println("-------遍历表名结束-------");
		
		
	}
	


    
	/**
	 * jdbc连接数据库
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	private static Connection getConn(String driver,String url,String username,String password) {
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * 获取数据库表名
	 * @param con
	 * @return
	 */
	private static Set<String> getTableNameByCon(Connection con) {
        Set<String> set = new HashSet<String>();
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, null,
                    new String[] { "TABLE" });
            while (rs.next()) {
                set.add(rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return set;
    }
	

}
