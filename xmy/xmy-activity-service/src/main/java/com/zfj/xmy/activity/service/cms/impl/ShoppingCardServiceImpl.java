package com.zfj.xmy.activity.service.cms.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.cms.dao.ShoppingCardExMapper;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.ShoppingCardDto;
import com.zfj.xmy.activity.service.cms.ShoppingCardService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardRecordMapper;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService{
	@Autowired
	private ShoppingCardExMapper shoppingCardExMapper;
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private ShoppingCardRecordMapper recordMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 分页查询商品信息
	 */
	@Override
	public List<ShoppingCardDto> findAllShoppingCard(ReqData reqData, PageBean pageBean) {
		List<ShoppingCardDto> list = shoppingCardExMapper.findShoppingCard(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setTotalCount(shoppingCardExMapper.countTotalShoppingCard(ReqUtil.reqParameterToCriteriaParameter(reqData)));
		return list;
	}
	
	/**
	 * 根据订单ID查询订单的购物卡记录
	 */
	@Override
	public List<ShoppingCard> findShoppingCardByUserId(Long orderId) {
		ReqData reqData = new  ReqData();
		String cardId = "0";
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(orderId);
		reqData.putValue("user_id", selectByPrimaryKey.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCardRecord> byExample = recordMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (ShoppingCardRecord shoppingCardRecord : byExample) {
			cardId+=","+shoppingCardRecord.getShoppingCardId();
		}
		//reqData.putValue("user_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("id", cardId, SystemConstant.REQ_PARAMETER_IN);
		List<ShoppingCard> selectByExample = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
	/**
	 * 修改
	 */
	@Override
	public void updateShoppingCard(Long id, Integer type, BigDecimal money) {
		ShoppingCard oldCard = shoppingCardMapper.selectByPrimaryKey(id);
		if (type == SystemConstant.userSpendPoints.SPEND_TYPE_SAVE) {//存入余额
			oldCard.setBalance(oldCard.getBalance().add(money));//+ userSpendPoints.getMoneyPoint());
		} else {
			oldCard.setBalance(oldCard.getBalance().subtract(money));//- userSpendPoints.getMoneyPoint());
		}
		shoppingCardMapper.updateByPrimaryKey(oldCard);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void importShoppingCard(MultipartFile file){
		System.out.println(file);
		//保存卡号数据
		String contentType = file.getContentType();
		if (StringUtil.isEmpty(contentType)) {
			throw new BusinessException("请选择要导入的购物卡");
		}
		if (!contentType.contains("excel")) {
			throw new BusinessException("文件格式不允许");
		}
		
		//
		try {
			
			
			List<ShoppingCard> shoppingCards = new ArrayList<ShoppingCard>();
			//1.从工作表中获取数据
			HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet hssfSheet = workbook.getSheetAt(0);
			for(Iterator<Row> ite = hssfSheet.rowIterator();ite.hasNext();){ //表格行循环
				 HSSFRow row=(HSSFRow)ite.next(); 
				 short firstCellNum = row.getFirstCellNum();
				 short lastCellNum = row.getLastCellNum();
				 ShoppingCard shoppingCard = new ShoppingCard();
				 Boolean isSave = true; //是否保存
				 for (int cellnum = firstCellNum; cellnum < lastCellNum; cellnum++) {
					    HSSFCell cell = row.getCell(cellnum);
		                System.out.println(cell.getStringCellValue());
		                if (cellnum == 0) { //第一排 为卡号
							shoppingCard.setCardNum(cell.getStringCellValue());
						}
		                if (cellnum == 1) { //第二排 为卡密码
							shoppingCard.setCardPassword(cell.getStringCellValue());
						}
		                if (cellnum == 2) { //第三排 为 卡总额,如果格式不对则跳过该行，比如表头
							try {
								shoppingCard.setTotalValue(new BigDecimal(cell.getStringCellValue()));
								shoppingCard.setBalance(new BigDecimal(cell.getStringCellValue()));
							} catch (NumberFormatException e) {
								e.printStackTrace();
								isSave = false;
								continue;
							}
						}
				 }
				 //添加其余字段
				 shoppingCard.setName(shoppingCard.getTotalValue() + "元购物卡");
				 shoppingCard.setCreateTime(DateUtil.date());
				 if (isSave) {
					 shoppingCards.add(shoppingCard);
				 }
			}
			
			if (CollectionUtils.isEmpty(shoppingCards)) {
				throw new BusinessException("没有需要导入的卡数据");
			}
			
			//2.保存工作表
			System.out.println(shoppingCards);
			shoppingCardExMapper.insertBatch(shoppingCards);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("文件格式有误");
		}
		
	}

	@Override
	public List<ShoppingCard> getShoppingcCards(String cardNo, String pwd) {
		ReqData reqData = new ReqData();
		reqData.putValue("card_num", cardNo, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("card_password", pwd, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCard> selectByExample = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
	
}
