package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.JiguangPushRecord;
import com.zfj.xmy.common.persistence.pojo.common.push.findJiguangPushRecordListResultDto;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JiguangPushRecordExMapper {

	/**
	 * 查询推送记录 
	 * @param parameter
	 * @param rowBounds
	 * @return
	 * @Description 
	 * @date 2018年2月3日  下午4:02:34
	 * @author wy
	 * 2018
	 * @return List<JiguangPushRecord>
	 */
	List<findJiguangPushRecordListResultDto> findJiguangPushRecordList(
			CriteriaParameter parameter, RowBounds rowBounds);

	/**
	 * 根据用户id查询推送记录
	 * @param userid
	 * @param rowBounds
	 * @return
	 * @Description 
	 * @date 2018年2月5日  上午11:37:54
	 * @author wy
	 * 2018
	 * @return List<JiguangPushRecord>
	 */
	List<JiguangPushRecord> findRecordsByUser(Long userid, RowBounds rowBounds);
	/**
	 * 根据用户id查询推送记录条数
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2018年2月5日  上午11:38:37
	 * @author wy
	 * 2018
	 * @return int
	 */
	int findRecordsByUserCount(Long userid);
}