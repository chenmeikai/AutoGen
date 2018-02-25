/**   
 * Copyright © 2018 
 * @Package: GenServiceImpl.java 
 * @author: Administrator   
 * @date: 2018年2月25日 下午4:32:17 
 */
package www.chenmeikai.com.service.serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.springframework.stereotype.Service;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import www.chenmeikai.com.base.Attribute;
import www.chenmeikai.com.base.Entity;
import www.chenmeikai.com.service.GenService;
import www.chenmeikai.com.utils.ProUtils;
import www.chenmeikai.com.utils.StringUtil;

/**      
 * @Description:TODO  
 * @author: cmk 
 * @date:   2018年2月25日 下午4:32:17     
 */

@Service
public class GenServiceImpl implements GenService {
	
	//获得连接
	@Override
	public Connection getConnect(String driver, String url, String userName, String password) {
		Connection conn =null;
		try {
			Class.forName(driver);
			Properties props = new Properties();  
            props.put("remarksReporting", "true");  
            props.put("user", userName);  
            props.put("password", password);  
		    conn = DriverManager.getConnection(url, props);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}
	
	
	//根据表名，生成一个实体类
	@Override
	public void genModel(Connection con, String tableName) {
	}
    
	//生成所有实体类
	@Override
	public void genModels(Connection conn) {
		DatabaseMetaData dbmd = null; 
		try {
			//获得元数据
			dbmd = conn.getMetaData();
			//获得表结果集
			ResultSet tabletSets = dbmd.getTables(null, "%", null, new String[] { "TABLE" });
			//遍历
			while (tabletSets.next()) {
				Entity entity =new Entity();
				/**
				 * 表名转换为实体类名
				 */
				String tName=tabletSets.getString("TABLE_NAME");
				String modelName =StringUtil.convertTableTName(tName);
				entity.setName(modelName);
				/**
				 * 字段转换为属性
				 */
				ResultSet rs =dbmd.getColumns(null, getSchema(conn),tName, "%");
				List<Attribute> attributes =new ArrayList<>();
				while(rs.next()){  
                    //System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));  
                    //属性名
					String columnName = rs.getString("COLUMN_NAME");  
                    String name =StringUtil.convertAttributeName(columnName);
                    //属性类型
                    String typeName = rs.getString("TYPE_NAME"); 
                    String type =StringUtil.changeDbType(typeName);
                    //注释
                    String remarks = rs.getString("REMARKS");  
                    if(remarks == null || remarks.equals("")){  
                        remarks = columnName;  
                    }  
                    Attribute attribute =new Attribute(name, type,remarks); 
                    attributes.add(attribute);
                } 
				//设置属性集合
				entity.setAttributes(attributes);
				
				Map<String,Object> map =new HashMap<>();
				//装入表信息
				map.put("entity", entity);
				//装入package路径
				String packagePath =ProUtils.getProperty("gen.entity.package.path");
				map.put("packagePath", packagePath);
				//模板文件
				String ftlName="entity.ftl";
				/**
				 * 生成文件路径
				 */
				String basePath =Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1)+ProUtils.getProperty("gen.entity.target.path");
				File filePath =new File(basePath);
				if(!filePath.exists()) {
					filePath.mkdirs();
				}
				String targetPath =basePath+modelName+".java";
				create(map,ftlName,targetPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//生成mapper
	@Override
	public Integer genMappers(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//生成mapper映射文件
	@Override
	public Integer genMappersXml(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//生成service
	@Override
	public Integer genService(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
    
	//生成controller
	@Override
	public Integer genController(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//关闭连接
	@Override
	public Integer disConnect(Connection connect) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//其他数据库不需要这个方法 oracle和db2需要  
    private  String getSchema(Connection conn) throws Exception {  
        String schema;  
        schema = conn.getMetaData().getUserName();  
        if ((schema == null) || (schema.length() == 0)) {  
            throw new Exception("ORACLE数据库模式不允许为空");  
        }  
        return schema.toUpperCase().toString();  
    } 
    
    private void create(Map<String,Object> map,String ftlName,String targetPath) {
    	
    	FileWriter out = null;
    	Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
    	
    	// 设置模板路径
    	String ftlPath =ProUtils.getProperty("gen.ftl.path");
    	cfg.setClassForTemplateLoading(GenServiceImpl.class, ftlPath);
    	// 设置默认字体
    	cfg.setDefaultEncoding("utf-8");
    	// 获取模板
    	Template template;
		try {
			template = cfg.getTemplate(ftlName);
			/**
	    	 * 设置输出文件
	    	 */
	    	File file = new File(targetPath);
	    	if(!file.exists()) {
	    		file.createNewFile();
	    	}
	    	//设置输入流
	    	out = new FileWriter(file);
	    	//模板输出
	    	template.process(map, out);
	    	
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (TemplateException e) {
			e.printStackTrace();
		}finally {
			if(out !=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }

}