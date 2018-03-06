package com.zfj.xmy.pc.web.controller.container.dto;

import com.xiaoleilu.hutool.util.RandomUtil;

public class HeadJudger {
	
	private static final String[] WAP_HEADERS = new String[]{"iphone", "android", "samsung", "ios", "nokia"};
	
	private static final String[] DESKTOP_HEADERS = new String[]{"windows", "macintosh"};
	
	private static final String[] PAD_HEADERS = new String[]{"ipad"};
	
	public static boolean IsWap(String header) {
		for(int i = 0; i < WAP_HEADERS.length; ++i)
			if(header.indexOf(WAP_HEADERS[i]) > 0)
				return true;
		return false;
	}
	
	public static boolean IsDesktop(String header) {
		for(int i = 0; i < DESKTOP_HEADERS.length; ++i)
			if(header.indexOf(DESKTOP_HEADERS[i]) > 0)
				return true;
		return false;
	}
	
	public static boolean isPad(String header) {
		for(int i = 0; i < PAD_HEADERS.length; ++i)
			if(header.indexOf(PAD_HEADERS[i]) > 0)
				return true;
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomUtil.randomString(6));
	}
	
}
