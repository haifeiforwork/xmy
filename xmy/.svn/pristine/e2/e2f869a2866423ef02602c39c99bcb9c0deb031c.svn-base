package com.zfj.xmy.cms.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.ObjectUtils;

import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.order.persistence.cms.pojo.dto.OrderExcleDto;

public class GoodsExcel {
	
	public void excleGoods(List<Goods> goodsList,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=goodsList.xls ");
		HttpServletRequest request = WebUtil.getRequest();
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("商品名称");
        row.createCell(1).setCellValue("商品简称");
        row.createCell(2).setCellValue("商品全称");
        row.createCell(3).setCellValue("商品编号");
        row.createCell(4).setCellValue("品牌名称");
        row.createCell(5).setCellValue("供应商名称");
        row.createCell(6).setCellValue("供应商编码");
        row.createCell(7).setCellValue("计量单位名称");
        row.createCell(8).setCellValue("库存");
        row.createCell(9).setCellValue("数量备注");
        row.createCell(10).setCellValue("库存报警值");
        row.createCell(11).setCellValue("重量");
        row.createCell(12).setCellValue("成本价");
        row.createCell(13).setCellValue("销售价");
        row.createCell(14).setCellValue("市场价");
        row.createCell(15).setCellValue("手机端销售价格");
        row.createCell(16).setCellValue("会员价");
        row.createCell(17).setCellValue("采购市场");
        row.createCell(18).setCellValue("产地");
        row.createCell(19).setCellValue("是否上架");
        row.createCell(20).setCellValue("是否是非卖品");
        row.createCell(21).setCellValue("是否被搜索");
        row.createCell(22).setCellValue("是否支持全国配送");
        row.createCell(23).setCellValue("商品类型");
        row.createCell(24).setCellValue("商品类型名称");
        row.createCell(25).setCellValue("是否使用描述模板");
        row.createCell(26).setCellValue("pc详细介绍");
        row.createCell(27).setCellValue("手机详细介绍");
        row.createCell(28).setCellValue("是否回收商品");
        row.createCell(29).setCellValue("类别编码");
        row.createCell(30).setCellValue("类别名称");
        row.createCell(31).setCellValue("创建时间");
        row.createCell(32).setCellValue("上架时间");
        row.createCell(33).setCellValue("下架时间");
        row.createCell(34).setCellValue("商品规格");
        row.createCell(35).setCellValue("商品默认购买数量");
        row.createCell(36).setCellValue("修改时间");
        row.createCell(37).setCellValue("商品图片");
        row.createCell(38).setCellValue("是否支持上门提货");
        row.createCell(39).setCellValue("商品成交量");
        row.createCell(40).setCellValue("商品评论总量");
        row.createCell(41).setCellValue("积分");
        row.createCell(42).setCellValue("存储方式");
        row.createCell(43).setCellValue("包装");
        row.createCell(44).setCellValue("称编码");
        int i = 1;
        for (Goods goods : goodsList) {
			HSSFRow createRow = sheet.createRow(i);
			createRow.createCell(0).setCellValue(goods.getName());
			createRow.createCell(1).setCellValue(goods.getShortName());
			createRow.createCell(2).setCellValue(goods.getFullName());
			createRow.createCell(3).setCellValue(goods.getCode());
			createRow.createCell(4).setCellValue(goods.getBrandName());
			createRow.createCell(5).setCellValue(goods.getSupplierName());
			createRow.createCell(6).setCellValue(goods.getSupplierCode());
			createRow.createCell(7).setCellValue(goods.getUnitName());
			createRow.createCell(8).setCellValue(goods.getNum());
			createRow.createCell(9).setCellValue(goods.getRemarkNum());
			createRow.createCell(10).setCellValue(goods.getAlarmNum());
			createRow.createCell(11).setCellValue(goods.getWeight());
			createRow.createCell(12).setCellValue(goods.getCostPrice().doubleValue());
			createRow.createCell(13).setCellValue(goods.getPrice().doubleValue());
			createRow.createCell(14).setCellValue(goods.getMarketPrice().doubleValue());
			createRow.createCell(15).setCellValue(goods.getPhonePrice().doubleValue());
			createRow.createCell(16).setCellValue(goods.getVipPrice().doubleValue());
			createRow.createCell(17).setCellValue(goods.getMarket());
			createRow.createCell(18).setCellValue(goods.getArea());
			createRow.createCell(19).setCellValue(goods.getIsPutway());
			createRow.createCell(20).setCellValue(goods.getIsSearch());
			createRow.createCell(21).setCellValue(goods.getIsDelivery());
			createRow.createCell(22).setCellValue(goods.getDes());
			createRow.createCell(23).setCellValue(goods.getType());
			createRow.createCell(24).setCellValue(goods.getTypeName());
			//createRow.createCell(25).setCellValue(goods.getIsDesmodel());
			createRow.createCell(26).setCellValue("");//描述
			createRow.createCell(27).setCellValue("");//描述
			createRow.createCell(28).setCellValue(goods.getIsRecycle());
			createRow.createCell(29).setCellValue(goods.getCategoryCode());
			createRow.createCell(30).setCellValue(goods.getCategoryName());
			createRow.createCell(31).setCellValue(DateUtil.format(goods.getCreateTime(), "yyyy-MM-dd"));
			createRow.createCell(32).setCellValue(DateUtil.format(goods.getPutwayTime(), "yyyy-MM-dd"));
			if(!ObjectUtils.isEmpty(goods.getOutwayTime())){
				createRow.createCell(33).setCellValue(DateUtil.format(goods.getOutwayTime(), "yyyy-MM-dd"));
			}
			createRow.createCell(34).setCellValue(goods.getStandard());
			createRow.createCell(35).setCellValue(goods.getDefaultBuynum());
			//createRow.createCell(36).setCellValue(goods.getUpdateTime());
			createRow.createCell(37).setCellValue(goods.getImgPath());
			createRow.createCell(38).setCellValue(goods.getIsUd());
			//createRow.createCell(39).setCellValue(goods.getSumDeal());
			//createRow.createCell(40).setCellValue(goods.getSumComment());
			createRow.createCell(41).setCellValue(goods.getPoints());
			createRow.createCell(42).setCellValue(goods.getStorageMethod());
			createRow.createCell(43).setCellValue(goods.getPackMethod());
			createRow.createCell(44).setCellValue(goods.getWeighCode());
			i++;
		}
        // 第六步，将文件存到指定位置  
        try  
        {  
        	//写入文件本地地址url
        	ServletOutputStream fout = response.getOutputStream();
    		wb.write(fout);  
    		fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	
}
