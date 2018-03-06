package com.zfj.xmy.activity.service.cms;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.ShoppingCardDto;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;

public interface ShoppingCardService {
	/**
	 * 分页查询商品信息
	 * @param reqData
	 * @param pageBean
	 * @return List<ShoppingCardDto>
	 * @author lij
	 * @date 2017年10月31日 下午5:27:23
	 */
	List<ShoppingCardDto> findAllShoppingCard(ReqData reqData,PageBean pageBean);
	
	/**
	 * 查用绑定的所有购物卡
	 * @param userId
	 * @return List<ShoppingCard>
	 * @author lij
	 * @date 2017年10月27日 下午5:08:35
	 */
	List<ShoppingCard> findShoppingCardByUserId(Long userId);
	
	void updateShoppingCard(Long id,Integer type,BigDecimal money);

	/**
	 * 导入购物卡
	 * @param file
	 * @Description 
	 * @date 2017年12月14日  下午4:03:07
	 * @author wy
	 * 2017
	 * @return void
	 */
	void importShoppingCard(MultipartFile file);
	/**
	 * 根据购物卡编号密码查询购物卡
	 * @param cardNo
	 * @param pwd
	 * @return List<ShoppingCard>
	 * @author lij
	 * @date 2018年1月24日 下午3:57:58
	 */
	List<ShoppingCard> getShoppingcCards(String cardNo,String pwd);
}
