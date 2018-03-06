package com.zfj.xmy.common;
/**
 * 系统常量
 * @author dengq
 * @createDate 2017年6月8日
 * @desription
 */
public class SystemConstant {

	private SystemConstant(){}
	
	/**
	 * 通查条件的符号:等于(=)
	 */
	public static final String REQ_PARAMETER_EQ = "eq" ;
	public static final String REQ_PARAMETER_EQ_DATE = "eq_date" ;
	/**
	 * 通查条件的符号:不等于(<>)
	 */
	public static final String REQ_PARAMETER_NE = "ne" ;
	public static final String REQ_PARAMETER_NE_DATE = "ne_date" ;
	/**
	 * 通查条件的符号:in(in)
	 */
	public static final String REQ_PARAMETER_IN = "in" ;
	/**
	 * 通查条件的符号:not in(not in)
	 */
	public static final String REQ_PARAMETER_NI = "ni" ;
	/**
	 * 通查条件的符号:模糊连接(like)
	 */
	public static final String REQ_PARAMETER_CN = "cn" ;
	/**
	 * 通查条件的符号:小于等于(<=)
	 */
	public static final String REQ_PARAMETER_LE = "le" ;
	public static final String REQ_PARAMETER_LE_DATE = "le_date" ;
	/**
	 * 通查条件的符号:大于等于(>=)
	 */
	public static final String REQ_PARAMETER_GE = "ge" ;
	public static final String REQ_PARAMETER_GE_DATE = "ge_date" ;
	/**
	 * 通查条件的符号:小等(<)
	 */
	public static final String REQ_PARAMETER_LT = "lt" ;
	public static final String REQ_PARAMETER_LT_DATE = "lt_date" ;
	/**
	 * 通查条件的符号:大于(>)
	 */
	public static final String REQ_PARAMETER_GT = "gt" ;
	public static final String REQ_PARAMETER_GT_DATE = "gt_date" ;
	/**
	 * 通查条件的符号:正则表达式
	 */
	public static final String REQ_PARAMETER_REGEXP = "regexp" ;
	/**
	 * 逻辑连接符 and
	 */
	public static final String REQ_PARAMETER_LOGIC_AND = "and" ;
	/**
	 * 逻辑连接符or
	 */
	public static final String REQ_PARAMETER_LOGIC_OR = "or" ;
	/**
	 * 条件判断 is null 
	 */
	public static final String REQ_PARAMETER_IS_NULL = "null" ;
	/**
	 * 条件判断 is not null 
	 */
	public static final String REQ_PARAMETER_IS_NOT_NULL = "notnull" ;
	/**
	 * 默认分页数为20
	 */
	public static final Integer PAGE_SIZE = 20 ;
	
	/**
	 * pc端的用户session信息
	 */
	public static final String PC_SESSION_INFO = "xmy-pc-web-session-info" ;
	
	//--------------------------------------------------------
	/**
	 * 移动端接口的版本号:1.0版本
	 */
	public static final String MOBILE_VERSION_V10 = "v=1.0" ;
	/**
	 * 请求数据类型：application/json
	 */
	public static final String REQ_CONTENT_TYPE_JSON = "application/json" ;
	/**
	 * 请求数据类型：application/json; charset=utf-8
	 */
	public static final String REQ_CONTENT_TYPE_JSON_CHARSET = "application/json; charset=utf-8" ;
	/**
	 * 请求数据类型：multipart/form-data 
	 */
	public static final String REQ_CONTENT_TYPE_MULTIPART = "multipart/form-data" ;
	/**
	 * 请求数据类型：text/html
	 */
	public static final String REQ_CONTENT_TYPE_HTML = "text/html" ;
	
	
	/**
	 * redis_保存扫码前缀
	 */
	public static final String REDIS_SCANCODE_PREF = "SCANCODE_";
	
	
	/***
	 * 发送短信业务类型  1.注册时发送
	 */
	public static final Integer SENDSMS_TYPE_REGISTER = 1;
	/***
	 * 发送短信业务类型  2.找回密码时发送
	 */
	public static final Integer SENDSMS_TYPE_RETRIEVE = 2;
	
	/**
	 * 发送短信业务类型  3.换绑手机号
	 */
	public static final Integer SENDSMS_TYPE_BINDPHONE = 3;
	
	/**
	 * app图形验证码 保存redis Key前缀
	 */
	public static final String IMAGECODE_DEVID = "IMAGECODE_DEVID_";
	
	
	
	
	
	
	// 首页缓存
	// @author wy
	 
	
	/**
	 *  app端首页缓存redis key
	 */
	public static final String REDIS_B2C_APP_INDEX_DATA = "B2C_APP_INDEX_DATA";
	
	/**
	 *  pc端首页缓存redis key
	 */
	public static final String REDIS_B2C_PC_INDEX_DATA = "B2C_PC_INDEX_DATA";
	
