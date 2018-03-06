package com.zfj.xmy.pc.web.controller.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.pc.PcColumService;
import com.zfj.xmy.common.VocabularyConstant;

@RestController
@RequestMapping("/news")
@CheckLogin(contains={"index"})
public class NewsController {
	@Autowired
	private PcColumService columService;
	
	@RequestMapping("/index")
	@CheckLogin
	public ModelAndView order(int ter,int chd,HttpServletRequest request){
		List<PcDownColumDto> findDownColum = columService.findDownColum(VocabularyConstant.VOC_COLUMN);
		request.getSession().setAttribute("findDownColum", findDownColum);
		request.setAttribute("ter", ter);
		request.setAttribute("chd", chd);
		return new ModelAndView("/news/about_xmy");
	}
	
	
	/**
     * 专题活动
     * @param ter
     * @param chd
     * @param request
     * @return
     */
    @RequestMapping("/zt/{name}")
    public ModelAndView zt(@PathVariable("name") String name,HttpServletRequest request){
       
        return new ModelAndView("/subject/"+name+"/"+name);
    }
}
