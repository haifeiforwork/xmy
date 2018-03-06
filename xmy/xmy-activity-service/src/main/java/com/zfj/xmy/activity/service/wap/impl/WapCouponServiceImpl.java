package com.zfj.xmy.activity.service.wap.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.activity.persistence.app.pojo.dto.AppCouponOutDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.CouponSearchInDto;
import com.zfj.xmy.activity.service.wap.WapCouponService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.EntityCouponMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.EntityCoupon;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.common.persistence.pojo.app.AppCouponInDto;
/**
 * 
 * @Description 优惠券service
 * @Author liuw
 * @Date 2017年7月6日下午8:31:57
 */
@Service
public class WapCouponServiceImpl extends BaseService implements WapCouponService {

	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private CouponUserMapper couponUserMapper;

	@Autowired
	private EntityCouponMapper entityCouponMapper;
	
	@Override
	public int insertCoupon(Coupon coupon) {
		int insert = couponMapper.insert(coupon);
		return insert;
	}
	@Override
	public void findCouponsAndPage(ReqData reqData, PageBean pageBean) {
		List<Coupon> selectByExampleWithBLOBs = couponMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		int countByExample = couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(countByExample);
		pageBean.setData(selectByExampleWithBLOBs);
	}
	@Override
	public int deleteByPrimeryKey(Object string) {
		int deleteByPrimaryKey = couponMapper.deleteByPrimaryKey(string);
		return deleteByPrimaryKey;
	}
	@Override
	public Coupon findCouponById(Object id) {
		Coupon coupon = couponMapper.selectByPrimaryKey(id);
		return coupon;
	}
	@Override
	public EntityCoupon findEntityCouponById(Long id) {
		EntityCoupon selectByPrimaryKey = entityCouponMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
	@Override
	public int updateCoupon(Coupon coupon) {
		int updateByPrimaryKeySelective=couponMapper.updateByPrimaryKeyWithBLOBs(coupon);
		return updateByPrimaryKeySelective;
	}
	@Override
	public String getCouponSupplierNames(String couponSupplierIds,List<TermData> supplierTermDatas ) {
		String couponSupplierNames="";
		String[] splitCouponSupplierIds = couponSupplierIds.split(",");
		for(int i=0;i<splitCouponSupplierIds.length;i++){//已选中的供应商ID集合
			for (int j = 0; j < supplierTermDatas.size(); j++) //总的供应商ID集合
			{
				if(splitCouponSupplierIds[i].equals(String.valueOf(supplierTermDatas.get(j).getId())))
				{
					couponSupplierNames+=supplierTermDatas.get(i).getName()+",";
					break;
				}
			}
		}
		return couponSupplierNames;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getRangesNames(Integer useRangeType,String useRangeIds,Vocabulary vocabularyByMark,List<TermData> categoryTermDatas,PageBean pageBeanGoods ) {
		String useRangeNames="";
		ReqData reqDataVocabulary=new ReqData();
		String[] splitUseRangeIds = useRangeIds.split(",");
		if (useRangeType == 1)// 全场通用
		{
			useRangeNames = SystemConstant.TERMDATA.AllCANUSE;
		} else if (useRangeType == 2)// 分类使用
		{
			reqDataVocabulary = new ReqData();
			reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK,SystemConstant.TERMDATA.CATEGORY,SystemConstant.REQ_PARAMETER_EQ);
			ReqData reqDataTermData = new ReqData();
			reqDataTermData.putValue(SystemConstant.TERMDATA.VID,vocabularyByMark.getId(), SystemConstant.REQ_PARAMETER_EQ);
			for (int i = 0; i < splitUseRangeIds.length; i++) {
				for (int j = 0; j < categoryTermDatas.size(); j++) {
					if (splitUseRangeIds[i].equals(String.valueOf(categoryTermDatas.get(j).getId()))) {
						useRangeNames += categoryTermDatas.get(j).getName()
								+ ",";
						break;
					}
				}
			}
		} else {// 限定商品或者指定商品
				List<Goods> goods = (List<Goods>) pageBeanGoods.getData();
				for (int i = 0; i < splitUseRangeIds.length; i++) {
					for (int j = 0; j < goods.size(); j++) {
						if (splitUseRangeIds[i].equals(String.valueOf(goods.get(j)
								.getCode()))) {
							useRangeNames += goods.get(j).getName() + ",";
						}
					}
				}
			
			}
		return useRangeNames;
	}
	@Override
	public List<CouponUser> findCoupons(ReqData reqData){
		List<CouponUser> selectByExample = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
	
	@Override
	public List<Coupon> transformByCouponUser(List<CouponUser> couponUsers){
		List<Coupon> coupons=new ArrayList<Coupon>();
		for (CouponUser couponUser : couponUsers) {
			Coupon coupon=null;
			Long couponId = couponUser.getCouponId();
			coupon=couponMapper.selectByPrimaryKey(couponId);
			coupons.add(coupon);
		}
		return coupons;
	}
//	@Override
//	public List<EntityCoupon> findCoupon(Long userId,CouponSearchInDto couponSearchInDto){
//		//查询类型：1 已过期 ；2 使用记录；3 未使用
//		Integer useType = couponSearchInDto.getUseType();
//		// 使用范围 （1：全场通用；2：分类使用；3：限定商品；4：排队商品）
//		Integer useRange = couponSearchInDto.getUseRange();
//		// 排序方法  1 过期时间 ； 2 最优惠
//		Integer order = couponSearchInDto.getOrder();
//		//需要返回的coupon优惠券记录列表
//		List<Coupon> coupons=null;
//		//需要返回的实体优惠券记录列表
//		List<EntityCoupon> entityCoupons=new ArrayList<>();
//		//电子优惠券id集合
//		List<Object> couponUserIds=new ArrayList<>();
//		//实体优惠券id集合
//		List<Object> entityCouponUserIds=new ArrayList<>();
//		
//		ReqData reqData=new ReqData();
//		
//		// todo 需要替换掉userid数据
//		reqData.putValue(SystemConstant.COUPON.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
//		
//		//没有携带查询类型，直接抛个异常前去提醒前台
//		if(ObjectUtils.isEmpty(useType)){
//			throw new BusinessException("请带上useType查询类型！");
//		}
//		//查询类型：1 已过期 ；2 使用记录；3 未使用
//		switch(useType){
//			case 1:	// 已过期的
//				reqData.putValue(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_NO_USE, SystemConstant.REQ_PARAMETER_EQ);
//				List<CouponUser> noUseCoupon = this.findCoupons(reqData);
//				for (CouponUser couponUser : noUseCoupon) {
//					
//					Long id = couponUser.getId();
//					EntityCoupon entityCoupon=new EntityCoupon();
//					
//					Long couponId = couponUser.getCouponId();
//					//为电子优惠券
//					if(couponUser.getType()==SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS){
//						Coupon findCouponById = this.findCouponById(couponId);
//						BeanUtils.copyProperties(findCouponById, entityCoupon);
//						entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//					}else if(couponUser.getType()==SystemConstant.COUPON.TYPE_ECOUPON_Entity){
//
//						//为实体纸质优惠券
//						
//						entityCoupon = this.findEntityCouponById(couponId);
//						entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_Entity);
//					}
//					// 判断是否是按照使用分类查询
//					Integer couponUseRange = entityCoupon.getUseRange();
//					// 不为空而且不相等的时候，就continue，进行下一次判断。说明不符合条件
//					if(!ObjectUtils.isEmpty(useRange) && !couponUseRange.equals(useRange)){
//						continue;
//					}
//					////说明已经过期
//					int compareTo = entityCoupon.getEffectiveEndTime().compareTo(new Date());
//					if(compareTo<=SystemConstant.COUPON.DATA_HAD_EXPIRED){
//						//先保存优惠券的id，后面统一取
//						
//						// 电子优惠券保存到电子优惠券列表里
//						if(entityCoupon.getType().equals(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS)){
//							couponUserIds.add(entityCoupon.getId());
//						}
//						// 纸质实体优惠券保存到纸质实体优惠券里面
//						if(entityCoupon.getType().equals(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS)){
//							entityCouponUserIds.add(entityCoupon.getId());
//						}
//					}
//				}
//				break;
//			case 2:	// 已经使用的（使用记录）
//				reqData.putValue(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_USED, SystemConstant.REQ_PARAMETER_EQ);
//				List<CouponUser> findCouponsUsed = this.findCoupons(reqData);
//				for (CouponUser couponUser : findCouponsUsed) {
//					EntityCoupon entityCoupon=new EntityCoupon();
//					Long couponId = couponUser.getCouponId();
//					//为电子优惠券
//					if(couponUser.getType()==SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS){
//						Coupon findCouponById = this.findCouponById(couponId);
//						BeanUtils.copyProperties(findCouponById, entityCoupon);
//						entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//					}else if(couponUser.getType()==SystemConstant.COUPON.TYPE_ECOUPON_Entity){
//						//为实体纸质优惠券
//						entityCoupon = this.findEntityCouponById(couponId);
//						entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_Entity);
//					}
//					// 判断是否是按照使用分类查询
//					Integer couponUseRange = entityCoupon.getUseRange();
//					// 不为空而且不相等的时候，就continue，进行下一次判断。说明不符合条件
//					if(!ObjectUtils.isEmpty(useRange) && !couponUseRange.equals(useRange)){
//						continue;
//					}
//					//先保存优惠券的id，后面统一取
//					
//					// 电子优惠券保存到电子优惠券列表里
//					if(entityCoupon.getType().equals(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS)){
//						
//						couponUserIds.add(entityCoupon.getId());
//					}
//					// 纸质实体优惠券保存到纸质实体优惠券里面
//					if(entityCoupon.getType().equals(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS)){
//						entityCouponUserIds.add(entityCoupon.getId());
//					}
//				}
//				break;
//			case 3: //  未使用
//				
//				reqData.putValue(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_NO_USE, SystemConstant.REQ_PARAMETER_EQ);
//				noUseCoupon = this.findCoupons(reqData);
//				for (CouponUser couponUser : noUseCoupon) {
//					EntityCoupon entityCoupon=new EntityCoupon();
//					
//					Long couponId = couponUser.getCouponId();
//					//为电子优惠券
//					if(couponUser.getType()==SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS){
//						Coupon findCouponById = this.findCouponById(couponId);
//						BeanUtils.copyProperties(findCouponById, entityCoupon);
//						entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//					}
//					//为实体纸质优惠券
//					if(couponUser.getType()==SystemConstant.COUPON.TYPE_ECOUPON_Entity){
//						entityCoupon = this.findEntityCouponById(couponId);
//						entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_Entity);
//					}
//					// 判断是否是按照使用分类查询
//					Integer couponUseRange = entityCoupon.getUseRange();
//					// 不为空而且不相等的时候，就continue，进行下一次判断。说明不符合条件
//					if(!ObjectUtils.isEmpty(useRange) && !couponUseRange.equals(useRange)){
//						continue;
//					}
//					
//					////说明没有过期，可以直接使用
//					int compareTo = entityCoupon.getEffectiveEndTime().compareTo(new Date());
//					if(compareTo>SystemConstant.COUPON.DATA_HAD_EXPIRED){
//						//先保存优惠券的id，后面统一取
//						
//						// 电子优惠券保存到电子优惠券列表里
//						if(entityCoupon.getType().equals(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS)){
//							
//							couponUserIds.add(entityCoupon.getId());
//						}
//						// 纸质实体优惠券保存到纸质实体优惠券里面
//						if(entityCoupon.getType().equals(SystemConstant.COUPON.TYPE_ECOUPON_Entity)){
//							entityCouponUserIds.add(entityCoupon.getId());
//						}
//					}
//				}
//				
//				break;
//		}
//		//根据上面取到的id再查询出所有的优惠券
//		System.out.println(couponUserIds);
//		System.out.println(entityCouponUserIds);
//		if(!ObjectUtils.isEmpty(couponUserIds)||entityCouponUserIds.size()>0){
//			CriteriaParameter criteriaParameter=new CriteriaParameter();
//			Criteria criteria=criteriaParameter.createCriteria();
//			
//			if(!ObjectUtils.isEmpty(order)){
//				switch(order){
//				//过期时间排序
//				case 1:
//					criteriaParameter.setOrderByClause(" coupon_value desc ");
//					break;
//				//最优惠排序
//				case 2:
//					criteriaParameter.setOrderByClause(" effective_end_time desc ");
//					break;
//				}
//			}
//			//电子优惠券有
//			if(couponUserIds.size()>0){
//				
//				criteria.in(SystemConstant.COUPON.ID, couponUserIds);
//				coupons= this.findsCoupon(criteriaParameter);
//				for (Coupon coupon : coupons) {
//					EntityCoupon entityCoupon=new EntityCoupon();
//					BeanUtils.copyProperties(coupon, entityCoupon);
//					entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//					entityCoupons.add(entityCoupon);
//				}
//			}
//			if(entityCouponUserIds.size()>0){
//				// 纸质优惠券有
//				criteria.in(SystemConstant.COUPON.ID, entityCouponUserIds);
//				List<EntityCoupon> findEntityCoupon = this.findEntityCoupon(criteriaParameter);
//				for(EntityCoupon entityCoupon:findEntityCoupon){
//					entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_Entity);
//					entityCoupons.add(entityCoupon);
//				}
//			}
//		
//		}
//		return entityCoupons;
//	}
	@Override
	public List<Coupon> findsCoupon(CriteriaParameter criteriaParameter){
		List<Coupon> selectByExample = couponMapper.selectByExample(criteriaParameter);
		return selectByExample;
	}
	@Override
	public List<EntityCoupon> findEntityCoupon(CriteriaParameter parameter){
		List<EntityCoupon> selectByExample = entityCouponMapper.selectByExample(parameter);
		return selectByExample;
	}
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public void setEntityCouponBinding(EntityCoupon entityCoupon,CouponUser couponUser){
//		CriteriaParameter parameter=new CriteriaParameter();
//		Criteria createCriteria = parameter.createCriteria();
//		
//		
//		//根据优惠券编码查询，如果查找不到，说明优惠券码输入错误
//		createCriteria.equalTo(SystemConstant.COUPON.COUPON_CODE, entityCoupon.getCouponCode());
//		List<EntityCoupon> selectByExample = entityCouponMapper.selectByExample(parameter);
//		if(ObjectUtils.isEmpty(selectByExample)){
//			throw new BusinessException("您的优惠券码输入错误");
//		}
//		//激活状态查询，看是否已经被激活了
//		createCriteria.equalTo(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_NO_USE);
//		selectByExample = entityCouponMapper.selectByExample(parameter);
//		if(ObjectUtils.isEmpty(selectByExample)){
//			throw new BusinessException("您输入的优惠券码已经激活过！");
//		}
//		//说明优惠券码输入正确，并且没有被激活
//		if(selectByExample.size()>0){
//			couponUser.setReceiveTime(new Date());
//			couponUser.setCouponId(selectByExample.get(0).getId());
//			couponUser.setStatus(SystemConstant.COUPON.STATUS_NO_USE);
//			couponUser.setType(SystemConstant.COUPON.TYPE_ECOUPON_Entity);
//			EntityCoupon updateEntityCoupon = selectByExample.get(0);
//			
//			updateEntityCoupon.setUserId(couponUser.getUserId());
//			updateEntityCoupon.setStatus(SystemConstant.COUPON.STATUS_USED);
//			couponUserMapper.insertSelective(couponUser);
//			entityCouponMapper.updateByPrimaryKeySelective(updateEntityCoupon);
//		}
//	}
//	@Override
//	public List<AppCouponOutDto> findsAllUsableCoupon(Long userId){
//		List<AppCouponOutDto> appCouponOutDtos=new ArrayList<>();
//		CriteriaParameter criteriaParameter=new CriteriaParameter();
//		Criteria createCriteria = criteriaParameter.createCriteria();
//		//优惠券的过期时间还没有超过当前的时间
//		createCriteria.greaterThan(SystemConstant.COUPON.EFFECTIVE_END_TIME, new Date());
//		//电子优惠券状态为启用的
//		createCriteria.equalTo(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_NO_USE);
//		List<Coupon> coupons = couponMapper.selectByExample(criteriaParameter);
//		for(Coupon coupon:coupons){
//			Long id = coupon.getId();
//			Integer countByCouponId = countByCouponId(id);
//			AppCouponOutDto appCouponOutDto=new AppCouponOutDto();
//			//把coupon拷贝到appCouponOutDto
//			BeanUtils.copyProperties(coupon, appCouponOutDto);
//			
//			//设定该优惠券已经领取百分比
//			Long couponCount = coupon.getCouponCount();
//			Integer usePercent=(int) (countByCouponId*100/couponCount);
//			appCouponOutDto.setUsePercent(usePercent);
//			
//			//当领取的电子优惠券等于或者超过电子优惠券数量的时候，说明已经领取完了
//			if(countByCouponId>=coupon.getCouponCount()){
//				appCouponOutDto.setType(SystemConstant.COUPON.TYPE_NO_AVAILABLE);
//			}else{//说明优惠券可以领取,只是逻辑上的可以领取，还需要判断用户已经领取了没有
//				
//				//判断该用户是否已经领取了
//				CriteriaParameter couponCriteriapCriteriaParameter=new CriteriaParameter();
//				Criteria couponCriteria = couponCriteriapCriteriaParameter.createCriteria();
//				couponCriteria.equalTo(SystemConstant.COUPON.COUPON_ID, id);
//				couponCriteria.equalTo(SystemConstant.COUPON.TYPE, SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//				couponCriteria.equalTo(SystemConstant.COUPON.USER_ID, userId);
//				List<CouponUser> selectByExample = couponUserMapper.selectByExample(couponCriteriapCriteriaParameter);
//				//coupon_user记录表为空，还没有领取
//				if(ObjectUtils.isEmpty(selectByExample)){
//					appCouponOutDto.setType(SystemConstant.COUPON.TYPE_NO_RECEIVE);
//				}else{
//					//说明已经领取了这个优惠券
//					appCouponOutDto.setType(SystemConstant.COUPON.TYPE_RECEIVED);
//				}
//			}
//			appCouponOutDtos.add(appCouponOutDto);
//		}
//		return appCouponOutDtos;
//	}
	/**
	 * 根据couponId查找该电子优惠券的已领取个数
	 * @Description 
	 * @param couponId
	 * @return
	 * @Author liuw
	 * @Date 2017年8月7日上午9:47:58
	 */
//	@Override
//	public Integer countByCouponId(Long couponId){
//		CriteriaParameter criteriaParameter=new CriteriaParameter();
//		Criteria createCriteria = criteriaParameter.createCriteria();
//		//优惠券id
//		createCriteria.equalTo(SystemConstant.COUPON.COUPON_ID, couponId);
//		//电子优惠券
//		createCriteria.equalTo(SystemConstant.COUPON.TYPE, SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//		int countByExample = couponUserMapper.countByExample(criteriaParameter);
//		return countByExample;
//	}
//	@Override
//	public void updateCouponReceive(AppCouponInDto coupon){
//		CriteriaParameter paramCouponUser=new CriteriaParameter();
//		Criteria criteriaCouponUser = paramCouponUser.createCriteria();
//		//优惠券id
//		criteriaCouponUser.equalTo("coupon_id", coupon.getId());
//		//优惠券类型，1代表电子优惠券
//		criteriaCouponUser.equalTo("type", SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//		
//		//已经领取了的个数
//		int receivedCoupnCount = couponUserMapper.countByExample(paramCouponUser);
//		
//		//总个数
//		Long electronCouponCount = couponMapper.selectByPrimaryKey(coupon.getId()).getCouponCount();
//		//优惠券的领取量大于等于了总允许的数量
//		if(electronCouponCount<=receivedCoupnCount){
//			throw new BusinessException("不好意思，您来晚了，该优惠券已经领取完了!");
//		}
//		
//		//查询之前是否领取了这个优惠券
//		CriteriaParameter param=new CriteriaParameter();
//		Criteria createCriteria = param.createCriteria();
//		createCriteria.equalTo("user_id", coupon.getUserId());
//		createCriteria.equalTo("coupon_id", coupon.getId());
//		List<CouponUser> selectByExample = couponUserMapper.selectByExample(param);
//		if(!CollectionUtils.isEmpty(selectByExample)){
//			throw new BusinessException("您已经领取了一次,请勿重复领取!");
//		}
//		
//		//更新用户优惠券记录表
//		CouponUser couponUser=new CouponUser();
//		couponUser.setReceiveTime(new Date());
//		couponUser.setUserId(coupon.getUserId());
//		couponUser.setType( SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//		couponUser.setCouponId(coupon.getId());
//		couponUser.setStatus(0);
//		couponUserMapper.insertSelective(couponUser);
//	}
//	@Override
//	public List<EntityCoupon> findCoupon(Long userId,
//			CouponSearchInDto couponSearchInDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Integer countByCouponId(Long couponId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public List<AppCouponOutDto> findsAllUsableCoupon(Long userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public void updateCouponReceive(AppCouponInDto coupon) {
//		// TODO Auto-generated method stub
//		
//	}
}