	/**
	 *  wap端首页缓存redis key
	 */
	public static final String REDIS_B2C_WAP_INDEX_DATA = "B2C_WAP_INDEX_DATA";
	/**
	 * APP 端订单下单前底部文字
	 */
	public static final String REDIS_B2C_APP_PREORDER_BANNER = "B2C_APP_PREORDER_BANNER";
	
//	/**
//	 * 使用任意手机号领取优惠券 的 劵分类id (coupon表id)
//	 *//*
//	public static final String REDIS_B2C_COUPON_CATEGORY_BY_PHONE = "B2C_COUPON_CATEGORY_BY_PHONE";*/
	
	
	//--------------------------------------------------------
	public static class SYS_USER{
		/**
		 * 用户状态：删除
		 */
		public static final int STATUS_DELETE = 99 ;
		
		/**
		 * 用户状态：启用
		 */
		public static final int STATUS_START = 0 ;
		
		/**
		 * 用户状态：禁用
		 */
		public static final int STATUS_DISABLED = 1 ;
	}
	/**
	 * 
	 * @author lijie
	 * 订单常量
	 */
	public static class ORDER{
		
		/**
		 * 订单号长度
		 */
		public  static final Integer NO_LENGTH = 12;
		
		/**
		 * 订单号初始值(12位)
		 */
		public static final String INIT_NO = "000000100000";
		
		/**
		 * 订单 删除  状态：未删除
		 */
		public static final Integer IS_DEL_NOT_DELETE = 1 ;
		
		/**
		 * 订单 删除  状态：已经删除
		 */
		public static final Integer IS_DEL_DELETE = 0 ;
		
		/**
		 * 前台 订单状态：待付款
		 */
		public static final Integer STATUS_UNPAID = 1 ;
		/**
		 * 前台 订单状态：待发货
		 */
		public static final Integer STATUS_DELIVERY = 2 ;
		/**
		 * 前台 订单状态：待收货
		 */
		public static final Integer STATUS_FINISH_DELIVERY = 3 ;
		/**
		 * 前台 订单状态：待评价
		 */
		public static final Integer STATUS_WAITTING_COMMENT = 4 ;
		/**
		 * 前台 订单状态：  已完成
		 */
		public static final Integer STATUS_FINISH = 5 ;
		/**
		 * 前台 配送方式：  买家自提
		 */
		public static final Integer DELIVERY_PICK = 0 ;
		/**
		 * 前台 配送方式：  平台配送
		 */
		public static final Integer DELIVERY_SEND = 1 ;
		
		/**
		 * 订单来源 (order_source) pc
		 */
		public static final Integer ORDER_SOURCE_PC = 1;
		
		/**
		 * 订单来源 (order_source) app
		 */
		public static final Integer ORDER_SOURCE_APP = 2;
		
		/**
		 * 订单来源 (order_source) wap
		 */
		public static final Integer ORDER_SOURCE_WAP = 3;
		
		
//		/**
//		 * 支付方式：支付宝
//		 */
//		public static final Integer PAY_TYPE_ALIPAY = 1 ;
//		/**
//		 * 支付方式：微信
//		 */
//		public static final Integer PAY_TYPE_WECHAT = 2 ;
//		/**
//		 * 支付方式：银行卡
//		 */
//		public static final Integer PAY_TYPE_BANKCARD = 3;
//		
		/**
		 * 支付方式：购物卡
		 */
		public static final Integer PAY_TYPE_GIFTCARD = 4 ;
		/**
		 * 支付方式：货到付款
		 */
		public static final Integer PAY_TYPE_PAYDELIVERY = 5 ;
		
		
		
		
		
		/**
		 * 订单状态：待审核
		 */
		public static final int SHIP_STATUS_AUDIT = 1 ;
		/**
		 * 订单状态：待支付
		 */
		public static final int SHIP_STATUS_UNPAID = 2 ;
		/**
		 * 订单状态：供货确认
		 */
		public static final int SHIP_STATUS_SUPPLY = 3 ;
		/**
		 * 订单状态：备货中
		 */
		public static final int SHIP_STATUS_PREPARE = 4 ;
		/**
		 * 订单状态：备货完成
		 */
		public static final int SHIP_STATUS_DELIVERY = 5 ;
		/**
		 * 订单状态：已发货
		 */
		public static final int SHIP_STATUS_FINISH_DELIVERY = 6 ;
		/**
		 * 订单状态：已完成
		 */
		public static final int SHIP_STATUS_ORDER_FINISH = 7 ;
		
		
		/**
		 *  退款状态 - 未退货'
		 */
		public static final Integer RETURN_STATUS_NO_REFUND = 0;
		/**
		 *  退款状态 - 退货中
		 */
		public static final Integer RETURN_STATUS_REFUNDING = 2;
		/**
		 *  退款状态 - 已退货
		 */
		public static final Integer RETURN_STATUS_NO_REFUNDED = 1;
		
