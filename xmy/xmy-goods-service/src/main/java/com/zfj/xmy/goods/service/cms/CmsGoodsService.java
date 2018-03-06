package com.zfj.xmy.goods.service.cms; 

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;

public interface CmsGoodsService {
	
	
	/**
	 * 新增商品
	 * @param good    
	 * @return void    
	 * Date:2017年6月19日 上午9:39:29
	 */
	void insertGood(GoodsWithBLOBs good,String[] imgPath,String[] sort,String specIdStr);
	

	
	/**
	 * 修改上架状态
	 * @param idArry
	 * @param status
	 * @return    
	 * @return int    
	 * Date:2017年6月22日 下午5:10:08
	 */
	int updatePutwayStatus(String[] idArry, String status);
	
	/**
	 * 查询商品 分页
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年6月26日 下午3:59:01
	 */
	void selectGoodsAndPage(ReqData reqData, PageBean pageBean);


	/**
	 * 根据id得到商品
	 * @param id
	 * @return    
	 * @return GoodsWithBLOBs    
	 * Date:2017年6月26日 下午4:56:34
	 */
	GoodsWithBLOBs getGoods(long id);


	/**
	 * 修改商品
	 * @param goods    
	 * @return void    
	 * Date:2017年6月26日 下午6:11:36
	 */
	void updateGoods(GoodsWithBLOBs goods,String[] imgPath,String[] sort,String specIdStr);


	/**
	 * 删除商品
	 * @param idArry
	 * @return    
	 * @return int    
	 * Date:2017年6月27日 上午9:53:32
	 */
	int deleteGoods(String[] idArry);
	
	/**
	 * 
	 * @Description 查询符合条件的所有商品（没有分页）
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午3:04:31
	 */
	List<Goods> findGoods(ReqData reqData);

	
	/**
	 * 查找商品图片
	 * @param goodsId
	 * @return    
	 * @return List<GoodsImage>    
	 * Date:2017年7月13日 下午7:57:20
	 */
	List<GoodsImage> findImage(long goodsId);


	/**
	 * 删除图片商品
	 * @param id
	 * @return    
	 * @return int    
	 * Date:2017年7月13日 下午8:19:22
	 */
	int deleteGoodsImage(String id);


	/**
	 * 商品图片关联
	 * @param fileName 压缩包路径  
	 * @param ossPath  oss上传路径 
	 * @return void    
	 * Date:2017年7月19日 上午10:20:23 
	 * @author hexw
	 */
	Map<String,Object> ImgCorrelation(String fileName,String targetPath,String ossPath);


	/**
	 * 根据商品id集合，查询出所有的商品
	 * @Description 
	 * @param ids
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日下午3:36:17
	 */
	List<Goods> findsGoodsWithIds(List<Object> ids);



	Map<String, Object> goodsImageCorrelation();


	void updateGoodsImage();


	/**
	 * 复制商品
	 * @param goodsId
	 * @return    
	 * @return int    
	 * Date:2017年11月7日 下午1:57:54 
	 * @author hexw
	 */
	int copyGoods(Long goodsId);

	
	/**
	 * 
	 * 获取文件里所有的商品编码    
	 * @return void    
	 * Date:2017年12月15日 下午4:22:27 
	 * @author hexw
	 */
	void getFileGoodsCode();



	List<Goods> findYouLike(PageBean pageBean);

	/**
	 * 根据商品code查询商品 0.没查到 1.查到了
	 * @param code
	 * @return
	 */
	Integer findGoodsCode(String code);


	/**
	 * 修改商品供货商名称和品牌名称
	 *     
	 * @return void    
	 * Date:2018年1月17日 上午11:09:46 
	 * @author hexw
	 */
	void updateGoodsSupplierName();

}
  