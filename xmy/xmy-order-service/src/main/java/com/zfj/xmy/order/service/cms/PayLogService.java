package com.zfj.xmy.order.service.cms;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.PayLog;

public interface PayLogService {

	void findTest(PageBean pageBean,ReqData reqData) ;
	
	void findTest2(PageBean pageBean,ReqData reqData) ;
	
	void updatePaylog(PayLog paylog) ;
}