		/**
		 * 物流类型    0 普通仓配系统
		 */
		public static final Long LOGISTICS_TYPE_NOMARL = 0L;
		
		/**
		 * 物流类型   1 第三方物流系统-快递鸟
		 */
		public static final Long LOGISTICS_TYPE_OTHER = 1L;
	}
	/**
	 * 
	 * @author lijie
	 * 用户消费（积分）流水
	 */
	public static class userSpendPoints{
		/**
		 * 流水记录类型：消费记录
		 */
		public static final int TYPE_SPEND = 1;
		
		/**
		 * 流水记录类型：积分记录
		 */
		public static final int TYPE_POINT = 2;
		
		/**
		 * 消费类型：存入
		 */
		public static final int SPEND_TYPE_SAVE = 1;
		
		/**
		 * 消费类型：支出
		 */
		public static final int SPEND_TYPE_SPEND = 2;
		
		/**
		 * 记录状态：未删除
		 */
		public static final int STATUS_NODELETE = 1;
		/**
		 * 记录状态：删除
		 */
		public static final int STATUS_DELETE = 0;
		/**
		 * 用户id
		 */
		public static final String USER_ID="user_id";
		/**
		 * 金额还是积分标识
		 */
		public static final String TYPE="type";
		/**
		 * 金额
		 */
		public static final int TYPE_MONEY=1;
		/**
		 * 积分
		 */
		public static final int TYPE_POINT_POINT=2;
		
		/**
		 * sign 0签到积分 
		 */
		public static final int SIGN=1;
		
		/**
		 * sign 1其它 
		 */
		public static final int ORTHER=1;
	}
	/**
	 * 
	 * @author ljie
	 * 角色常量
	 */
	public static class SYS_ROLE{
		
		/**
		 * 角色状态：启用
		 */
		public static final int STATUS_START = 0 ;
		/**
		 * 角色状态：禁用
		 */
		public static final int STATUS_DISABLE = 1 ;
	}
	
	/**
	 * 
	 * @Description 商城内容常量
	 * @Author liuw
	 * @Date 2017年7月5日上午11:16:07
	 */
	public static class CONTENT
	{
		/**
		 * 默认分页大小为10
		 */
		public static final int PAGE_SIZE=10;
		/**
		 * 常量数字0
		 */
		public static final int ZERO=0;
		/**
		 * 常量数字1
		 */
		public static final int ONE=1;
		/**
		 * 常量数字3
		 */
		public static final int THREE=3;
	}
	/**
	 * 
	 * @Description  termData术语表
	 * @Author liuw
	 * @Date 2017年7月7日下午5:57:31
	 */
	public static class TERMDATA
	{
		/**
		 * vid
		 */
		public static final String VID="vid";
		/**
		 * mark标识
		 */
		public static final String MARK="mark";
		/**
		 * column
		 */
		public static final String COLUMN="column";
		/**
		 * parent_id
		 */
		public static final String PARENT_ID="parent_id";
		/**
		 * 为0，也就是代表栏目（一级）
		 */
		public static final int PARENT_IDIS0=0;
		/**
		 * pageBean
		 */
		public static final String PAGEBEAN="pageBean";
		/**
		 * pageCount
		 */
		public static final String PAGECOUNT="pageCount";
		/**
		 * supplier（供应商标识）
		 */
		public static final String SUPPLIER="supplier";
		
		/**
		 * 品牌
		 */
		public static final String BRAND="brand";
		
		/**
		 * 商品类别category标识
		 */
		public static final String CATEGORY="category";
		/**
		 * 全场通用
		 */
		public static final String AllCANUSE="全场通用";
		/**
		 * code
		 */
		public static final String CODE="code";
		/**
		 * email标识
		 */
		public static final String EMAIL="email";
		/**
		 * sender标识
		 */
		public static final String SENDER="sender";
		
		/**
		 * delivery_mode标识
		 */
		public static final String DELIVERY_MODE="delivery_mode";
		/**
		 * 发票设置标识(invoice_setting)
		 */
		public static final String INVOICE_SETTING="invoice_setting";
		/**
		 * 关于香满园H5页面设置（APP）
		 */
		public  static final String APP_ABOUTXMY="app_aboutxmy";
		
		/**
		 * 首页展示
		 */
		public static final Integer SHOW = 0;  
		
		/**
		 * 首页不展示
		 */
		public static final Integer NOT_SHOW = 1;  
		
		/**
		 * 启用
		 */
		public static final Integer YES = 0;
		
		/**
		 * 禁用
		 */
		public static final Integer NO = 1;
		
		/**
		 * 跨境商品
		 */
		public static final String  ACROSSBORDERS = "跨境";
		
		/**
		 * 是跨境商品
		 */
		public static final Integer IS_ACROSSBORDERS = 0;
		
