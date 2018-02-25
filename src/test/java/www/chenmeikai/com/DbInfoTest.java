/**   
 * Copyright © 2018 
 * @Package: DbInfoTest.java 
 * @author: Administrator   
 * @date: 2018年2月25日 上午11:37:56 
 */
package www.chenmeikai.com;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import www.chenmeikai.com.utils.DbInfoUtils;

/**      
 * @Description:TODO  
 * @author: cmk 
 * @date:   2018年2月25日 上午11:37:56     
 */
public class DbInfoTest {
	
	
	@Test
	public void test() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.79.35.34:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		String userName = "root";
		String password = "chenmeikai2018";
		String tableName ="city";
		
		List<Map<String,Object>> list =  DbInfoUtils.getTableInfo(driver,url,userName,password,tableName);
		System.out.println(list);  
	}

}
