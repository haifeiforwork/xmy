package com.zfj.xmy.util.template;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 字符串模板替换工具
 * @author wy
 *
 */
public class TemplateUtil {

	private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
	
	public TemplateUtil() {
		configuration.setDefaultEncoding("utf-8");
	}

	/**
	 * 字符串替换 方法 
	 * @param template 
	 * @param dataModel
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * @Description 
	 * @date 2018年2月6日  下午4:45:23
	 * @author wy
	 * 2018
	 * @return String
	 */
	public static String format(String template,Object dataModel) throws IOException, TemplateException{
		Template templateObject = new Template("", new StringReader(template), configuration);
		return getOutStr(templateObject, dataModel);
		
	}
	
	private static String getOutStr(Template template,Object dataModel) throws TemplateException, IOException{
		StringWriter stringWriter = new StringWriter();
		template.process(dataModel, stringWriter);
		String output = stringWriter.toString();
		
		return output;
	}
	
	public static void main(String[] args) {
		Map<String,Object> root = new HashMap<String, Object>();
		
		root.put("mimi", "事甼呼");
		String template = "我我我我${mimi}了了";
		try {
			String res = TemplateUtil.format(template, root);
			System.out.println(res);
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
