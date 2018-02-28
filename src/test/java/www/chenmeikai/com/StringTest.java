/**   
 * Copyright © 2018 
 * @Package: StringTest.java 
 * @author: Administrator   
 * @date: 2018年2月25日 下午5:48:50 
 */
package www.chenmeikai.com;

import org.junit.Test;

/**      
 * @Description:TODO  
 * @author: cmk 
 * @date:   2018年2月25日 下午5:48:50     
 */
public class StringTest {
	
	//indexOf 测试
	@Test
	public void test() {
		
		String regex ="t_";
		String text ="t_inex_1";
		if(text.indexOf(regex)==0) {
		String cutString =text.substring(regex.length());
		System.out.println(cutString);
		}
	}
	
	@Test
	public void test2() {
		
		String text ="hello";
		String sub =text.substring(0,1);
		String end =text.substring(1);
		System.out.println(sub);
		System.out.println(end);
		
	}

}
