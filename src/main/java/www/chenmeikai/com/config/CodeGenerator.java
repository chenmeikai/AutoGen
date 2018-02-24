/**   
 * Copyright © 2018  
 * @Package: www.chenmeikai.com.config   
 * @date: 2018年2月24日 下午5:05:52 
 */
package www.chenmeikai.com.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import www.chenmeikai.com.model.Attribute;
/**
 * @Description:TODO
 * @author: cmk
 * @date: 2018年2月24日 下午5:05:52
 */

public class CodeGenerator {

	public static void main(String[] args) {

		try {
			new CodeGenerator().gen();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public void gen() throws IOException, TemplateException {

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File("E:\\freemarker\\templet"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Template temp = cfg.getTemplate("person.ftl"); // load E:/Work/Freemarker/templates/person.ftl

		// Create the root hash
		Map<String, Object> root = new HashMap<String, Object>();

		root.put("packageName", "com.ricky.java");
		root.put("className", "Person");
		root.put("author", "Ricky Fung");

		List<Attribute> attr_list = new ArrayList<Attribute>();
		attr_list.add(new Attribute("id", "Long"));
		attr_list.add(new Attribute("name", "String"));
		attr_list.add(new Attribute("age", "Integer"));
		attr_list.add(new Attribute("hobby", "List<String>"));

		root.put("attrs", attr_list);

		// Writer out = new OutputStreamWriter(System.out);
		// Writer out = new OutputStreamWriter(System.out);
		File dir = new File("E:\\freemarker\\create");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		OutputStream fos = new FileOutputStream(new File(dir, "Person.java")); // java文件的生成目录
		Writer out = new OutputStreamWriter(fos);
		temp.process(root, out);

		fos.flush();
		fos.close();

		System.out.println("gen code success!");
	}
}