		/**
		 * 不是跨境商品
		 */
		public static final Integer NOT_ACROSSBORDERS = 1;
		/**
		 * pc搜索关键字配置 12 vid后续可变更
		 */
		public static final Long PC_SEARCHVID = 12l;
	}
	/**
	 * 
	 * @Description user_info 用户信息（会员信息）常量
	 * @Author liuw
	 * @Date 2017年7月12日下午8:46:49
	 */
	public static class USERINFO{
		/**
		 * user_id
		 */
		public static final String USER_ID="user_id";
		/**
		 * user id
		 */
		public static final String USERINFO_ID="user_info.id";
		/**
		 * type
		 */
		public static final String TYPE="type";
		/**
		 * spend_type
		 */
		public static final String SPEND_TYPE="spend_type";
		
		/**
		 * 性别:男
		 */
		public static final Integer SEX_MALE = 0;
		/**
		 * 性别：女
		 */
		public static final Integer SEX_FEMALE = 1;
		
		/**
		 * 密码错误，代码-1
		 */
		public static final Integer PASSWORD_ERROR=-1;
		
		/**
		 * 手机号字段（mobile_phone）
		 */
		public static final String MOBILE_PHONE="mobile_phone";
		/**
		 * 邮箱字段（email）
		 */
		public static final String EMAIL="email";
		/**
		 * 状态启用（字符）
		 */
		public static final String STATUS_ON_STRING="0";
		/**
		 * 状态启用（整型）
		 */
		public static final Integer STATUS_ON_INTEGER=0;
		/**
		 * 状态禁用（整型）
		 */
		public static final Integer STATUS_OFF_INTEGER=1;
	}
	/**
	 * 
	 * @Description user表中的常量
	 * @Author liuw
	 * @Date 2017年7月19日下午4:22:42
	 */
	public static class USER{
		/**
		 * status状态，启用
		 */
		public  static final Integer STATUS_ENABLE = 0;
		/**
		 * status状态，禁用
		 */
		public static final Integer STATUS_DISABLE = 1;
		
		/**
		 * user的id字段（user.id）
		 */
		public final  static String USER_ID="user.id";
		/**
		 * user的password
		 */
		public static final String PASSWORD="password";
		
		/**
		 * app 用户本地头像路径
		 */
		public static final String HTYP_XMY_APP = "http://app.xmy365.com/";
		
		/**
		 * 默认赠送积分 (注册 绑定手机 邮箱都赠送50积分)
		 */
		public static final Integer PRESENT_POINTS=50;
	}
	/**
	 * 
	 * @Description UserAddress常量
	 * @Author liuw
	 * @Date 2017年7月20日下午8:03:00
	 */
	public static class USERADDRESS{
		/**
		 * 是默认的收货地址(0)
		 */
		public  static final Integer IS_DEFAULT_AADDRESS=0;
		
		/**
		 * 是否是默认收货地址，字符串："is_default"
		 */
		public  static final String IS_DEFAULT_S="is_default";
		/**
		 * 不是默认的收货地址(1)
		 */
		public  static final Integer IS_NOT_DEFAULT_AADDRESS=1;
		
		/**
		 * 用户的收货地址的id（id，string类型）
		 */
		public  static final String userAddressId="id";
		/**
		 * 用户id（user_id）
		 */
		public  static final String USER_ID="user_id";
		/**
		 * 配送人信息：1
		 */
		public static final Integer DELIVERY = 1;
	}
	/**
	 * 
	 * @Description 广告常量
	 * @Author liuw
	 * @Date 2017年7月18日上午9:23:07
	 */
	public static class AdInfoImage{
		/**
		 * ISCATEGORY: 0
		 */
		public static final Integer ISCATEGORY=0;
		/**
		 *   分类Top图类型（2）
		 */
		public static final Integer TYPE_CLASSIFICATIONTOPIMG=2;
		/**
		 *  type为3，代表开抢啦Top图
		 */
		public static final int TYPE_OPENGRAB=3;
		/**
		 * 类型：广告类型（type=2为APP端分类Top图，type=3为开抢啦Top图）
		 */
		public static final String TYPE="type";
		/**
		 * 广告的id
		 */
		public static final String AD_ID="ad_id";
		/**
		 * type=1 代表广告
		 */
		public static final int TYPE_ADVERTISEMENT=1;
		/**
		 * position_id标识
		 */
		public static final String	POSITION_ID="position_id";
		/**
		 * category标识
		 */
		public static final String CATEGORY_ID="category_id";
		/**
		 * 首页top广告图
		 */
		public static final Integer POSITION_ID_INDEX_TOP=1;
		
		/**
		 *  活动广告图
		 */
		public static final Integer ACTIVITY_ADIMAGE = 1;
		
		/**
		 * 商品广告图
		 */
		public static final Integer GOODS_ADIMAGE = 0;		
				
		/**
		 * 大图
		 */
		public static final Integer BIG_IMAGE = 0;
		
