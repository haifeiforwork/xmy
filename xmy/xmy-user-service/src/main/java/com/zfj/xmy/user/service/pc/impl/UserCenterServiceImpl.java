package com.zfj.xmy.user.service.pc.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.lang.Validator;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.log.LogExp;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.base.util.common.DateUtil;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.CommonUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.BrowsedGoodsMapper;
import com.zfj.xmy.common.persistence.dao.CollectionGoodsMapper;
import com.zfj.xmy.common.persistence.dao.FeedbackMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardRecordMapper;
import com.zfj.xmy.common.persistence.dao.UserAnswerMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.dao.UserQuestionMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.BrowsedGoods;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.Feedback;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAnswer;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserQuestion;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.user.service.pc.PcSmsLogService;
import com.zfj.xmy.user.service.pc.UserCenterService;
import com.zfj.xmy.util.EmailUtil;

@Service
public class UserCenterServiceImpl extends BaseService implements UserCenterService{
	@Autowired
	private UserMapper userMapper ;
	
	@Autowired
	private UserInfoMapper userInfoMapper ;
	
	@Autowired
	private UserQuestionMapper userQuestionMapper;
	
	@Autowired
	private UserAnswerMapper userAnswerMapper;
	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper;
	
	@Autowired
	private CollectionGoodsMapper collectionGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private PcSmsLogService pcSmsLogService;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private BrowsedGoodsMapper browsedGoodsMapper;
	
	@Autowired
	private ShoppingCardRecordMapper shoppingCardRecordMapper;
	
	
	
	//三个月 、一年
	private final static int TRIMESTER=3;
	private final static int YEAR=12;
	
