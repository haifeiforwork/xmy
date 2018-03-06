package com.zfj.xmy.util;  

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.util.ObjectUtils;

import com.zfj.base.util.common.StringUtil;

/** 
 * @Title: DecompressionUtil.java 
 * @Package com.zfj.xmy.cms.web.controller.goodsManagement 
 * @Description: 
 * @author hexw
 * @date 2017年7月4日 下午7:45:57 
 */
public class DecompressionUtil {
	
	private String file; // 目标文件夹
	
	private String saveRootDirectory; //解压文件的存放路径 
	
	
	private DecompressionUtil(String file, String saveRootDirectory ) {
		this.file = file;
		this.saveRootDirectory = saveRootDirectory;
	}

	public static  Map<String,Object>  zipFileRead(String file, String saveRootDirectory) {
		 Map<String,Object> result = new HashMap<String,Object>() ;
	        try {
	            ZipFile zipFile = new ZipFile(file,Charset.forName("GBK"));
	            String fileName = null;
	            @SuppressWarnings("unchecked")
				Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
	            while (enu.hasMoreElements()) {
	                ZipEntry zipElement = (ZipEntry) enu.nextElement();
	                InputStream read = zipFile.getInputStream(zipElement);
	                fileName = zipElement.getName();
	                if (fileName != null && fileName.indexOf(".") != -1) {//是否为文件 （文件带有路径如：/images/a.jpg）
	                	result = execute(zipElement,read,saveRootDirectory,result);
	                }
	            }
	        } catch (Exception e) {
	        	 result.put("success", false);
	            e.printStackTrace();
	        }
	        return result;
	    }

	    private static Map<String,Object> execute(ZipEntry ze, InputStream read,String saveRootDirectory,Map<String,Object> result )
	            throws FileNotFoundException, IOException {
	    	
	    	boolean isSuccess = true ;
	        //如果只读取图片，自行判断就OK.
	        String fileName = ze.getName();
	      //  if(fileName.lastIndexOf(".jpg")!= -1 || fileName.lastIndexOf(".bmp")!= -1  || fileName.lastIndexOf(".jpeg")!= -1){//指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）
	        	File file = new File(saveRootDirectory+ File.separator + fileName);
	            if (!file.exists()) {
	                File rootDirectoryFile = new File(file.getParent());
	                //创建目录
	                if (!rootDirectoryFile.exists()) {
	                	isSuccess = rootDirectoryFile.mkdirs();
	                }
	                //创建文件
	                try {
	                    file.createNewFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            //写入文件
	            BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(file));
	            int cha = 0;
	            while ((cha = read.read()) != -1) {
	                write.write(cha);
	            }
	            if(StringUtil.isEmpty(result.get("filePath"))){
	            	 result.put("filePath",file.getParent() );
	            }
	            //要注意IO流关闭的先后顺序
	            write.flush();
	            write.close();
	            read.close();
	        //  }
	            result.put("success", isSuccess);
	            return result;
	   }
	    
}
  