		/**
		 * 小图
		 */
		public static final Integer SMALL_IMAGE = 1;
	}
	/**
	 * 购物卡常量
	 * @Description 
	 * @Author liuw
	 * @Date 2017年8月3日下午8:23:03
	 */
	public static final class SHOPPING_CARD{
		/**
		 * 用户id
		 */
		public static final String USER_ID="user_id";
		/**
		 * 购物卡id
		 */
		public static final String SHOPPING_CARD_ID="shopping_card_id";
		
		/**
		 * @author lij
		 */
		
		/**
		 * 购物卡状态:1:未激活
		 */
		public static final Integer NO_ACTIVE = 1;
		
		/**
		 * 购物卡状态:2:已激活
		 */
		public static final Integer ACTIVE = 2;
		
		/**
		 * 购物卡状态:3 已激活，没有余额了
		 */
		public static final Integer ACTIVE_NO_BALANCE = 2;
		/**
		 * 绑定错误，卡号和密码不正确
		 */
		public static final int RESULTERROR=1;
		/**
		 * 已经被绑定过
		 */
		public static final int RESULTBOUND=2;
	}
	/**
	 * 
	 * @Description  优惠券常量
	 * @Author liuw
	 * @Date 2017年7月31日下午8:23:41
	 */
	public static final class COUPON{
		/**
		 * 用户id
		 */
		public static final String USER_ID="user_id";
		/**
		 * 结果集
		 */
		public static final String RESULT= "result";
		/**
		 * 优惠券id
		 */
		public static final String COUPON_ID="coupon_id";
		/**
		 * 优惠券表的单条记录的id
		 */
		public static final String ID="id";
		/**
		 * 使用状态
		 */
		public static final String STATUS="status" ;
		/**
		 * 使用时间
		 */
		public static final String USE_TIME="use_time";
		
		
		/**
		 * 未使用
		 */
		public static final String NO_USE_STRING="未使用";
		/**
		 * 使用记录
		 */
		public static final String USE_RECORD_STRING="使用记录";
		/**
		 * 已过期
		 */
		public static final String EXPIRED_STRING="已过期";
		
		/**
		 * 优惠券过期时间
		 */
		/*public static final String EFFECTIVE_END_TIME="effective_end_time";*/
		/**
		 * 已经过期<br>
		 * (d1.compareTo(d2)>0)<br>
		 * <br>
		 * d1时间大于d2<br>
		 *  
		 */
		/*public static final int DATA_HAD_EXPIRED=0;*/
		/**
		 * 优惠券头部name标识
		 */
		/*public static final String NAME="name";
		*//**
		 * 优惠券头部count标识
		 *//*
		public static final String COUNT="count";*/
		
		/**
		 * type类型（1 电子优惠券 ； 2 实体纸质优惠券）
		 */
		/*public static final String TYPE="type";*/
		/**
		 * 代表电子优惠券
		 *//*
		public static final Integer TYPE_ECOUPON_ELECTRONICS=1;
		*//**
		 * 代表实体纸质优惠券
		 *//*
		public static final Integer TYPE_ECOUPON_Entity=2;
		
		*//**
		 * 实体优惠券编码
		 *//*
		public static final String COUPON_CODE="coupon_code";*/
		/**
		 * 未领取
		 */
		public static final Integer TYPE_NO_RECEIVE=1;
		/**
		 * 已领取
		 */
		public static final Integer TYPE_RECEIVED=1;
		/**
		 * 已抢完
		 */
		public static final Integer TYPE_NO_AVAILABLE=1;
		
		
		/**
		 * 使用范围 - 全场通用
		 */
		public static final int ALL_USE = 1;

		/**
		 * 使用范围 - 分类使用
		 */
		public static final int CATEGORY_USE = 2;

		/**
		 * 使用范围 - 限定商品
		 */
		public static final int LIMIT_USE = 3;

		/**
		 * 使用范围 - 排除商品
		 */
		public static final int LINE_UP_USE = 4;
		
		/***
		 * 在领券中心 - 显示
		 */
		public static final Integer SHOW_IN_COUPON_CENTER_TRUE = 1;
		/***
		 * 在领券中心 - 不显示
		 */
		public static final Integer SHOW_IN_COUPON_CENTER_FASLE = 0;
		
		/**
		 * COUPON 表 状态 - 启用
		 */
		public static final Integer COUPON_STATUS_ENABLE = 0;
		
		/**
		 * COUPON 表 状态 - 禁用
		 */
		public static final Integer COUPON_STATUS_DISABLE = 1;
	}
	
	/***
	 * coupon_user 表的常量
	 * @author wy
	 *
	 */
	public static final class COUPON_USER{
		/**
		 * 未使用
		 */
		public static final Integer STATUS_NO_USE = 0;
		/**
		 * 已使用
		 */
		public static final Integer STATUS_USED = 1;
		
		
		/**
		 * 在使用中，暂时不能被其它订单使用
		 */
		public static final int STATUS_PENDING = 2;
		
		
		/**
		 * 界面参数 - 使用状态 - 已经过期
		 */
		public static final Integer FRONT_STATUS_EXPIRED = 2;
	}
	