	@Override
	public UserInfo findUserinfo(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateUserInfo(UserInfo userInfo) {
		return userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public User findUser(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<UserQuestion> findQuestion(int type) {
		ReqData reqData=new ReqData();
		reqData.putValue("type", type,SystemConstant.REQ_PARAMETER_EQ);
		List<UserQuestion> list=userQuestionMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}

	@Override
	public void insertAnswer(UserAnswer userAnswer) {
		userAnswerMapper.insert(userAnswer);
	}

	@Override
	public List<UserAnswer> findAnswer(Long id) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		List<UserAnswer> list=userAnswerMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}

	@Override
	public List<UserSpendPoints> findRecord(Long id) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", SystemConstant.userSpendPoints.TYPE_SPEND,SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> list=userSpendPointsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}

	@Override
	public void findRecordByPage(Long id,PageBean pageBean,int length) {
		ReqData reqData=new ReqData();
		if(length==-3){
			Date date=DateUtil.addMonthOfYear(new Date(), length);
			String time=DateUtil.format(date, "yyyy-MM-dd");
			reqData.putValue("use_time", time,SystemConstant.REQ_PARAMETER_GE);
		}
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("use_time");
		List<ShoppingCardRecord> list=shoppingCardRecordMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		int count=shoppingCardRecordMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	public List<Goods> findGoodsCollect(Long id) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		List<CollectionGoods> list=collectionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		List<Goods> goods=new ArrayList<>();
		for(CollectionGoods collect:list){
			goods.add(goodsMapper.selectByPrimaryKey(collect.getGoodsId()));
		}
		return goods;
	}
	
	@Override
	public void findGoodsCollectPage(Long id, PageBean pageBean,String category,String goodsName) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		if(category!=null&&!"null".equals(category)){  //根据分类搜索
			reqData.putValue("goods_category_name", category,SystemConstant.REQ_PARAMETER_CN);
		}
		List<CollectionGoods> list=collectionGoodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		List<Goods> goods=new ArrayList<>();
		for(CollectionGoods collect:list){
			Goods gd=goodsMapper.selectByPrimaryKey(collect.getGoodsId());
			if (ObjectUtil.isNotNull(gd)) {
				gd.setImgPath(commonGoodsService.getFirstGoodsImg(collect.getGoodsId()));
				if (!StringUtil.isEmpty(goodsName)) { //根据商品名称搜索
					if (gd.getName().contains(goodsName)) {
						goods.add(gd);
					}
				} else {
					goods.add(gd);
				}
			}
		}
		pageBean.setData(goods);
		int count=collectionGoodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	public void findAdviseByPage(Long id, PageBean pageBean) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		List<Feedback> list=feedbackMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		int count=feedbackMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	public void addAdvise(Feedback feedback) {
		feedbackMapper.insert(feedback);
	}

	@Override
	public void delAdvise(Long id) {
		feedbackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void findShopCard(long id, PageBean pageBean,int status) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		
		if(status==TRIMESTER){
			Date time=DateUtil.addMonthOfYear(new Date(), -TRIMESTER);
			String date=DateUtil.format(time, "yyyy-MM-dd");
			reqData.putValue("create_time", date,SystemConstant.REQ_PARAMETER_GT);
		}
		
		if(status==YEAR){
			Date time=DateUtil.addMonthOfYear(new Date(), -YEAR);
			String date=DateUtil.format(time, "yyyy-MM-dd");
			reqData.putValue("create_time", date,SystemConstant.REQ_PARAMETER_GT);
		}
		
		List<ShoppingCard> list=shoppingCardMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		int count=shoppingCardMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	public void updatePhone(long id,String phone,String code) {
		CommonUtil.validEmpty(phone,"手机号码不能为空") ;
		CommonUtil.validEmpty(code,"手机校验码不能为空") ;
		//1、校验手机验证码是否过期
		pcSmsLogService.valid(code,phone) ;
		//2、校验手机号码是否被注册
		boolean isExist = validUserNameOrMobilePhone(phone,1) ;
		if(!isExist){
			LogExp.error(logger,"该手机号码已经被使用") ;
		}
		UserInfo userInfo=new UserInfo();
		userInfo.setId(id);
		userInfo.setMobilePhone(phone);
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
		
		User user=new User();
		user.setId(id);
		user.setName(phone);
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public boolean validUserNameOrMobilePhone(String key, Integer type) {
		if(0 != type && 1 != type){
			return false ;
		}
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		if(0 == type){//验证用户名是否已经存在
			criteria.equalTo("name",key) ;
			List<User> uList = userMapper.selectByExample(parameter) ;
			if(!CollectionExtUtils.isEmpty(uList)){
				return false ;
			}
		}else{//验证手机号码
			criteria.equalTo("mobile_phone",key) ;
			List<UserInfo> uiList = userInfoMapper.selectByExample(parameter) ;
			if(!CollectionExtUtils.isEmpty(uiList)){
				return false ;
			}
		}
		return true ;
	}

	@Override
	public void updateImg(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public void addFootprint(long userId, long goodsId) {
		BrowsedGoods browsedGoods=new BrowsedGoods();
		browsedGoods.setUserId(userId);
		browsedGoods.setGoodsId(goodsId);
		browsedGoods.setBrowsedTime(new Date());
		browsedGoodsMapper.insert(browsedGoods);
	}

	@Override
	public String sendEmail(String email) {
		String code = RandomUtil.randomNumbers(4);
		String str="亲爱的用户:"+System.getProperty("line.separator")+
				"您好！"+System.getProperty("line.separator")+System.getProperty("line.separator")+
				"您正在香满圆进行邮箱验证，本次请求的验证码为:"+System.getProperty("line.separator")+System.getProperty("line.separator")+code+System.getProperty("line.separator")+
				"<font color=\"#9B9B9B\">(为了保障您帐号的安全性，请勿将验证码告诉给他人。)</font>"+System.getProperty("line.separator")+
				"香满圆";
		EmailUtil.sendEmail(email, "香满圆系统邮件", str);
		System.out.println();
		return code;
	}
	
	@Override
	public void deleteCollectionGoods(String idStr,Long userId){
		String[] ids = idStr.split("\\,");
		if (ids.length > 0 ) {
			List<Object> idList = new ArrayList<Object>();
			for (String id : ids) {
				idList.add(Long.parseLong(id));
			}
			CriteriaParameter para=new CriteriaParameter();
			Criteria createCriteria = para.createCriteria();
			createCriteria.equalTo("user_id", userId);
			createCriteria.in("goods_id", idList);
			collectionGoodsMapper.deleteByExample(para);
		}
		
	}
	
	@Override
	public int voildMobilePhone(String phone) {
		int result = 0;
		ReqData reqData = new  ReqData();
		reqData.putValue("mobile_phone", phone, SystemConstant.REQ_PARAMETER_EQ);
		List<UserInfo> list = userInfoMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() >0) {
			result = 1;
		}
		return result;
	}
}
