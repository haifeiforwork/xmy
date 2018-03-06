package com.zfj.xmy.goods.service.cms;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.PcGoodsSeting;

public interface PcGoodsSetService {
	/**
	 * @param reqData
	 * @param pageBean
	 * @return List<PcGoodsSeting>
	 * @author lij
	 * @date 2017年8月15日 下午5:30:00
	 * 分页查询Pc商品设置的信息
	 */
	List<PcGoodsSeting> findPcGoodsSeting(ReqData reqData,PageBean pageBean);
	/**
	 * @return Integer
	 * @author lij
	 * @date 2017年8月15日 下午5:56:47
	 * 查询Pc商品的总条数
	 */
	Integer  countPcgoods(ReqData reqData);
	/**
	 * @param type
	 * @param goodsId void
	 * @author lij
	 * @date 2017年8月15日 下午7:33:55
	 * 为前台分类添加商品
	 */
	void addPcGoods(Integer type,Long goodsId);
	/**
	 * 删除分类
	 * @param typeid
	 * @param goodsid void
	 * @author lij
	 * @date 2017年10月14日 下午3:53:07
	 */
	void deletePcGoods(Integer typeid,Long goodsid);
	/**
	 * 批量删除
	 * @param ids void
	 * @author lij
	 * @date 2017年10月14日 下午4:27:53
	 */
	void deletePcGood(String ids);
}