	/**
	 * 
	 * @Description feedback反馈表的常量
	 * @Author liuw
	 * @Date 2017年7月28日下午4:34:18
	 */
	public static final class FEEDBACK{
		/**
		 * 反馈来源为app（type=1）
		 */
		public static final Integer TYPE_APP = 1;
		/**
		 * 当前反馈的状态，未解决（0）
		 */
		public static final Integer STATUS_NO_SOLVE=0;
	}
	/**
	 * 文件保存路径
	 * @ClassName: filePath 
	 * @Description: 
	 * @author hexw
	 * @date 2017年7月19日 下午4:34:28 
	 *
	 */
	public static class FilePath{
		
		/**
		 * 商品图片压缩包解压路径
		 */
		public static String GOODS_IMAGEZIP_FILLPATH = "D://apache-tomcat-7.0.41/webapps/ROOT/file/";
		
	}
	/**
	 * 评论表常量
	 * @ClassName: Comment 
	 * @Description: 
	 * @author hexw
	 * @date 2017年7月19日 下午4:34:28 
	 */
	public static class Comment{
		/**
		 * 订单评论
		 */
		public static int COMMENT_ORDER = 0;
		/**
		 * 商品评论
		 */
		public static int COMMENT_GOODS = 1;
		/**
		 * 评论审核状态：审核通过
		 */
		public static int COMMENT_AUDIT_TRUE = 0;
		/**
		 * 评论审核状态：待审核
		 */
		public static int COMMENT_AUDIT_WAIT = 1;
		/**
		 * 评论审核状态：审核不通过
		 */
		public static int COMMENT_AUDIT_FLASE = 2;
	}
	/**
	 * 
	 * @Description 收藏夹常量
	 * @Author liuw
	 * @Date 2017年7月24日下午2:35:21
	 */
	public static class COLLECTIONGOODS{
		/**
		 * 收藏夹类型为商品,(1)
		 */
		public static final int TYPE_GOODS=1;
		/**
		 * 收藏夹类型为店铺，(2)
		 */
		public static final int TYPE_SHOP=2;
		
		/**
		 * 收藏夹类型("type")
		 */
		public static final String TYPE="type";
		/**
		 * 用户的id(user_id)
		 */
		public static final String USER_ID="user_id";
		/**
		 * 收藏夹商品分类名称
		 */
		public static final String GOODS_CATEGORY_NAME="goods_category_name";
		/**
		 *  收藏夹商品id
		 */
		public static final String GOODS_ID="goods_id";
		
		/**
		 * 收藏夹每天记录的id
		 */
		public static final String ID="id";
		
	}
	/**
	 * 
	 * @Description  足迹表常量
	 * @Author liuw
	 * @Date 2017年7月31日下午2:57:15
	 */
	public static class BROWSEDGOODS{
		/**
		 * 用户id
		 */
		public static final String USER_ID="user_id";
	}
	/**
	 * 商品关联规则
	 * @ClassName: RelevanceGoods 
	 * @Description: 
	 * @date 2017年7月27日 下午1:53:50 
	 *
	 */
	public static class RelevanceGoods{
		
		/**
		 * 商品关联的规则：商品编码12位，从左到右前面10位相同的进行关联
		 */
		public static Integer GOODS_CODE_SUBSTR = 10;
	
	}
	
	public static class GoodsConstant{
		
		public static Integer YES = 0;  // 是
		
		public static Integer NO = 1;  // 否
		
		/**
		 * 上架
		 */
		public static Integer PUTWAY = 0; 
		
		/**
		 * 下架
		 */
		public static Integer NOT_PUTWAY = 1;
    	
    	/**
    	 * 未回收
    	 */
    	public static final Integer NOT_RECYCLE = 1;
    	
		
		/**
		 * 商品的二级分类名称
		 */
		public static final String CATEGORY_NAME="category_name";
		/**
		 * 商品二级分类id
		 */
		public static final String CATEGORY_ID="category_id";
	
		/**
		 * 是否支持全国配送 0; 是
		 */
		public final static Integer IS_DELIVERY_TRUE = 0;
		/**
		 * 是否支持全国配送 1; 否
		 */
		public final static Integer IS_DELIVERY_FALSE = 1;
	}
	
	/**
	 * 限时限量活动
	 * @ClassName: LIMITACTIVITY 
	 * @Description: 
	 * @date 2017年7月31日 上午11:35:13 
	 *
	 */
	public static class LIMITACTIVITY{
		
		public final static Integer STATUS_YES = 0;  // 是
		
		public final static Integer STATUS_NO = 1;  // 否
		
		public final static Integer APP = 2;  // app端
		
		public final static Integer WAP = 1;  // wap端
		
