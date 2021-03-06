package com.zfj.xmy.activity.service.pc;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcContainerDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcIndexDto;
import com.zfj.xmy.common.persistence.pojo.Goods;

/**
 * @author lij
 * 
 */
public interface PcIndexConfigService {
	/**
	 * @return List<PcIndexDto>
	 * @author lic
	 * @date 2017年8月14日 上午10:39:47
	 * 查询首页广告图片
	 */
	PcIndexDto findHomeAd();
	/**
	 * @return List<PcContainerDto>
	 * @author lij
	 * @date 2017年8月14日 上午11:18:15
	 * 查询货柜信息
	 */
	List<PcContainerDto> findPcContainer();
	/**
	 * 暂时没使用该方法
	 * @return List<PcContainerDto>
	 * @author lij
	 * @date 2017年10月13日 下午5:35:36
	 */
	List<PcContainerDto> findContainerDtos();
	/**
	 * 最新改版(新增关键字)
	 * @return List<PcContainerDto>
	 * @author lij
	 * @date 2017年10月13日 下午5:38:37
	 */
	List<PcContainerDto> findCOntainers();
	/**
	 * 分页查询货柜商品
	 * @param containerId
	 * @return List<Goods>
	 * @author lij
	 * @date 2017年10月13日 下午5:59:44
	 */
	List<Goods> findContainerGoods(Long containerId,Integer pageIndex,Integer consize,PageBean pageBean);
	/**
	 * 查询首页关键字
	 * @return List<String>
	 * @author lij
	 * @date 2017年11月22日 下午2:22:44
	 */
	List<String> findSearchKeyWords();
}
