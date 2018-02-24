/**   
 * Copyright © 2018  
 * @Package: www.chenmeikai.com.controller   
 * @date: 2018年2月24日 下午2:10:38 
 */
package www.chenmeikai.com.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**    
 * @Description:TODO 
 * @author: cmk
 * @date:   2018年2月24日 下午2:10:38      
 */

@Controller
@RequestMapping("")
public class MainController {

    @Resource
    Configuration cfg;

    @GetMapping("test")
    public String main(Model model){
        String w="Welcome FreeMarker!";
        Map<String,Object> root = new HashMap<>();
        root.put("w",w);
        freeMarkerContent(root);
        model.addAttribute("w","Welcome FreeMarker!");
        return "test";
    }

    private void freeMarkerContent(Map<String,Object> root){
        try {
            Template temp = cfg.getTemplate("test.ftl");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
		    String path2=this.getClass().getResource("/").getPath()+"static/test.html";
		    String path =path2.substring(1);
            Writer file = new FileWriter(new File(path));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}