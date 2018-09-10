/**
 * Copyright © 2018
 *
 * @Package: www.chenmeikai.com.model
 * @date: 2018年2月24日 下午5:44:51
 */
package www.chenmeikai.com.Attribute;

/**
 * @Description:实体类 属性
 * @author: cmk
 * @date: 2018年2月24日 下午5:44:51
 */
public class Attribute {

    //属性名
    private String name;
    //属性类型
    private String type;
    //字段名
    private String column;
    //字段类型
    private String dbType;
    //注释
    private String remark;

    private String gName;

    private String sName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }


    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * @param name
     * @param type
     * @param remark
     * @param gName
     * @param sName
     */
    public Attribute(String name, String type, String remark, String gName, String sName) {
        super();
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.gName = gName;
        this.sName = sName;
    }

    /**
     * @param name
     * @param type
     * @param column
     * @param dbType
     * @param remark
     * @param gName
     * @param sName
     */
    public Attribute(String name, String type, String column, String dbType, String remark, String gName,
                     String sName) {
        super();
        this.name = name;
        this.type = type;
        this.column = column;
        this.dbType = dbType;
        this.remark = remark;
        this.gName = gName;
        this.sName = sName;
    }


}
