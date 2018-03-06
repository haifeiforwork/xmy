package com.zfj.xmy.cms.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.ObjectUtils;

import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.order.persistence.cms.pojo.dto.OrderExcleDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.LableDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderGoodsDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.ShoppingDto;

public class PoiUtil {
	
	/**
	 * 支付方式-微信网页端
	 */
	public static final int TRADETYPE_WX_JSAPI = 0;
	/**
	 * 支付方式-微信 APP
	 */
	public static final int TRADETYPE_WX_APP = 1;
	/**
	 * 支付方式-支付宝 APP
	 */
	public static final int TRADETYPE_ALIPAY_APP = 2;
	
	/**
	 * 支付方式-银联-手机控件支付
	 */
	public static final int TRADETYPE_UNIONPAY_APP = 3;
	/**
	 * 支付方式-银联-手机WAP支付
	 */
	public static final int TRADETYPE_UNIONPAY_WAP = 4;
	/**
	 * 支付方式-银联-网关支付
	 */
	public static final int TRADETYPE_UNIONPAY_GATEWAY = 5;
	
	/**
	 * 支付方式-微信 H5 支付
	 */
	public static final int TRADETYPE_WX_MWEB = 6;
	/**
	 * 支付方式-微信 扫码 支付
	 */
	public static final int TRADETYPE_WX_SCANCODE = 7;
	
	/**
	 * 支付方式-支付宝 - 电脑网站  支付
	 */
	public static final int TRADETYPE_ALIPAY_PC_WEB = 8;

	/**
	 * 支付方式-支付宝 - 老接口-即时到账
	 */
	public static final int TRADETYPE_ALIPAY_OLD_DIRECTPAY = 9;
	
	/**
	 * 支付方式-支付宝 - WAP网站  支付
	 */
	public static final int TRADETYPE_ALIPAY_WAP = 10;
	
	/**
	 * 支付方式  map
	 */
	public static final  Map<Integer,String> getTradeType(){
		Map<Integer,String> result = new HashMap<Integer, String>();
		result.put(TRADETYPE_WX_JSAPI, "微信网页端");
		result.put(TRADETYPE_WX_APP, "微信 APP");
		result.put(TRADETYPE_ALIPAY_APP, "支付宝 APP");
		result.put(TRADETYPE_UNIONPAY_APP, "银联-手机控件支付");
		result.put(TRADETYPE_UNIONPAY_WAP, "银联-手机WAP支付");
		result.put(TRADETYPE_UNIONPAY_GATEWAY, "银联-网关支付");
		result.put(TRADETYPE_WX_MWEB, "微信 H5 支付");
		result.put(TRADETYPE_WX_SCANCODE, "微信 扫码 支付");
		result.put(TRADETYPE_ALIPAY_PC_WEB, "支付宝 - 电脑网站  支付");
		result.put(TRADETYPE_ALIPAY_OLD_DIRECTPAY, "支付宝 - 老接口-即时到账");
		result.put(TRADETYPE_ALIPAY_WAP, "支付宝 - WAP支付");
		return result;
	}
	
	//设置标题样式
	public static HSSFCellStyle getTileStyleCell(HSSFWorkbook wb){
		//标题样式
        HSSFCellStyle createCellStyle = wb.createCellStyle();//单元格样式
    	createCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//背景颜色
    	createCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充方式
    	createCellStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
    	createCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    	createCellStyle.setBorderBottom(BorderStyle.THIN);//下边框     
    	createCellStyle.setBorderLeft(BorderStyle.THIN);//左边框        
    	createCellStyle.setBorderRight(BorderStyle.THIN);//右边框        
    	createCellStyle.setBorderTop(BorderStyle.THIN);//上边框           
    	//字体
    	HSSFFont createFont = wb.createFont();
    	createFont.setFontHeight((short)200);
    	createFont.setBold(true);
    	createCellStyle.setFont(createFont);
		return createCellStyle;
	}
	//设置标题样式
	public static HSSFCellStyle getTopStyle(HSSFWorkbook wb){
		//标题样式
        HSSFCellStyle createCellStyle = wb.createCellStyle();//单元格样式
    	//createCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//背景颜色
    	//createCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充方式
    	createCellStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
    	createCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    	createCellStyle.setBorderBottom(BorderStyle.THIN);//下边框     
    	createCellStyle.setBorderLeft(BorderStyle.THIN);//左边框        
    	createCellStyle.setBorderRight(BorderStyle.THIN);//右边框        
    	createCellStyle.setBorderTop(BorderStyle.THIN);//上边框           
    	//字体
    	HSSFFont createFont = wb.createFont();
    	createFont.setFontHeight((short)200);
    	createFont.setBold(true);
    	createCellStyle.setFont(createFont);
		return createCellStyle;
	}
	/**
	 * 获取内容样式
	 * @param wb
	 * @return HSSFCellStyle
	 * @author ***
	 * @date 2017年12月3日 下午4:46:16
	 */
	public static HSSFCellStyle getConentStyle(HSSFWorkbook wb){
		//标题样式
        HSSFCellStyle createCellStyle = wb.createCellStyle();//单元格样式
    	//createCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//背景颜色
    	//createCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充方式
    	createCellStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
    	createCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    	createCellStyle.setBorderBottom(BorderStyle.THIN);//下边框     
    	createCellStyle.setBorderLeft(BorderStyle.THIN);//左边框        
    	createCellStyle.setBorderRight(BorderStyle.THIN);//右边框        
    	createCellStyle.setBorderTop(BorderStyle.THIN);//上边框           
    	//字体
    	HSSFFont createFont = wb.createFont();
    	createFont.setFontHeight((short)200);
    	createFont.setBold(true);
    	//createCellStyle.setFont(createFont);
		return createCellStyle;
	}
	
