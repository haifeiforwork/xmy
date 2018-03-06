package com.zfj.xmy.activity.service.app.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.zfj.base.util.common.DateUtil;
import com.zfj.xmy.activity.persistence.cms.dao.PointsGoodsExMapper;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;
import com.zfj.xmy.activity.service.app.PointsService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.PointsActivityMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.dao.VocabularyMapper;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.common.service.CommonGoodsService;

@Service
public class PointsServiceImpl extends BaseService implements PointsService{

	@Autowired
	private PointsActivityMapper pointsActivityMapper;
	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper;
	
	@Autowired
	private PointsGoodsExMapper pointsGoodsExMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private VocabularyMapper vocabularyMapper;
	@Autowired
	private TermDataMapper termDataMapper;
	
	private static final Integer STATUS=0;
	private final static int SIGN=0;
	
	@Override
	public List<UserSpendPoints> findUserSpendPoints(Long uid,int type) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id",uid,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type",type,SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> list=userSpendPointsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	
	@Override
	public List<PointsActivity> findPointsActivity() {
		ReqData reqData=new ReqData();
		reqData.putValue("status", STATUS,SystemConstant.REQ_PARAMETER_EQ);
		List<PointsActivity> list=pointsActivityMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	
	@Override
	public List<PointsGoodsDto> findPointGoods(Long aid) {
		ReqData reqData = new ReqData();
		reqData.putValue("p.points_id",aid, SystemConstant.REQ_PARAMETER_EQ);
		List<PointsGoodsDto> list = pointsGoodsExMapper.selectPointsGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for(PointsGoodsDto point:list){
			point.setImgPath(commonGoodsService.getFirstGoodsImg(point.getGoodsId()));
		}
		return list;
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer sign(Long userId){
		CriteriaParameter para=new CriteriaParameter();
		
		boolean sign = this.isSign(userId);
		if(sign){
			throw new BusinessException("您今天已经签过了!");
		}else{
			//默认为5分
			Integer points=5;
			Integer signDays = this.signDays(userId);//签到天数
			
			if(signDays==null||signDays==0){
				signDays=1;
			}else if(signDays<7){
				signDays+=1;
				points=points*signDays;
			}else if(signDays==7){
				signDays=1;
			}else{
				signDays=1;
			}
			//积分存到userinfo表中
			UserInfo selectByPrimaryKey = userInfoMapper.selectByPrimaryKey(userId);
			if(selectByPrimaryKey.getAccPoints()!=null)
				selectByPrimaryKey.setAccPoints(selectByPrimaryKey.getAccPoints()+points);
			else
				selectByPrimaryKey.setAccPoints(points);
			userInfoMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
			
			//写记录进去到积分记录表
			UserSpendPoints userSpendPoints=new UserSpendPoints();
			userSpendPoints.setCreatTime(new Date());
			userSpendPoints.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SAVE);
			userSpendPoints.setUserId(userId);
			userSpendPoints.setRemarks("签到得"+points+"积分");
			userSpendPoints.setType(SystemConstant.userSpendPoints.TYPE_POINT);
			userSpendPoints.setMoneyPoint(new BigDecimal(points));
			userSpendPoints.setDays(signDays);
			userSpendPoints.setSign(SIGN);
			userSpendPoints.setConsole("admin");
			
			userSpendPointsMapper.insertSelective(userSpendPoints);
			
			return points;
		}
	}
	@Override
	public boolean isSign(Long userId){
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		Date date=new Date();
		String today=DateUtil.format(date, "yyyy-MM-dd");
		String yesterday=DateUtil.format(DateUtil.addDayOfYear(date, -1), "yyyy-MM-dd");
		createCriteria.equalTo("sign", 0);
		createCriteria.equalTo("user_id", userId);
		createCriteria.greaterThan("creat_time", today);
		List<UserSpendPoints> selectByExample = userSpendPointsMapper.selectByExample(para);
		//判断是否已经签到了
		if(!ObjectUtils.isEmpty(selectByExample)&&selectByExample.size()>0)
			return true;
		return false;
	}
	@Override
	public Integer signDays(Long userId){
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		Date date=new Date();
		String yesterday=DateUtil.format(DateUtil.addDayOfYear(date, -1), "yyyy-MM-dd");
		createCriteria.equalTo("sign", 0);
		createCriteria.equalTo("user_id", userId);
		createCriteria.greaterThan("creat_time", yesterday);//比昨天0点晚的签到
		para.setOrderByClause("  creat_time desc");
		List<UserSpendPoints> selectByExample = userSpendPointsMapper.selectByExample(para);
		//判断是否已经签到了
		if(!ObjectUtils.isEmpty(selectByExample)&&selectByExample.size()>0)//如果今天没签到就是一条记录，今天签到了就是二条记录
			return selectByExample.get(0).getDays();//返回签到天数
		return 0;
	}
	public static void main(String[] args) {
		Date date=new Date();
		String today=DateUtil.format(date, "yyyy-MM-dd");
		String yesterday=DateUtil.format(DateUtil.addDayOfYear(date, -1), "yyyy-MM-dd");
		System.out.println(today);
		System.out.println(yesterday);
	}

	@Override
	public TermData memberRight(TermData termData) {
		 TermData result = null;

		 //从vovabulary查询出vid
	    CriteriaParameter vocabularyParam = new CriteriaParameter();
	    CriteriaParameter.Criteria vocabularycreateCriteria = vocabularyParam.createCriteria();
	    vocabularycreateCriteria.equalTo("mark", "app_memnber_article");
	    Long vid = null;
	   List<Vocabulary> selectByExample = vocabularyMapper.selectByExample(vocabularyParam);
	    if (!CollectionUtils.isEmpty(selectByExample)) {
	      vid = ((Vocabulary)selectByExample.get(0)).getId();
	    }

	    //根据weight和vid查出对应的那条() weight取值(1 赚积分 2会员日 3神秘好礼 4 超值抢购 5专题狂欢)
	    CriteriaParameter termParam = new CriteriaParameter();
	    CriteriaParameter.Criteria termcreateCriteria = termParam.createCriteria();
	    termcreateCriteria.equalTo("weight", termData.getWeight());
	    termcreateCriteria.equalTo("vid", vid);

	    PageBean pageBean = new PageBean();
	    List<TermData> selectByExampleWithBLOBs = termDataMapper.selectByExampleWithBLOBs(termParam, pageBean.getRowBounds());
	    if (!CollectionUtils.isEmpty(selectByExampleWithBLOBs)) {
	      result = (TermData)selectByExampleWithBLOBs.get(0);
	    }

	    return result;
	}
}
