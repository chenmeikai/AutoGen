/**
 * Copyright © 2018
 *
 * @Package: GenService.java
 * @author: Administrator
 * @date: 2018年2月25日 下午3:35:01
 */
package www.chenmeikai.com.service;

import java.sql.Connection;

/**
 * @Description:自动生成代码
 * @author: cmk
 * @date: 2018年2月25日 下午3:35:01
 */
public interface GenService {
    //获得连接
    public Connection getConnect(String driver, String url, String userName, String password);

    //根据表名，生成一个实体类
    public void genModel(Connection conn, String tableName);

    //生成所有实体类
    public void genModels(Connection conn);

    //生成mapper
    public void genMappers(Connection conn);

    //生成mapper基类
    public void genBaseMappers();

    //生成mapper映射文件
    public void genMappersXml(Connection conn);

    //生成service
    public void genService(Connection conn);

    //生成service基类
    public void genBaseService(Connection conn);

    //生成serviceImpl
    public void genServiceImpl(Connection conn);

    //生成serviceImpl基类
    public void genBaseServiceImpl(Connection conn);

    //生成conntroller
    public void genController(Connection conn);

    //关闭连接
    public void disConnect(Connection conn);

}
