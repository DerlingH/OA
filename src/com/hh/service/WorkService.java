package com.hh.service;
import java.util.List;
import com.hh.entity.Work;
public interface WorkService{
	/**
	 * 获得Work数据的总行数
	 * @return
	 */
    long getWorkRowCount();
    
    
    
	/**
	 * 获得Work数据集合
	 * @return
	 */
    List<Work> selectWork();
    
    
    //三表联合查询的工作
    List<Work> selectAllWork();	
    
    
    
	/**
	 * 获得一个Work对象,以参数Work对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Work selectWorkByObj(Work obj);
	/**
	 * 通过Work的id获得Work对象
	 * @param id
	 * @return
	 */
    Work selectWorkById(Integer id);
	/**
	 * 插入Work到数据库,包括null值
	 * @param value
	 * @return
	 */
    
    //通过userid查出该user的所有任务并排序
    List<Work> selectByuserId(Integer userid);
    
    
    
    int insertWork(Work value);
	/**
	 * 插入Work中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyWork(Work value);
	/**
	 * 通过Work的id删除Work
	 * @param id
	 * @return
	 */
    int deleteWorkById(Integer id);
	/**
	 * 通过Work的id更新Work中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateWorkById(Work enti);
	/**
	 * 通过Work的id更新Work中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyWorkById(Work enti);
}