package com.zfj.xmy.activity.service.wap;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.app.pojo.dto.AppCouponOutDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.CouponSearchInDto;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.EntityCoupon;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.common.persistence.pojo.app.AppCouponInDto;

public interface WapCouponService {
	/**
	 * 
	 * @Description 插入一条优惠券信息
	 * @param coupon
	 * @return
	 * @Author liuw
	 * @Date 2017年7月6日下午8:31:19
	 */
	int insertCoupon(Coupon coupon);
	/**
	 * 
	 * @Description 查找优惠券列表分页 
	 * @param reqData
	 * @param pageBean
	 * @Author liuw
	 * @Date 2017年7月6日下午8:33:10
	 */
	void findCouponsAndPage(ReqData reqData,PageBean pageBean);
	
	/**
	 * 
	 * @Description 根据主键删除
	 * @param string
	 * @return
	 * @Author liuw
	 * @Date 2017年7月7日上午11:02:23
	 */
	int deleteByPrimeryKey(Object string);
	/**
	 * 
	 * @Description 根据Id查找coupon（优惠券）
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月10日上午9:38:28
	 */
	Coupon findCouponById(Object id);
	/**
	 * 
	 * @Description 更新coupon
	 * @param coupon
	 * @return
	 * @Author liuw
	 * @Date 2017年7月10日下午4:04:26
	 */
	int updateCoupon(Coupon coupon);
	/**
	

	/**
	 * 
	 * @Description 根据IDs集合,转化得到supplierNames（供应商名字集合）
	 * @param couponSupplierIds
	 * @param supplierTermDatas
	 * @return
	 * @Author liuw
	 * @Date 2017年7月10日下午4:17:56
	 */
	String getCouponSupplierNames(String couponSupplierIds,List<TermData> supplierTermDatas );
	/**
	 * 
	 * @Description 计算出供应商名字集合
	 * @param useRangeType
	 * @param useRangeIds
	 * @param vocabularyByMark
	 * @param categoryTermDatas
	 * @param pageBeanGoods
	 * @return
	 * @Author liuw
	 * @Date 2017年7月11日下午2:30:45
	 */
	String getRangesNames(Integer useRangeType,String useRangeIds,Vocabulary vocabularyByMark ,List<TermData> categoryTermDatas,PageBean pageBeanGoods);
	
	/**
	 * 
	 * @Description 根据条件，查找符合条件的优惠券（app）
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月25日下午5:54:56
	 */
	List<CouponUser>  findCoupons(ReqData reqData);
	/**
	 * 
	 * @Description 根据couponuser关系表中的couponid，查找出coupon记录
	 * @param couponUsers
	 * @return
	 * @Author liuw
	 * @Date 2017年7月25日下午7:34:23
	 */
	List<Coupon> transformByCouponUser(List<CouponUser> couponUsers);
	
//	/**
//	 * 
//	 * @Description APP端根据条件查询优惠券详情<br>
//	 * <pre>
//	 * 查询类型：1 已过期 ；2 使用记录；3 未使用
//	 * private int useType;
//	 * 使用范围 （1：全场通用；2：分类使用；3：限定商品；4：排队商品）
//	 * private int useRange;
//	 *  排序方法  1 过期时间 ； 2 最优惠
//	 *  private int order;
//	 * </pre>
//	 * @param couponSearchInDto
//	 * @return
//	 * @Author liuw
//	 * @Date 2017年8月1日下午8:37:18
//	 */
//	List<EntityCoupon> findCoupon(Long userId,CouponSearchInDto couponSearchInDto);
	
	/**
	 * 
	 * @Description 根据条件查询所有coupon（优惠券）列表
	 * @param criteriaParameter
	 * @return
	 * @Author liuw
	 * @Date 2017年8月2日上午9:20:39
	 */
	List<Coupon> findsCoupon(CriteriaParameter criteriaParameter);
//	/**
//	 * 绑定实体券
//	 * @Description 
//	 * @param entityCoupon
//	 * @param couponUser
//	 * @Author liuw
//	 * @Date 2017年8月4日下午2:27:45
//	 */
//	void setEntityCouponBinding(EntityCoupon entityCoupon,CouponUser couponUser);
	/**
	 * 根据id查询实体纸质优惠券
	 * @Description 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年8月4日下午5:00:55
	 */
	EntityCoupon findEntityCouponById(Long id);
	/**
	 * 根据条件查找实体优惠券
	 * @Description 
	 * @param parameter
	 * @return
	 * @Author liuw
	 * @Date 2017年8月4日下午5:29:26
	 */
	List<EntityCoupon> findEntityCoupon(CriteriaParameter parameter);
//	/**
//	 * 根据couponId查找该电子优惠券的已领取个数
//	 * @Description 
//	 * @param couponId
//	 * @return
//	 * @Author liuw
//	 * @Date 2017年8月7日上午9:47:58
//	 */
//	Integer countByCouponId(Long couponId);
//	/**
//	 * 判断可用的优惠券
//	 * @Description 
//	 * @param userId
//	 * @return
//	 * @Author liuw
//	 * @Date 2017年8月7日上午10:45:25
//	 */
//	List<AppCouponOutDto> findsAllUsableCoupon(Long userId);
//	/**
//	 * 领取电子优惠券
//	 * @Description 
//	 * @param coupon
//	 * @Author liuw
//	 * @Date 2017年9月11日下午5:23:33
//	 */
//	void updateCouponReceive(AppCouponInDto coupon);
}
