/**   
 * Copyright © 2018 
 * @Package: GenMain.java 
 * @author: Administrator   
 * @date: 2018年2月25日 下午10:52:50 
 */
package www.chenmeikai.com.config;

import java.sql.Connection;

import www.chenmeikai.com.service.GenService;
import www.chenmeikai.com.service.serviceImpl.GenServiceImpl;

/**      
 * @Description:TODO  
 * @author: cmk 
 * @date:   2018年2月25日 下午10:52:50     
 */
public class GenMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.79.35.34:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		String userName = "root";
		String password = "chenmeikai2018";
		String tableName ="city";
		
		GenService genService =new GenServiceImpl();
		Connection conn =genService.getConnect(driver, url, userName, password);
		genService.genModels(conn);

	}

}