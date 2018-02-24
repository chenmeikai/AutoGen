/**   
 * Copyright © 2018  
 * @Package: www.chenmeikai.com.model   
 * @date: 2018年2月24日 下午5:44:51 
 */
package www.chenmeikai.com.model;

/**    
 * @Description:TODO 
 * @author: cmk
 * @date:   2018年2月24日 下午5:44:51      
 */
public class Attribute {
	
	private String name ;
	
	private String type;
	
	
	

	public Attribute(String name, String type) {
		super();
		this.name = name;
		this.type = type;
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
	
	

}
