/**   
 * Copyright © 2018 
 * @Package: DBInfoUtils.java 
 * @author: Administrator   
 * @date: 2018年2月25日 上午11:29:00 
 */
package www.chenmeikai.com.utils;

import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;
import java.util.Properties;  

/**      
 * @Description:JDBC 
 * @author: cmk 
 * @date:   2018年2月25日 上午11:29:00     
 */

public class DbInfoUtils {  
    
    /** 
     * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释 
     * @param driver 数据库连接驱动 
     * @param url 数据库连接url 
     * @param user  数据库登陆用户名 
     * @param pwd 数据库登陆密码 
     * @param table 表名 
     * @return Map集合 
     */  
    public static List<Map<String,Object>> getTableInfo(String driver,String url,String userName,String password,String table){  
        List<Map<String,Object>> result = new ArrayList<>();  
          
        Connection conn = null;       
        DatabaseMetaData dbmd = null;  
          
        try {  
            conn = getConnections(driver,url,userName,password);  
              
            dbmd = conn.getMetaData();  
            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[] { "TABLE" });  
              
            while (resultSet.next()) {  
                String tableName=resultSet.getString("TABLE_NAME");  
                System.out.println(tableName);  
                  
                if(tableName.equals(table)){  
                    ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn),tableName, "%");  
  
                    while(rs.next()){  
                        //System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));  
                        Map<String,Object> map = new HashMap<>();  
                        String columnName = rs.getString("COLUMN_NAME");  
                        map.put("columnName",columnName);  
                          
                        String remarks = rs.getString("REMARKS");  
                        if(remarks == null || remarks.equals("")){  
                            remarks = columnName;  
                        }  
                        map.put("remarks",remarks);  
                          
                        String typeName = rs.getString("TYPE_NAME");  
                        map.put("typeName",typeName);  
                          
                        map.put("javaType", changeDbType(typeName));  
                        result.add(map);  
                    }  
                }  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
          
        return result;  
    }  
      
    private static String changeDbType(String dbType) {  
        dbType = dbType.toUpperCase();  
        switch(dbType){  
            case "VARCHAR":  
            case "VARCHAR2":  
            case "CHAR": 
            case "LONGTEXT":
            case "TINYTEXT":
            case "TEXT":
                return "String";  
            case "NUMBER":  
            case "DECIMAL":  
                return "BigDecimal";  
            case "INT":  
            case "SMALLINT":  
            case "INTEGER":  
                return "Integer";  
            case "BIGINT":  
                return "Long";  
            case "DATETIME":  
            case "TIMESTAMP":  
            case "DOUBLE":  
                return "Double";  
            case "FLOAT":  
            	return "Float";  
            case "DATE":  
            	return "Date";  
            default:  
                return "String";  
        }  
    }  
  
    /**
     *   
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @return
     * @throws Exception
     */
    private static Connection getConnections(String driver,String url,String user,String pwd) throws Exception {  
        Connection conn = null;  
        try {  
            Properties props = new Properties();  
            props.put("remarksReporting", "true");  
            props.put("user", user);  
            props.put("password", pwd);  
            Class.forName(driver);  
            conn = DriverManager.getConnection(url, props);  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw e;  
        }  
        return conn;  
    }  
      
    //其他数据库不需要这个方法 oracle和db2需要  
    private static String getSchema(Connection conn) throws Exception {  
        String schema;  
        schema = conn.getMetaData().getUserName();  
        if ((schema == null) || (schema.length() == 0)) {  
            throw new Exception("ORACLE数据库模式不允许为空");  
        }  
        return schema.toUpperCase().toString();  
  
    }  
  
    public static void main(String[] args) {  
          
        /*//这里是Oracle连接方法  
        String driver = "oracle.jdbc.driver.OracleDriver";  
        String url = "jdbc:oracle:thin:@192.168.12.44:1521:orcl";  
        String user = "bdc";  
        String pwd = "bdc123";  
        //String table = "FZ_USER_T";  
        String table = "FZ_USER_T"; */ 
          
        //mysql  
    	String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.79.35.34:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		String username = "root";
		String password = "chenmeikai2018";
        String table = "city"; 
          
          
        List<Map<String,Object>> list = getTableInfo(driver,url,username,password,table);  
        System.out.println(list);  
    }  
      
}  
