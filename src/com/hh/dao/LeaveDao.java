package com.hh.dao;
import com.hh.entity.Leave;
import java.util.List;
public interface LeaveDao{
	/**
	 * 获得Leave数据的总行数
	 * @return
	 */
    long getLeaveRowCount();
	/**
	 * 获得Leave数据集合
	 * @return
	 */
    List<Leave> selectLeave();
	/**
	 * 获得一个Leave对象,以参数Leave对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    
    //根据用户的id查询
    List<Leave> selectLeaveByUserId(Integer id);
    
    //获得三表查询到的所有请假信息
    List<Leave> selectAllLeave();
    
    Leave selectLeaveByObj(Leave obj);
	/**
	 * 通过Leave的id获得Leave对象
	 * @param id
	 * @return
	 */
    Leave selectLeaveById(Integer id);
	/**
	 * 插入Leave到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertLeave(Leave value);
	/**
	 * 插入Leave中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyLeave(Leave value);
	/**
	 * 通过Leave的id删除Leave
	 * @param id
	 * @return
	 */
    int deleteLeaveById(Integer id);
	/**
	 * 通过Leave的id更新Leave中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateLeaveById(Leave enti);
	/**
	 * 通过Leave的id更新Leave中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyLeaveById(Leave enti);
}