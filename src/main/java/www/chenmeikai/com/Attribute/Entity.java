/**
 * Copyright © 2018
 *
 * @Package: Entity.java
 * @author: Administrator
 * @date: 2018年2月25日 下午2:45:39
 */
package www.chenmeikai.com.Attribute;

import java.util.List;

/**
 * @Description:实体类
 * @author: cmk
 * @date: 2018年2月25日 下午2:45:39
 */
public class Entity {

    //类名
    private String name;

    //表名
    private String table;

    //属性集合
    private List<Attribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }


}
