package com.zfj.xmy.goods.service.cms;  

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDir;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDto;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegVo;

/** 
 * @Title: CategoryWordSegService.java 
 * @Package com.zfj.xmy.goods.service.cms 
 * @Description: 
 * @author hexw
 * @date 2017年8月8日 下午8:01:47 
 */
public interface CategoryWordSegService {
	
	/**
	 * 新增分类规格
	 * @param vo
	 * @return    
	 * @return CategoryWordSeg    
	 * Date:2017年8月8日 下午8:30:34 
	 * @author hexw
	 */
	CategoryWordSeg insertCategorySpec(CategoryWordSegVo vo);
	
	/**
	 * 根据分类id查询分类规格
	 * @param cid
	 * @return    
	 * @return List<CategoryWordSegDto>    
	 * Date:2017年8月9日 上午9:12:33 
	 * @author hexw
	 */
	List<CategoryWordSegDto> findCategoryWordSegDto(long cid);
	
	/**
	 * 删除规格
	 * @param id
	 * @return    
	 * @return int    
	 * Date:2017年8月9日 上午9:27:50 
	 * @author hexw
	 */
	int deleteSpec(long id);
	
	/**
	 * 根据id查询规格
	 * @param id
	 * @return    
	 * @return CategoryWordSegDto    
	 * Date:2017年8月9日 上午9:46:24 
	 * @author hexw
	 */
	CategoryWordSegDto getCategoryWordSegDto(long id);
	
	/**
	 * 修改分类规格
	 * @param vo
	 * @return    
	 * @return int    
	 * Date:2017年8月9日 上午10:08:07 
	 * @author hexw
	 */
	int updateCategoryWordSeg(CategoryWordSegVo vo);
	
	/**
	 * 查询指定分类的分词
	 * @param cid
	 * @return    
	 * @return List<CategoryWordSegDir>    
	 * Date:2017年8月9日 下午4:25:45 
	 * @author hexw
	 */
	List<CategoryWordSegDir> findCategoryWordSegByCid(long cid);
	
	/**
	 * 商品搜索词汇
	 * @param goodsId
	 * @return    
	 * @return List<String>    
	 * Date:2017年8月10日 上午10:27:07 
	 * @author hexw
	 */
	List<String> findGoodsSpec(long goodsId);
	
	/**
	 * 根据id得到分词
	 * @param id
	 * @return    
	 * @return CategoryWordSeg    
	 * Date:2017年8月12日 下午2:09:07 
	 * @author hexw
	 */
	CategoryWordSeg getCategoryWordSeg(long id);

}
  