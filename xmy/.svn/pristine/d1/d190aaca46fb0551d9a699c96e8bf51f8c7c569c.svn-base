package com.zfj.xmy.order.service.cms;

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.OrderPath;
import com.zfj.xmy.common.persistence.pojo.SysUser;

public interface OrderPathService {
	
	List<OrderPath> findOrderPathByOrderId(ReqData reqData);
	
	void addOrderPath(OrderPath orderPath);
	
	void addOrderPath(long orderId,String content,SysUser sysUser);
}