	/**
	 * 生成采购清单
	 * @param dto
	 * @param url void
	 * @author lij
	 * @throws IOException 
	 * @date 2017年10月16日 下午3:41:50
	 */
	public void createShoppingList(List<OrderGoodsDto> dto,String url,HttpServletResponse response,List<OrderDto> orderDto) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=shoping.xls ");
		HttpServletRequest request = WebUtil.getRequest();
		String path = request.getServletContext().getRealPath("")+"/model/shopping.xls";
		InputStream in = new FileInputStream(new File(path));
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook(in);  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.getSheet("采购清单"); 
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(9,3000);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式  
        style.setAlignment(HorizontalAlignment.RIGHT);
        HSSFCellStyle createCellStyle = getTileStyleCell(wb);
        HSSFCellStyle topStyle = getTopStyle(wb);
        HSSFCellStyle conentStyle = getConentStyle(wb);
    	row.setHeight((short)500);
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 10);
        HSSFCell cell = row.createCell((short) 0);  
        sheet.addMergedRegion(cra);
        cell.setCellValue("采购总表");
        cell.setCellStyle(topStyle);
        HSSFRow row1 = sheet.createRow((int) 1);
        cell=row1.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(1);
        cell.setCellValue("商品编号");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(2);
        cell.setCellValue("商品名称");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(3);
        cell.setCellValue("数量");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(4);
        cell.setCellValue("单位");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(5);
        cell.setCellValue("成本价");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(6);
        cell.setCellValue("销售价");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(7);
        cell.setCellValue("市场价");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(8);
        cell.setCellValue("供应商");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(9);
        cell.setCellValue("备注");
        cell.setCellStyle(createCellStyle);
        cell=row1.createCell(10);
        cell.setCellValue("称编码");
        cell.setCellStyle(createCellStyle);
        row1.setHeight((short)500);
        int j=2;
        int i=1;
        for (OrderGoodsDto goodsDto : dto) {
			row=sheet.createRow(j);
			row.setRowStyle(style1);
			row.setHeight((short)345);
			//放入数据
			/*row.createCell(0).setCellValue(student.getId());
			row.createCell(1).setCellValue(student.getName());
			row.createCell(2).setCellValue(student.getAge());*/
			row.createCell(0).setCellValue(i);
			row.getCell(0).setCellStyle(conentStyle);
			row.createCell(1).setCellValue(goodsDto.getCode());
			row.getCell(1).setCellStyle(conentStyle);
			row.createCell(2).setCellValue(goodsDto.getName());
			row.getCell(2).setCellStyle(conentStyle);
			row.createCell(3).setCellValue(goodsDto.getSumGoodsNum());//销售总数
			row.getCell(3).setCellStyle(conentStyle);
			row.createCell(4).setCellValue(goodsDto.getUnitName());
			row.getCell(4).setCellStyle(conentStyle);
			row.createCell(5).setCellValue(goodsDto.getCostPrice().toString());//成本价
			row.getCell(5).setCellStyle(conentStyle);
			row.createCell(6).setCellValue(goodsDto.getPrice().toString());//销售价
			row.getCell(6).setCellStyle(conentStyle);
			row.createCell(7).setCellValue(goodsDto.getMarketPrice().toString());//市场价
			row.getCell(7).setCellStyle(conentStyle);
			row.createCell(8).setCellValue(goodsDto.getSupplierName());//供货商名称
			row.getCell(8).setCellStyle(conentStyle);
			row.createCell(9).setCellValue(goodsDto.getActiveName());//备注
			row.getCell(9).setCellStyle(conentStyle);
			row.createCell(10).setCellValue(goodsDto.getWeighCode());//陈编码
			row.getCell(10).setCellStyle(conentStyle);
			row1=sheet.createRow(j+1);
			row1.setHeight((short)345);
			HSSFCell createCell = row1.createCell(0);
			//商品编码
			//createCell.setCellValue(student.getName());
			String str="";
			List<ShoppingDto> getsDto = goodsDto.getsDto();
			for (ShoppingDto shoppingDto : getsDto) {
				str+="编码:"+shoppingDto.getNo()+"数量:"+shoppingDto.getNum();
			}
			sheet.addMergedRegion(new CellRangeAddress(j+1, j+1, 0, 10));
			createCell.setCellValue(str);
			createCell.setCellStyle(conentStyle);
			createCell.getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
			conentStyle.setAlignment(HorizontalAlignment.LEFT);
			j+=2;
			i++;
		}
        CellRangeAddress orderTileText= new CellRangeAddress(j, j, 0, 10);
        HSSFRow orderTitle = sheet.createRow(j);
        sheet.addMergedRegion(orderTileText);
        orderTitle.createCell(0).setCellValue("订单商品详细");
        orderTitle.getCell(0).setCellStyle(style1);
        orderTitle.getCell(0).setCellStyle(topStyle);
        j++;
        HSSFRow orderTile = sheet.createRow(j);
        orderTile.setHeight((short)500);
        orderTile.createCell(0).setCellValue("订单编号");
        orderTile.getCell(0).setCellStyle(createCellStyle);
        orderTile.createCell(1).setCellValue("商品编码");
        orderTile.getCell(1).setCellStyle(createCellStyle);
        orderTile.createCell(2).setCellValue("商品名称");
        orderTile.getCell(2).setCellStyle(createCellStyle);
        orderTile.createCell(3).setCellValue("数量");
        orderTile.getCell(3).setCellStyle(createCellStyle);
        orderTile.createCell(4).setCellValue("单位");
        orderTile.getCell(4).setCellStyle(createCellStyle);
        orderTile.createCell(5).setCellValue("成本价");
        orderTile.getCell(5).setCellStyle(createCellStyle);
        orderTile.createCell(6).setCellValue("销售价");
        orderTile.getCell(6).setCellStyle(createCellStyle);
       /* orderTile.createCell(9).setCellValue("活动价");
        orderTile.getCell(9).setCellStyle(createCellStyle);*/
        orderTile.createCell(7).setCellValue("市场价");
        orderTile.getCell(7).setCellStyle(createCellStyle);
        orderTile.createCell(8).setCellValue("合计");
        orderTile.getCell(8).setCellStyle(createCellStyle);
        orderTile.createCell(9).setCellValue("供应商");
        orderTile.getCell(9).setCellStyle(createCellStyle);
        orderTile.createCell(10).setCellValue("称编码");
        orderTile.getCell(10).setCellStyle(createCellStyle);
        j++;
        for (OrderDto order : orderDto) {
        	int size=order.getGoodsList().size()-1;
    		if(size>=0){
    			int k = 0;
    			HSSFRow dataRow = sheet.createRow(j);
    			dataRow.setHeight((short)345);
    			dataRow.createCell(0).setCellValue(order.getNo());
    			dataRow.getCell(0).setCellStyle(conentStyle);
    			dataRow.createCell(9).setCellValue(order.getConsigneePhone());
    			dataRow.getCell(9).setCellStyle(conentStyle);
    			dataRow.createCell(10).setCellValue(order.getConsigneeName());
    			dataRow.getCell(10).setCellStyle(conentStyle);
    			List<OrderGoodsDto> goodsList = order.getGoodsList();
    			for (OrderGoodsDto goods : goodsList) {
    				HSSFRow goodsRow = sheet.getRow(j);
    				if(ObjectUtils.isEmpty(goodsRow)){
    					goodsRow = sheet.createRow(j);
    					goodsRow.setHeight((short)345);
    				}
    				goodsRow.createCell(1).setCellValue(goods.getCode());
    				goodsRow.getCell(1).setCellStyle(conentStyle);
    				goodsRow.createCell(2).setCellValue(goods.getName());
    				goodsRow.getCell(2).setCellStyle(conentStyle);
    				goodsRow.createCell(3).setCellValue(goods.getSumGoodsNum());
    				goodsRow.getCell(3).setCellStyle(conentStyle);
    				goodsRow.createCell(4).setCellValue(goods.getUnitName());
    				goodsRow.getCell(4).setCellStyle(conentStyle);
    				goodsRow.createCell(5).setCellValue(goods.getCostPrice().toString());
    				goodsRow.getCell(5).setCellStyle(conentStyle);
    				goodsRow.createCell(6).setCellValue(goods.getPrice().toString());
    				goodsRow.getCell(6).setCellStyle(conentStyle);
    				goodsRow.createCell(7).setCellValue(goods.getMarketPrice().toString());
    				goodsRow.getCell(7).setCellStyle(conentStyle);
    				goodsRow.createCell(8).setCellValue(order.getTotal().toString());
    				goodsRow.getCell(8).setCellStyle(conentStyle);
    				goodsRow.createCell(9).setCellValue(goods.getSupplierName());
    				goodsRow.getCell(9).setCellStyle(conentStyle);
    				goodsRow.createCell(10).setCellValue(goods.getWeighCode());
    				goodsRow.getCell(10).setCellStyle(conentStyle);
    				if(k>0){
    					goodsRow.createCell(9).setCellStyle(conentStyle);
    				}
    				j++;
    				k++;
    			}
    			int c= j;
    			if(size>0){
    				CellRangeAddress zior = new CellRangeAddress(c-(order.getGoodsList().size()), c-1, 0, 0);
    				CellRangeAddress zior1 = new CellRangeAddress(c-(order.getGoodsList().size()), c-1, 8, 8);
    				sheet.addMergedRegion(zior);
    				sheet.addMergedRegion(zior1);
    			}
    		}
        	//j++;
		}
        
        // 第六步，将文件存到指定位置  
        try  
        {  
        	//写入文件本地地址url
            //FileOutputStream fout = new FileOutputStream(url);  
            ServletOutputStream fout = response.getOutputStream();
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	/**
	 * @param type void
	 * @author lij
	 * @throws IOException 
	 * @date 2017年10月18日 上午10:10:02
	 */
	public void createShoppingCart(Integer type,String fileName,List<OrderGoodsDto> goodsDto,List<OrderDto> orderDto,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=order.xls "); 
		HttpServletRequest request = WebUtil.getRequest();
		String path = request.getServletContext().getRealPath("")+"/model/shopping.xls";
		InputStream in = new FileInputStream(new File(path));
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook(in);  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.getSheet("采购清单");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(9, 4000);
        sheet.setColumnWidth(10, 4000);
        HSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow rowTitle = sheet.createRow((int) 0);
        CellRangeAddress title = new CellRangeAddress(0, 0, 0, 10);
        if(type==1){
        	rowTitle.createCell(0).setCellValue("香满园----西部农产品电子商务平台  农配中心");
        }
        if(type==0){
        	rowTitle.createCell(0).setCellValue("香满园----西部农产品电子商务平台  水果市场");
        }
        HSSFCellStyle createCellStyle = getTileStyleCell(wb);
        HSSFCellStyle topStyle = getTopStyle(wb);
        HSSFCellStyle conentStyle = getConentStyle(wb);
        rowTitle.getCell(0).setCellStyle(titleStyle);
        sheet.addMergedRegion(title);
        HSSFRow fuTile = sheet.createRow(1);
        CellRangeAddress fuTiles= new CellRangeAddress(1, 1, 0, 10);
        fuTile.createCell(0).setCellValue("采购总表");
        fuTile.getCell(0).setCellStyle(topStyle);
        sheet.addMergedRegion(fuTiles);
        HSSFRow biaoti = sheet.createRow(2);
        biaoti.setHeight((short)500);
        biaoti.createCell(0).setCellValue("序号");
        biaoti.getCell(0).setCellStyle(createCellStyle);
        biaoti.createCell(1).setCellValue("商品编码");
        biaoti.getCell(1).setCellStyle(createCellStyle);
        biaoti.createCell(2).setCellValue("商品名称");
        biaoti.getCell(2).setCellStyle(createCellStyle);
        biaoti.createCell(3).setCellValue("数量");
        biaoti.getCell(3).setCellStyle(createCellStyle);
        biaoti.createCell(4).setCellValue("单位");
        biaoti.getCell(4).setCellStyle(createCellStyle);
        biaoti.createCell(5).setCellValue("成本价");
        biaoti.getCell(5).setCellStyle(createCellStyle);
        biaoti.createCell(6).setCellValue("销售价");
        biaoti.getCell(6).setCellStyle(createCellStyle);
        biaoti.createCell(7).setCellValue("市场价");
        biaoti.getCell(7).setCellStyle(createCellStyle);
        biaoti.createCell(8).setCellValue("供应商");
        biaoti.getCell(8).setCellStyle(createCellStyle);
        biaoti.createCell(9).setCellValue("备注");
        biaoti.getCell(9).setCellStyle(createCellStyle);
        biaoti.createCell(10).setCellValue("称编码");
        biaoti.getCell(10).setCellStyle(createCellStyle);
        //遍历数据
        int j=3;
        /*for(int i=3;i<=13;i++){
        	HSSFRow dataRow = sheet.createRow(i);
        	dataRow.createCell(0).setCellValue("1");
        	dataRow.createCell(1).setCellValue("2");
        	j++;
        }*/
        int i=0;//表示序号
        for (OrderGoodsDto goods : goodsDto) {
        	HSSFRow dataRow = sheet.createRow(j);
        	dataRow.setHeight((short)345);
        	dataRow.createCell(0).setCellValue(i);
        	dataRow.getCell(0).setCellStyle(conentStyle);
        	dataRow.createCell(1).setCellValue(goods.getCode());
        	dataRow.getCell(1).setCellStyle(conentStyle);
        	dataRow.createCell(2).setCellValue(goods.getName());
        	dataRow.getCell(2).setCellStyle(conentStyle);
        	dataRow.createCell(3).setCellValue(goods.getSumGoodsNum());
        	dataRow.getCell(3).setCellStyle(conentStyle);
        	dataRow.createCell(4).setCellValue(goods.getUnitName());
        	dataRow.getCell(4).setCellStyle(conentStyle);
        	dataRow.createCell(5).setCellValue(goods.getCostPrice().toString());
        	dataRow.getCell(5).setCellStyle(conentStyle);
        	dataRow.createCell(6).setCellValue(goods.getPrice().toString());
        	dataRow.getCell(6).setCellStyle(conentStyle);
        	dataRow.createCell(7).setCellValue(goods.getMarketPrice().toString());
        	dataRow.getCell(7).setCellStyle(conentStyle);
        	dataRow.createCell(8).setCellValue(goods.getSupplierName());
        	dataRow.getCell(8).setCellStyle(conentStyle);
        	dataRow.createCell(9).setCellValue(goods.getActiveName());
        	dataRow.getCell(9).setCellStyle(conentStyle);
        	dataRow.createCell(10).setCellValue(goods.getWeighCode());
        	dataRow.getCell(10).setCellStyle(conentStyle);
        	conentStyle.setAlignment(HorizontalAlignment.LEFT);
        	j++;
        	i++;
		}
        CellRangeAddress orderTileText= new CellRangeAddress(j, j, 0, 10);
        HSSFRow orderTitle = sheet.createRow(j);
        sheet.addMergedRegion(orderTileText);
        orderTitle.createCell(0).setCellValue("订单商品详细");
        orderTitle.getCell(0).setCellStyle(topStyle);
        j++;
        HSSFRow orderTile = sheet.createRow(j);
        orderTile.setHeight((short)500);
        orderTile.createCell(0).setCellValue("订单编号");
        orderTile.getCell(0).setCellStyle(createCellStyle);
        orderTile.createCell(1).setCellValue("商品编码");
        orderTile.getCell(1).setCellStyle(createCellStyle);
        orderTile.createCell(2).setCellValue("商品名称");
        orderTile.getCell(2).setCellStyle(createCellStyle);
        orderTile.createCell(3).setCellValue("数量");
        orderTile.getCell(3).setCellStyle(createCellStyle);
        orderTile.createCell(4).setCellValue("单位");
        orderTile.getCell(4).setCellStyle(createCellStyle);
        orderTile.createCell(5).setCellValue("成本价");
        orderTile.getCell(5).setCellStyle(createCellStyle);
        orderTile.createCell(6).setCellValue("销售价");
        orderTile.getCell(6).setCellStyle(createCellStyle);
        orderTile.createCell(7).setCellValue("市场价");
        orderTile.getCell(7).setCellStyle(createCellStyle);
        orderTile.createCell(8).setCellValue("供应商");
        orderTile.getCell(8).setCellStyle(createCellStyle);
        orderTile.createCell(9).setCellValue("客户电话");
        orderTile.getCell(9).setCellStyle(createCellStyle);
        orderTile.createCell(10).setCellValue("客户名称");
        orderTile.getCell(10).setCellStyle(createCellStyle);
        j++;
        for (OrderDto order : orderDto) {
        	int size=order.getGoodsList().size()-1;
    		if(size>=0){
    			int k =0;
    			HSSFRow dataRow = sheet.createRow(j);
    			dataRow.setHeight((short)345);
    			dataRow.createCell(0).setCellValue(order.getNo());
    			dataRow.getCell(0).setCellStyle(conentStyle);
    			dataRow.createCell(9).setCellValue(order.getConsigneePhone());
    			dataRow.getCell(9).setCellStyle(conentStyle);
    			dataRow.createCell(10).setCellValue(order.getConsigneeName());
    			dataRow.getCell(10).setCellStyle(conentStyle);
    			List<OrderGoodsDto> goodsList = order.getGoodsList();
    			for (OrderGoodsDto goods : goodsList) {
    				HSSFRow goodsRow = sheet.getRow(j);
    				if(ObjectUtils.isEmpty(goodsRow)){
    					goodsRow = sheet.createRow(j);
    					goodsRow.setHeight((short)345);
    				}
    				goodsRow.createCell(1).setCellValue(goods.getCode());
    				goodsRow.getCell(1).setCellStyle(conentStyle);
    				goodsRow.createCell(2).setCellValue(goods.getName());
    				goodsRow.getCell(2).setCellStyle(conentStyle);
    				goodsRow.createCell(3).setCellValue(goods.getSumGoodsNum());
    				goodsRow.getCell(3).setCellStyle(conentStyle);
    				goodsRow.createCell(4).setCellValue(goods.getUnitName());
    				goodsRow.getCell(4).setCellStyle(conentStyle);
    				goodsRow.createCell(5).setCellValue(goods.getCostPrice().toString());
    				goodsRow.getCell(5).setCellStyle(conentStyle);
    				goodsRow.createCell(6).setCellValue(goods.getPrice().toString());
    				goodsRow.getCell(6).setCellStyle(conentStyle);
    				goodsRow.createCell(7).setCellValue(goods.getMarketPrice().toString());
    				goodsRow.getCell(7).setCellStyle(conentStyle);
    				goodsRow.createCell(8).setCellValue(goods.getSupplierName());
    				goodsRow.getCell(8).setCellStyle(conentStyle);
    				if(k>0){
    					goodsRow.createCell(9).setCellStyle(conentStyle);
    					goodsRow.createCell(10).setCellStyle(conentStyle);
    				}
    				j++;
    				k++;
    			}
    			int c= j;
    			if(size>0){
    				CellRangeAddress zior = new CellRangeAddress(c-(order.getGoodsList().size()), c-1, 0, 0);
    				CellRangeAddress zior1 = new CellRangeAddress(c-(order.getGoodsList().size()), c-1, 9, 9);
    				CellRangeAddress zior2 = new CellRangeAddress(c-(order.getGoodsList().size()), c-1, 10, 10);
    				sheet.addMergedRegion(zior);
    				sheet.addMergedRegion(zior1);
    				sheet.addMergedRegion(zior2);
    			}
    		}
        	//j++;
		}
       /* for(int k=j;k<=j+6;k++){
        	HSSFRow dataRow = sheet.createRow(k);
        	dataRow.createCell(0).setCellValue("1");
        	dataRow.createCell(1).setCellValue("2");
        	k+=2;
        	CellRangeAddress zior = new CellRangeAddress(k-2, k, 0, 0);
        	CellRangeAddress zior1 = new CellRangeAddress(k-2, k, 12, 12);
        	CellRangeAddress zior2 = new CellRangeAddress(k-2, k, 13, 13);
        	sheet.addMergedRegion(zior);
        	sheet.addMergedRegion(zior1);
        	sheet.addMergedRegion(zior2);
        }*/
        try {
			/*List<Student> student = getStudent();
			for (Student stu : student) {
				HSSFRow dataRow = sheet.createRow(j);
	        	dataRow.createCell(0).setCellValue(stu.getName());
	        	dataRow.createCell(1).setCellValue(stu.getName());
	        	j+=(stu.getAge()-1);
	        	int c= j;
	        	CellRangeAddress zior = new CellRangeAddress(c-(stu.getAge()-1), c, 0, 0);
	        	CellRangeAddress zior1 = new CellRangeAddress(c-(stu.getAge()-1), c, 12, 12);
	        	CellRangeAddress zior2 = new CellRangeAddress(c-(stu.getAge()-1), c, 13, 13);
	        	sheet.addMergedRegion(zior);
	        	sheet.addMergedRegion(zior1);
	        	sheet.addMergedRegion(zior2);
	        	j++;
			}*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try  
        {  
            ServletOutputStream fout = response.getOutputStream();
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	/**
	 * 导出标签
	 *  void
	 * @author lij
	 * @throws Exception 
	 * @date 2017年10月18日 下午3:49:54
	 */
	public void exlLable(List<LableDto> lableDto,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=lable.xls ");
		HttpServletRequest request = WebUtil.getRequest();
		String path = request.getServletContext().getRealPath("")+"/model/sss.xls";
		InputStream in = new FileInputStream(new File(path));
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook(in);  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.getSheetAt(0);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        int i=0;
        int h=0;
        for (LableDto lable : lableDto) {
			String str = lable.getName()+"\n"+lable.getCode()+"\n"+lable.getNo();
			int j=lable.getNum();
			for(int k=0;k<j;k++){
				HSSFRow dataRow = sheet.getRow(i);
				if(ObjectUtils.isEmpty(dataRow.getCell(h))){
					dataRow.createCell(h).setCellValue(str);
				}else{
					dataRow.getCell(h).setCellValue(str);
				}
				h++;
				//每4个换行
				if(h==4){
					i++;
					h=0;
				}
			}
		}
        // 第六步，将文件存到指定位置  
        try  
        {  
        	//写入文件本地地址url
            //FileOutputStream fout = new FileOutputStream(url);  
            ServletOutputStream fout = response.getOutputStream();
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 导出白标签
	 *  void
	 * @author lij
	 * @throws Exception 
	 * @date 2017年10月18日 下午3:49:54
	 */
	public void exlWhriteLable(List<Order> orderList,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=WhriteLable.xls ");
		HttpServletRequest request = WebUtil.getRequest();
		String path = request.getServletContext().getRealPath("")+"/model/whirte.xls";
		InputStream in = new FileInputStream(new File(path));
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook(in);  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.getSheetAt(0);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        int i = 0;
        int j = 0;
    	for (Order order : orderList) {
			HSSFRow row = sheet.getRow(i);
			row.getCell(j).setCellValue(order.getNo());
			HSSFRow row2 = sheet.getRow(i+1);
			row2.getCell(j).setCellValue("          "+order.getConsigneeName()+"      "+order.getConsigneePhone());
			HSSFRow row3 = sheet.getRow(i+2);
			row3.getCell(j).setCellValue(order.getConsigneeAddress());
//			HSSFRow row4 = sheet.getRow(i+3);
//			row4.getCell(j).setCellValue(order.getBusinessRemark());
			if(j==0){
				j=1;
			}else{
				j=0;i+=3;
			}
		}
        /*for (LableDto lable : lableDto) {
			String str = lable.getName()+"\n"+lable.getCode()+"\n"+lable.getNo();
        	HSSFRow dataRow = sheet.getRow(i);
			int j=lable.getNum();
			for(int k=0;k<j;k++){
				if(ObjectUtils.isEmpty(dataRow.getCell(k))){
					dataRow.createCell(k).setCellValue(str);
				}else{
					dataRow.getCell(k).setCellValue(str);
				}
				//dataRow.getCell(k).setCellStyle(style);
			}
        	i++;
		}*/
        // 第六步，将文件存到指定位置  
        try  
        {  
        	//写入文件本地地址url
            //FileOutputStream fout = new FileOutputStream(url);  
            ServletOutputStream fout = response.getOutputStream();
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	/**
	 * 导出客户清单
	 * @param map
	 * @param response void
	 * @author lij
	 * @throws Exception 
	 * @date 2017年11月13日 上午9:46:14
	 */
	public void exlWithUserList(Map<String, Object> map,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = WebUtil.getRequest();
		String path = request.getServletContext().getRealPath("")+"/model/223user.xls";
		InputStream in = new FileInputStream(new File(path));
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook(in);
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.getSheetAt(0);
        
        //获取是否是APP第一单
        Boolean app = (Boolean) map.get("isAPPfirst");
        //1.写入订单信息
        //1.1头部
        Order order = (Order) map.get("order");
        response.setHeader("Content-Disposition", "attachment; filename="+order.getNo()+".xls ");
        if(order.getOrderSource()==1){//Pc
        	 sheet.getRow(2).getCell(1).setCellValue(order.getConsigneeName()+" PC");
        }
        if(order.getOrderSource()==2){//app
        	sheet.getRow(2).getCell(1).setCellValue(order.getConsigneeName()+" APP");
        }
        if(order.getOrderSource() ==3){//wap
        	sheet.getRow(2).getCell(1).setCellValue(order.getConsigneeName()+" WAP");
        }
        sheet.getRow(1).getCell(1).setCellValue(order.getNo());
        sheet.getRow(1).getCell(4).setCellValue(DateUtil.format(order.getPayTime(), "yyyy-MM-dd"));
        //sheet.getRow(2).getCell(1).setCellValue(order.getConsigneeName());
        sheet.getRow(2).getCell(4).setCellValue(order.getConsigneePhone());
        sheet.getRow(3).getCell(1).setCellValue(order.getConsigneeAddress());
        if(app){
        	 sheet.getRow(16).getCell(0).setCellValue("赠品");
        }
        //1.2中间金额
        sheet.getRow(15).getCell(1).setCellValue(order.getTotal().toString());
        //1.3底部时间
        String format = "";
        if(ObjectUtils.isEmpty(order.getParsetTime())){
        	format = "用户未指定";
        }else{
        	format = DateUtil.format(order.getParsetTime(), "yyyy-MM-dd");
        }
        sheet.getRow(21).getCell(2).setCellValue(order.getNo()+"   "+order.getConsigneeName()+"      "+order.getConsigneePhone());
        sheet.getRow(22).getCell(2).setCellValue(order.getConsigneeAddress());//待定
        sheet.getRow(25).getCell(2).setCellValue(order.getTotal().toString());
        sheet.getRow(18).getCell(2).setCellValue(DateUtil.format(order.getPayTime(), "yyyy-MM-dd"));
        sheet.getRow(24).getCell(2).setCellValue(DateUtil.format(DateUtil.offsetDay(order.getExtendTime(), 1), "yyyy-MM-dd"));//扩展时间向后偏移一天
        //具体支付方式
        String str = "";
        //购物卡实付
        BigDecimal cardPay = order.getPay().subtract(order.getDiscountPrice());
        if(cardPay.compareTo(new BigDecimal("0"))==1){//购物卡支付不为0
        	str +="购物卡："+cardPay.toString();
        }
        if(order.getDiscountPrice().compareTo(new BigDecimal("0"))==1){//优惠卷支付不为0
        	str +="优惠卷："+order.getDiscountPrice().toString();
        }
        if(order.getActual().compareTo(new BigDecimal("0"))==1){//第三方不为0
        	if(order.getPayType()!=5){
        		Map<Integer, String> tradeType = getTradeType();
        		String string = tradeType.get(order.getThirdPartyPayType());
        		str +=string+"："+order.getActual().toString();
        	}
        }
        sheet.getRow(17).getCell(5).setCellValue(str);
        sheet.getRow(19).getCell(2).setCellValue(order.getTotal().toString());
        if(ObjectUtils.isEmpty(order.getPay())){
        	sheet.getRow(15).getCell(3).setCellValue("0.0");
        	sheet.getRow(19).getCell(3).setCellValue("0.0");
        	sheet.getRow(25).getCell(3).setCellValue("0.0");
        }else{
        	if(order.getShipStatus() >= SystemConstant.ORDER.SHIP_STATUS_SUPPLY ){//已经支付了
        		 if(order.getPayType() == 5){//货到付款
        			 sheet.getRow(19).getCell(3).setCellValue(order.getPay().toString());
        			 sheet.getRow(15).getCell(3).setCellValue(order.getPay().toString());
        			 sheet.getRow(25).getCell(3).setCellValue(order.getPay().toString());
        		 }else{
        			 sheet.getRow(19).getCell(3).setCellValue(order.getTotal().toString());
        			 sheet.getRow(15).getCell(3).setCellValue(order.getTotal().toString());
        			 sheet.getRow(25).getCell(3).setCellValue(order.getTotal().toString());
        		 }
        	}
        }
        if(ObjectUtils.isEmpty(order.getActual())){
        	sheet.getRow(19).getCell(6).setCellValue("0.0");
        	sheet.getRow(25).getCell(6).setCellValue("0.0");
        	sheet.getRow(15).getCell(6).setCellValue("0.0");
        }else{
        	if(order.getShipStatus() >= SystemConstant.ORDER.SHIP_STATUS_SUPPLY ){//已经支付了
        		 if(order.getPayType() == 5){//货到付款
        			 sheet.getRow(19).getCell(6).setCellValue(order.getTotal().subtract(order.getPay()).toString());
        			 sheet.getRow(25).getCell(6).setCellValue(order.getTotal().subtract(order.getPay()).toString());
        			 sheet.getRow(15).getCell(6).setCellValue(order.getTotal().subtract(order.getPay()).toString());
        		 }else{
        			sheet.getRow(19).getCell(6).setCellValue("0.0");
        	        sheet.getRow(25).getCell(6).setCellValue("0.0");
        	        sheet.getRow(15).getCell(6).setCellValue("0.0");
        		 }
        	}
        }
        //sheet.getRow(21).getCell(1).setCellValue(order.getNo());
        //sheet.getRow(22).getCell(1).setCellValue(format);//待定
        
        sheet.getRow(17).getCell(2).setCellValue(order.getNo());
        wb.setSheetName(0, order.getNo()+"-1");
        //2.写入订单商品信息
        List<OrderGoods> goodsList = (List<OrderGoods>) map.get("goods");
        int size = goodsList.size();
        HSSFSheet sheet2 =null;
        HSSFSheet sheet3 =null; 
        HSSFSheet sheet4 =null;
        HSSFSheet sheet5 =null;
        if(size>10){
        	sheet2 = wb.cloneSheet(0);
        }
        if(size>20){
        	sheet3 = wb.cloneSheet(0);
        }
        if(size>30){
        	sheet4 = wb.cloneSheet(0);
        }
        if(size>40){
        	sheet5 = wb.cloneSheet(0);
        }
        //2.1订单商品大于10个的时候
        //goodsList.size()>10;
        int i = 5;
        for (OrderGoods orderGoods : goodsList) {
        	sheet.getRow(i).getCell(0).setCellValue(orderGoods.getNo().toString());
        	sheet.getRow(i).getCell(2).setCellValue(orderGoods.getName());
        	sheet.getRow(i).getCell(4).setCellValue(orderGoods.getNum());
        	if(ObjectUtils.isEmpty(orderGoods.getSumPoints()) || orderGoods.getSumPoints()==0){
        		sheet.getRow(i).getCell(5).setCellValue(orderGoods.getPrice().doubleValue());
        		sheet.getRow(i).getCell(7).setCellValue(orderGoods.getSumPrice().doubleValue());
        	}else{
        		sheet.getRow(i).getCell(5).setCellValue(orderGoods.getPoints().toString()+"积分");
        		sheet.getRow(i).getCell(7).setCellValue(orderGoods.getSumPoints().toString()+"积分");
        	}
        	i++;
        	if(i>14){
        		break;
        	}
		}
        if(size>10){
        	//sheet1显示
        	if(order.getOrderSource()==1){//Pc
            	 sheet.getRow(1).getCell(1).setCellValue(order.getNo()+"-1 PC");
            }
            if(order.getOrderSource()==2){//app
            	 sheet.getRow(1).getCell(1).setCellValue(order.getNo()+"-1 APP");
            }
            if(order.getOrderSource() ==3){//wap
            	 sheet.getRow(1).getCell(1).setCellValue(order.getNo()+"-1 WAP");
            }
			 sheet2.getRow(1).getCell(1).setCellValue(order.getNo()+"-2");
	       	wb.setSheetName(1, order.getNo()+"-2");
	       	int a=5;
	    	for(int q=10;q<goodsList.size();q++){
	    		sheet2.getRow(a).getCell(0).setCellValue(goodsList.get(q).getNo().toString());
	        	sheet2.getRow(a).getCell(2).setCellValue(goodsList.get(q).getName());
	        	sheet2.getRow(a).getCell(4).setCellValue(goodsList.get(q).getNum());
	        	if(ObjectUtils.isEmpty(goodsList.get(q).getSumPoints()) || goodsList.get(q).getSumPoints() == 0){
	        		sheet2.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPrice().doubleValue());
		        	sheet2.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPrice().doubleValue());
	        	}else{
	        		sheet2.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPoints().toString()+"积分");
		        	sheet2.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPoints().toString()+"积分");
	        	}
	        	a++;
	        	if(a>14){
	        		break;
	        	}
	    	}
       }
        if(size>20){
			 sheet3.getRow(1).getCell(1).setCellValue(order.getNo()+"-3");
	       	wb.setSheetName(2, order.getNo()+"-3");
	       	int a=5;
	    	for(int q=20;q<goodsList.size();q++){
	    		sheet3.getRow(a).getCell(0).setCellValue(goodsList.get(q).getNo().toString());
	        	sheet3.getRow(a).getCell(2).setCellValue(goodsList.get(q).getName());
	        	sheet3.getRow(a).getCell(4).setCellValue(goodsList.get(q).getNum());
	        	if(ObjectUtils.isEmpty(goodsList.get(q).getSumPoints()) || goodsList.get(q).getSumPoints() == 0){
	        		sheet3.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPrice().doubleValue());
		        	sheet3.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPrice().doubleValue());
	        	}else{
	        		sheet3.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPoints()+"积分");
		        	sheet3.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPoints()+"积分");
	        	}
	        	a++;
	        	if(a>14){
	        		break;
	        	}
	    	}
       }
        
        if(size>30){
			 sheet4.getRow(1).getCell(1).setCellValue(order.getNo()+"-4");
			 sheet4.getRow(23).getCell(2).setCellValue(order.getNo());
	       	wb.setSheetName(3, order.getNo()+"-4");
	       	int a=5;
	    	for(int q=30;q<goodsList.size();q++){
	    		sheet4.getRow(a).getCell(0).setCellValue(goodsList.get(q).getNo().toString());
	        	sheet4.getRow(a).getCell(2).setCellValue(goodsList.get(q).getName());
	        	sheet4.getRow(a).getCell(4).setCellValue(goodsList.get(q).getNum());
	        	if(ObjectUtils.isEmpty(goodsList.get(q).getSumPoints()) || goodsList.get(q).getSumPoints() == 0){
	        		sheet4.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPrice().doubleValue());
		        	sheet4.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPrice().doubleValue());
	        	}else{
	        		sheet4.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPoints()+"积分");
		        	sheet4.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPoints()+"积分");
	        	}
	        	a++;
	        	if(a>14){
	        		break;
	        	}
	    	}
      }
        if(size>40){
			 sheet5.getRow(1).getCell(1).setCellValue(order.getNo()+"-4");
	       	wb.setSheetName(3, order.getNo()+"-5");
	       	int a=5;
	    	for(int q=40;q<goodsList.size();q++){
	    		sheet5.getRow(a).getCell(0).setCellValue(goodsList.get(q).getNo().toString());
	        	sheet5.getRow(a).getCell(2).setCellValue(goodsList.get(q).getName());
	        	sheet5.getRow(a).getCell(4).setCellValue(goodsList.get(q).getNum());
	        	if(ObjectUtils.isEmpty(goodsList.get(q).getSumPoints()) || goodsList.get(q).getSumPoints() == 0){
	        		sheet5.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPrice().doubleValue());
		        	sheet5.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPrice().doubleValue());
	        	}else{
	        		sheet5.getRow(a).getCell(5).setCellValue(goodsList.get(q).getPoints()+"积分");
		        	sheet5.getRow(a).getCell(7).setCellValue(goodsList.get(q).getSumPoints()+"积分");
	        	}
	        	a++;
	        	if(a>14){
	        		break;
	        	}
	    	}
        }
        
        // 第六步，将文件存到指定位置  
        try  
        {  
        	//写入文件本地地址url
            //FileOutputStream fout = new FileOutputStream(url);  
        	ServletOutputStream fout = response.getOutputStream();
    		wb.write(fout);  
    		fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	
	public void exlAllOrder(List<OrderExcleDto> orderExcelDto,HttpServletResponse response,String beig,String end) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=orderSale.xls ");
		HttpServletRequest request = WebUtil.getRequest();
		String path = request.getServletContext().getRealPath("")+"/model/salemodel.xls";
		InputStream in = new FileInputStream(new File(path));
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook(in);
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.getRow(0).getCell(0).setCellValue(beig+"到"+end+"经营情况");
        int i = 3;//内容开始
        for (OrderExcleDto orderDto : orderExcelDto) {
			sheet.getRow(i).getCell(1).setCellValue(DateUtil.format(orderDto.getCaretDate(), "yyyy-MM-dd"));
			sheet.getRow(i).getCell(2).setCellValue(orderDto.getNo());
			sheet.getRow(i).getCell(3).setCellValue(orderDto.getTotal().toString());
			if(orderDto.getPayType()==SystemConstant.ORDER.PAY_TYPE_GIFTCARD){//购物卡支付
				sheet.getRow(i).getCell(4).setCellValue(orderDto.getPay().toString());
			}
			if(orderDto.getPayType()==SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY){//货到付款
				if(!ObjectUtils.isEmpty(orderDto.getPay()) && orderDto.getPay().compareTo(new BigDecimal("0.00"))!=0){
					sheet.getRow(i).getCell(4).setCellValue(orderDto.getPay().toString());
					sheet.getRow(i).getCell(5).setCellValue(orderDto.getTotal().subtract(orderDto.getPay()).toString());
					if(!ObjectUtils.isEmpty(orderDto.getCouponMoney())){
						sheet.getRow(i).getCell(5).setCellValue(orderDto.getTotal().subtract(orderDto.getPay()).subtract(orderDto.getCouponMoney()).toString());
					}
				}else{
					if(!ObjectUtils.isEmpty(orderDto.getCouponMoney())){
						sheet.getRow(i).getCell(5).setCellValue(orderDto.getTotal().subtract(orderDto.getCouponMoney()).toString());
					}else{
						sheet.getRow(i).getCell(5).setCellValue(orderDto.getTotal().toString());
					}
				}
			}
			if(!ObjectUtils.isEmpty(orderDto.getPayType())){
				if(orderDto.getPayType()==1){//支付宝
					sheet.getRow(i).getCell(7).setCellValue(orderDto.getAutal().toString());
				}
				if(orderDto.getPayType()==2){//微信
					sheet.getRow(i).getCell(10).setCellValue(orderDto.getAutal().toString());
				}
				if(orderDto.getPayType()==3){//银联
					sheet.getRow(i).getCell(6).setCellValue(orderDto.getAutal().toString());
				}
				if(orderDto.getPayType()==6){//混合支付
					if(!ObjectUtils.isEmpty(orderDto.getPay()) && orderDto.getPay().compareTo(new BigDecimal("0.00"))!=0){
						sheet.getRow(i).getCell(4).setCellValue(orderDto.getPay().toString());
					}
					if(!ObjectUtils.isEmpty(orderDto.getTrable())){
						if(orderDto.getTrable().equals(0) || orderDto.getTrable().equals(1) //微信
								|| orderDto.getTrable().equals(6) || orderDto.getTrable().equals(7)){
							sheet.getRow(i).getCell(10).setCellValue(orderDto.getAutal().toString());
						}
						if(orderDto.getTrable().equals(2) || orderDto.getTrable().equals(8) || orderDto.getTrable().equals(10)){//支付宝
							sheet.getRow(i).getCell(7).setCellValue(orderDto.getAutal().toString());
						}
						if(orderDto.getTrable().equals(3) || orderDto.getTrable().equals(4) || orderDto.getTrable().equals(5)){//银联
							sheet.getRow(i).getCell(6).setCellValue(orderDto.getAutal().toString());
						}
					}
				}
			}
			//优惠卷
			if(!ObjectUtils.isEmpty(orderDto.getCouponMoney()) && orderDto.getCouponMoney().compareTo(new BigDecimal("0.00"))!=0){
				sheet.getRow(i).getCell(8).setCellValue(orderDto.getCouponMoney().toString());
			}
			//备注
			sheet.getRow(i).getCell(11).setCellValue(orderDto.getRemark());
			/*if(!ObjectUtils.isEmpty(orderDto.getPay()) && orderDto.getPay().compareTo(new BigDecimal("0.00"))!=0){
				sheet.getRow(i).getCell(4).setCellValue(orderDto.getPay().toString());
			}*/
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