		public final static Integer PC = 3;  // pc端
		/**
		 * 冰点价
		 */
		public final static int FREEZING=1;
		/**
		 * 天天特价
		 */
		public final static int DAYDAY=2;
		
		/**
		 * 每周特价
		 */
		public final static int WEEKWEEK=3;
		
		/**
		 * 整件惠
		 */
		public final static int WOLE=4;
		
		/**
		 * 专题促销
		 */
		public final static int PROMOTIONS =5;
		
		
	}
	
	/**
	 * 消息推送
	 * @author wy
	 *
	 */
	public static class MSG_PUSH_INFO {
		/**
		 * 是否删除（0 存在）
		 */
		public final static Integer DEL_NOMARL = 0;
		/**
		 * 是否删除（1 删除）
		 */
		public final static Integer DEL_DELETE = 1;
		
		/**
		 * 读取状态（0 未读）
		 */
		public final static Integer READSTATUS_UNREAD = 0;
		/**
		 * 读取状态（1 已读）
		 */
		public final static Integer READSTATUS_READ = 1;
	}
	
	/**
	 * 极光推送记录表
	 * @author wy
	 *
	 */
	public static class JIGUANG_PUSH_RECORD {
		/**
		 * 推送业务类型 - 0:全体广播
		 */
		public final static Integer TYPE_ALL_BROCAST = 0;
		/**
		 *  推送业务类型 -  1：分组标签广播 
		 */
		public final static Integer TYPE_GROUP_BROCAST = 1;
		/**
		 *  推送业务类型 -  2:一对一推送
		 */
		public final static Integer TYPE_TO_SINGLE = 2;
	}
	
	/**
	 * HTML key 类 获取H5页面 的 接口常量
	 * @author wy
	 *
	 */
	public static class HTML_KEY {
		/**
		 * 协议页面
		 */
		public static final String PROTOCOL = "protocol";
	}
	
	
	
	
	/**
	 * 分类规格
	 * @ClassName: CATEGORY_SPEC 
	 * @Description: 
	 * @date 2017年8月8日 下午7:45:21 
	 *
	 */
	public static class CATEGORY_SPEC{
		
		/**
		 * 首页菜单
		 */
		public static final Integer TYPE_MENU = 0;
		
		/**
		 * 类别规格
		 */
		public static final Integer TYPE_SPEC = 1;
		
		/**
		 * 种类
		 */
		public static final String KIND = "种类"; 
		
		/**
		 * parentId
		 */
		public static final	long PARENT_ID = 0;
	}
	
	
	/**
	 * 商品图片
	 * @ClassName: GOODS_IMAGE 
	 * @Description: 
	 * @date 2017年8月10日 下午4:13:33 
	 *
	 */
	public static class GOODS_IMAGE{
		
		/**
		 * 商品主图
		 */
		public static final Integer GOODS_MIAN_IMAGE = 1;
	}
	
	/**
	 * 支付相关
	 * @author wy
	 *
	 */
    public static class PAY{
		
		/**
		 * 业务类型 - 购买商品
		 */
		public static final Integer BUY_GOODS = 1;
	}
    
    /**
     * Pc商品设置常量
     * @author lij
     */
    public static class PCGOODSSET{
    	/**
    	 * 热卖商品 1
    	 */
    	public static final Integer HOTGOODS = 1;
    	/**
    	 * 新品上市 2
    	 */
    	public static final Integer NEWGOODS = 2;
    	/**
    	 * 热销商品 3
    	 */
    	public static final Integer HOTSEALGOODS = 3;
    }
    
    /**
     * 活动类型 （主要用于区分活动属于那张表）
     * @ClassName: ACTIVITY 
     * @Description: 
     * @date 2017年8月19日 上午10:43:07 
     *
     */
    public static  class ACTIVITY{
    	
    	/**
    	 * 买即赠（buy_and_present）
    	 */
    	public static final int BYU_AND_PRESENT_ACTIVITY = 0;
    	
    	/**
    	 * 专题促销活动(promotion_activity)
    	 */
    	public static final int PROMOTION_ACTIVITY = 1;
    	
    	/**
    	 * 限时限量活动  （limit_activity表）
    	 */
    	public static final int LIMIT_ACTIVITY = 2;
    	
    	/**
    	 * 积分活动
    	 */
    	public static final int POINTS_ACTIVITY = 3 ;
    }
    /**
     * 专题页面
     * @Description 
     * @Author liuw
     * @Date 2017年8月29日上午10:08:38
     */
    public static class TOPICPAGE{
    	/**
    	 * 已经解压
    	 */
    	public static final int UNZIP=1;
    	/**
    	 * 没有解压
    	 */
    	public static final int ISZIP=0;
    }

