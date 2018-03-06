package com.zfj.xmy.pc.web.interceptor;

import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoleilu.hutool.util.NetUtil;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.pc.PcColumService;
import com.zfj.xmy.activity.service.pc.PcIndexConfigService;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.pc.web.Lijie;
import com.zfj.xmy.pc.web.common.SessionInfo;
@RestController
public class LoginInterceptor implements HandlerInterceptor{

	@Autowired
	private PcIndexConfigService configService;
	
	@Autowired
	private PcColumService columService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//3.查询栏目
		List<PcDownColumDto> findDownColum = columService.findDownColum(VocabularyConstant.VOC_COLUMN);
		//4.查询商品导航
		List<PcDownColumDto> goodsNavigated = columService.findDownColum(VocabularyConstant.VOC_CATEGORY);
		//9.获取搜索关键字
		List<String> keyWords = configService.findSearchKeyWords();
		request.getSession().setAttribute("keyWords", keyWords);
		request.getSession().setAttribute("findDownColum", findDownColum);
		request.getSession().setAttribute("goodsNavigated", goodsNavigated);
		if (handler instanceof HandlerMethod){
			 Lijie lijie = ((HandlerMethod) handler).getBeanType().getAnnotation(Lijie.class);
			 Lijie hand = ((HandlerMethod) handler).getMethod().getAnnotation(Lijie.class);
			 if(lijie == null && hand==null){
				 return true;
			 }
			 SessionInfo sessionInfo = SessionInfo.get();
			 if(ObjectUtils.isEmpty(sessionInfo)){
				 String contextPath = request.getServletContext().getContextPath();
				 response.sendRedirect(contextPath+"/user/login");
				/* InetAddress localhost = NetUtil.getLocalhost();
				 System.out.println(localhost);*/
				 return false;
			 }else{
				 return true;
			 }
		 }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
