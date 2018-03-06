package com.zfj.xmy.goods.service.cms;  

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDir;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegVo;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;

/** 
 * @Title: CategoryService.java 
 * @Package com.zfj.xmy.goods.service 
 * @Description: 
 * @author hexw
 * @date 2017年6月19日 上午11:21:40 
 */
public interface CategoryService {

	/**
	 * 查找分类
	 * @return    
	 * @return List<TermDataDir>    
	 * Date:2017年6月15日 下午1:46:25
	 */
	List<TermDataDir> findCategory(Integer status);
	

	

    /**
     * 
     * @Description 根据条件查询分类列表（不分页）
     * @param reqData 
     * @return
     * @Author liuw
     * @Date 2017年7月17日上午9:47:16
     */
	List<TermDataDir> selectCategory(ReqData reqData);


	/**
	 * 根据分类id查询分类 分词
	 * @param id
	 * @return    
	 * @return List<CategoryWordSeg>    
	 * Date:2017年7月20日 上午10:26:42 
	 * @author hexw
	 */
	List<CategoryWordSeg> findCategoryWordSeg(long id);


	/**
	 * 删除分词
	 * @param id    
	 * @return void    
	 * Date:2017年7月20日 上午11:10:11 
	 * @author hexw
	 */
	int deleteCategoryWordSeg(long id);


	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return    
	 * @return int    
	 * Date:2017年7月21日 上午11:51:41 
	 * @author hexw
	 */
	int updateCategoryStatus(long id, int status);


	
	/**
	 * 添加类别分词
	 * @param vo    
	 * @return void    
	 * Date:2017年8月12日 下午1:55:10 
	 * @author hexw
	 */
	CategoryWordSeg insertCategoryWordSeg(CategoryWordSegVo vo);



	/**
	 * 修改类别分词
	 * @param categoryWordSeg
	 * @return    
	 * @return int    
	 * Date:2017年8月12日 下午2:17:33 
	 * @author hexw
	 */
	int updateCategoryWordSeg(CategoryWordSeg categoryWordSeg);



	/**
	 * 修改类别
	 * @param termData    
	 * @return void    
	 * Date:2017年8月12日 下午2:40:34 
	 * @author hexw
	 */
	void updateCategory(TermData termData);

}
  