    /**
     * 配置模板常量
     * @Description 
     * @Author liuw
     * @Date 2017年8月30日上午10:05:40
     */
    public static class INDEXCONFIG{
    	/**
    	 * app首页配置 1
    	 */
    	public static final Long APPINDEXCONFIG=1L;
    	/**
    	 * pc首页配置 2
    	 */
    	public static final Long PCINDEXCONFIG=2L;
    	/**
    	 * 精选首页配置 3
    	 */
    	public static final Long CHOICEFRUITSETTING=3L;
    	
    	/**
    	 * 跨境专区配置 4
    	 */
    	public static final Long CROSSBORDERSETTING=4L;
    }
    
    /**
     * xmywap端常量
     * @author CJ
     * @createDate 2017年9月27日
     *
     */
    public static class XMYWAP{
    	/**
    	 * 登录页面
    	 */
    	public static final String LOGIN = "index/login";
    	
    	/**
    	 * wap端session
    	 */
    	public static final String WAP_SESSION = "wap_session";
    	
    	/**
    	 * 注册页面
    	 */
    	public static final String REGISTER = "index/register"; 
    	
    	/**
    	 * 拼购抽奖和三免一
    	 */
    	public static final Long[] ACTIVITY_ID = new Long[]{(long)7725,(long)7726,(long)7727,(long)7728,(long)7729,(long)7730,(long)7878};

    	/**
    	 * 拼购抽奖
    	 */
    	public static final Long[] LUCK_ID = new Long[]{(long) 7265,(long) 7266,(long) 7267,(long) 7269,(long) 7318,(long) 7270,(long) 7271,(long) 7272,(long) 7273};
    }
    
    /**
     *   xmy园线上地址
     * @ClassName: XMYONLINEURL 
     * @Description: 
     * @date 2017年9月28日 下午8:07:36 
     *
     */
    public static class XMYONLINEURL {
    	
    	/**
    	 * 香满园 线上pc地址
    	 */
    	public static final String XMY_PC_URL = "http://www.xmy365.com/";
    	
    	
    }
    
    /**
     * 买即赠
     * @ClassName: BUYANDPRSENT 
     * @Description: 
     * @date 2017年10月21日 下午5:17:01 
     *
     */
    public static class BUYANDPRSENT{
    	
    	/**
    	 * 状态 -启用
    	 */
    	public static final Integer USERING = 0;
    	
    	/**
    	 * 状态 -禁用 
    	 */
    	public static final Integer NOT_USEING = 1;
    	
    }
    
    
    /**
     * 专题促销
     * @ClassName: PROMOTIONACTIVITY 
     * @Description: 
     * @date 2017年11月14日 上午11:42:53 
     *
     */
    public static class PROMOTIONACTIVITY{
    	
     	/**
    	 * 状态 -启用
    	 */
    	public static final Integer USERING = 0;
    	
    	/**
    	 * 状态 -禁用 
    	 */
    	public static final Integer NOT_USEING = 1;
    }
    
    /**
     * 支付记录表
     * @ClassName: TRADEPRODUCTION 
     * @Description: 
     * @date 2017年12月17日 下午3:20:25 
     *
     */
    public static class  TRADEPRODUCTION {
    	
    	/**
    	 * 待交易
    	 */
    	public static final Integer TRANSTATE_WAIT = 1 ;
    	
    	/**
    	 * 交易成功
    	 */
    	public static final Integer TRANSTATE_SUCCESS = 2 ;
    	
      	/**
    	 * 交易失败
    	 */
    	public static final Integer TRANSTATE_FAIL = 3 ;
    }
    public static class NEWACIVITY{
    	/**
    	 * 拼购抽奖
    	 */
    	public static final Long LOTTERY_GOODS_ID=7878L;
    	/**
    	 * 买三免一活动商品ID 
    	 */
    	public static final Long[] BUY_THREE_FREE_ONE={7725L,7726L,7727L,7728L,7729L,7730L};
    	/**
    	 * 免邮费商品
    	 */
    	public static final Long[] FEE_FREIGTH_GOODS={7265L,7266L,7267L,7269L,7318L,7270L,7271L,7272L,7273L};
    	/**
    	 * 满额减商品
    	 */
    	public static final Long[] IS_TRUE_SUB={7632L,7633L,7634L,7635L,7637L,7638L,7639L,7641L,7694L,7642L,7643L,7644L,7645L,7646L,7647L};
   
    	/**
    	 * 优惠券商品
    	 */
    	public static final Long COUPON_GOODS=7878L;
    	
    	/**
    	 * 购买的优惠券商品对应的抵用券id
    	 */
    	public static final Long[] COUPON_GOODS_COUPON_ID={1514512078287L,1514512078288L,1514512078289L};
    	
    }
    
    /**
     * 优惠券赠送活动
     * @author Administrator
     *
     */
    public static class PRESENTCOUPONACTIVITY{
    	
    	/**
    	 * 活动开启状态 
    	 */
    	public static int OPEN_STATUS = 0 ; 
    	
    	/**
    	 * 活动关闭状态
    	 */
    	public static int CLOSE_STATUS = 1 ;
    	
    }
    
}
