package com.zfj.xmy.wap.web.common;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.user.service.wap.WapUserService;

/**
 * @author mu
 *
 */
public class SessionUtils {
	
	public static User getUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("wap_session");
		return info.getUser();
	}
	public static UserInfo getUserInfo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("wap_session");
		return info.getUserInfo();
	}
	public static List<UserAddrees> getUserAddress(HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("wap_session");
		return info.getUserAddress();
	}
	
	public static void reSetUserInfo(HttpSession session, WapUserService service) {
		SessionInfo info = (SessionInfo) session.getAttribute("wap_session");
		User user = info.getUser();
		UserInfo userInfo = service.queryInfo(user);
		info.setUserInfo(userInfo);
		session.setAttribute("wap_session", info);
	}
	
	public static void reSetUserAddress(HttpSession session, WapUserService service) {
		SessionInfo info = (SessionInfo) session.getAttribute("wap_session");
		User user = info.getUser();
		List<UserAddrees> address = service.queryAddress(user);
		info.setUserAddress(address);
		session.setAttribute("wap_session", info);
	}
	
	public static void reSetUser(HttpSession session, WapUserService service) {
		SessionInfo info = (SessionInfo) session.getAttribute("wap_session");
		Long id = info.getUser().getId();
		User user = service.getUser(id);
		info.setUser(user);
		session.setAttribute("wap_session", info);
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getAttr(HttpServletRequest req, HttpServletResponse res, Class<T> cls, String attrName) {
		HttpSession session = req.getSession();
		Object object = session.getAttribute(attrName);
		if(null != object) {
			return (T) object;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionInfo(HttpServletRequest req, HttpServletResponse res, Class<T> cls, String fieldName) {
		HttpSession session = req.getSession();
		Object object = session.getAttribute("wap_session");
		if(null == object) {
			return null;
		}
		SessionInfo info = (SessionInfo) object;
		
		try {
			Field[] fields = SessionInfo.class.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field f : fields) {
				if(f.getName().equals(fieldName)) {
					Object obj = f.get(info);
					return (T) obj;
				}
			}
		} catch(Exception e) {
			return null;
		}
		
		return null;
	}
	
}
