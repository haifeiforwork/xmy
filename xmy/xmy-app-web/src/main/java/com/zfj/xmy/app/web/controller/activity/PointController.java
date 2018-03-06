package com.zfj.xmy.app.web.controller.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.xmy.activity.persistence.app.pojo.dto.AppPointsInDto;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;
import com.zfj.xmy.activity.service.app.PointsService;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;

@RestController
@RequestMapping(value="/point",params=SystemConstant.MOBILE_VERSION_V10)
public class PointController extends BaseController{
	@Autowired
	private PointsService pointsService;
	
	@RequestMapping("/list/{uid}/{type}")
	public ResponseEntity<AppResp> UserSpendPoints(@PathVariable("uid") Long uid,@PathVariable("type") int type){
		return AppResp.get(pointsService.findUserSpendPoints(uid,type));
	}
	
	@RequestMapping("/findPointsActivity")
	public ResponseEntity<AppResp> findTermData(){
		return AppResp.get(pointsService.findPointsActivity());
	}
	
	@RequestMapping("/findPointsGoods")
	public ResponseEntity<AppResp> findPointsGoods(@RequestBody AppPointsInDto appPointsInDto){
		return AppResp.get(pointsService.findPointGoods(appPointsInDto.getPointsId()));
	}
	/**
	 * APP签到
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月13日下午6:02:26
	 */
	@RequestMapping("/sign")
	public ResponseEntity<AppResp> sign(){
		Map<String, Object> result=new HashMap<String, Object>();
		Long userId = AppLocalInfo.getUserId();
		Integer sign = pointsService.sign(userId);
		result.put("points", sign);
		Integer signDays = pointsService.signDays(userId);
		result.put("desc","恭喜您连续签到"+(signDays)+"天获得了"+(sign)+"积分");
		return AppResp.get(result);
	}
	/**
	 * 判断今天是否签到了
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月13日下午6:55:25
	 */
	@RequestMapping("/isSign")
	public ResponseEntity<AppResp> isSign(){
		Map<String, Object> result=new HashMap<String, Object>();
		Long userId = AppLocalInfo.getUserId();
		boolean sign = pointsService.isSign(userId);
		if(sign){
			Integer signDays = pointsService.signDays(userId);
			result.put("signDays", signDays);
		}
		result.put("isSign", sign);
		return AppResp.get(result);
	}
}
