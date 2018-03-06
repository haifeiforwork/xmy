package com.zfj.xmy.activity.persistence.pc.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.PointsActivity;

/** 
 * @Title: PointsActivityDto.java 
 * @Package com.zfj.xmy.activity.persistence.pc.pojo.dto 
 * @Description: 
 * @author zhangh
 * @date 2017年10月25日 下午5:59:46 
 */
public class PointsActivityDto extends PointsActivity{
	
	private List<PcPointsGoodsDto> pcPointsGoodsDtos;

	public List<PcPointsGoodsDto> getPcPointsGoodsDtos() {
		return pcPointsGoodsDtos;
	}

	public void setPcPointsGoodsDtos(List<PcPointsGoodsDto> pcPointsGoodsDtos) {
		this.pcPointsGoodsDtos = pcPointsGoodsDtos;
	}
	
}
