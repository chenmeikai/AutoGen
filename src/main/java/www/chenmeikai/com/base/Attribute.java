/**   
 * Copyright © 2018  
 * @Package: www.chenmeikai.com.model   
 * @date: 2018年2月24日 下午5:44:51 
 */
package www.chenmeikai.com.base;

/**    
 * @Description:实体类 属性 
 * @author: cmk
 * @date:   2018年2月24日 下午5:44:51      
 */
public class Attribute {
	
	//属性名
	private String name ;
	//属性类型
	private String type;
	//注释
	private String remark;
	
	

	/**
	 * @param name
	 * @param type
	 * @param remark
	 */
	public Attribute(String name, String type, String remark) {
		super();
		this.name = name;
		this.type = type;
		this.remark = remark;
	}

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
	
	

}
