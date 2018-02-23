/**   
 * Copyright © 2018 www.chenmeikai.com
 * @Package: FtlController.java 
 * @author: Administrator   
 * @date: 2018年2月23日 下午9:35:49 
 */
package www.chenmeikai.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:TODO  
 * @author: cmk 
 * @date:   2018年2月23日 下午9:37:16
 */
@Controller
public class FtlController {
	
	@RequestMapping("demo")
	public String demo() {
		return "demo";
	}

}
