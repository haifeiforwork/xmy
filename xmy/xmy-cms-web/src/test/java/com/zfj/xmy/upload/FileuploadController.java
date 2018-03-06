package com.zfj.xmy.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zfj.base.util.common.ThumbnailatorUtil;

@RestController
@RequestMapping("/fileupload")
public class FileuploadController {

	
	@RequestMapping("/index")
	public ResponseEntity<List<Map<String,Object>>> upload(HttpServletRequest request){
		
		MultipartHttpServletRequest mReq = (MultipartHttpServletRequest) request ;
		MultiValueMap<String,MultipartFile> vMap = mReq.getMultiFileMap() ;
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>() ;
		
		Map<String,MultipartFile> fMap = mReq.getFileMap() ;
		
		String path = "d:/fileupload" ;
		
		for(Map.Entry<String,MultipartFile> entry : fMap.entrySet()){
			MultipartFile file = entry.getValue() ;
			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("fileName",file.getOriginalFilename()) ;
			map.put("filePath",path + "/" + file.getOriginalFilename()) ;
			mList.add(map) ;
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path + "/" + file.getOriginalFilename())) ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<Map<String,Object>>>(mList,HttpStatus.OK) ;
		
	}
	
}
