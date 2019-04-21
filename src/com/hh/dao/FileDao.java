package com.hh.dao;
import com.hh.entity.File;
import java.util.List;
public interface FileDao{
	/**
	 * 获得File数据的总行数
	 * @return
	 */
    long getFileRowCount();
	/**
	 * 获得File数据集合
	 * @return
	 */
    List<File> selectFile();
	/**
	 * 获得一个File对象,以参数File对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    File selectFileByObj(File obj);
	/**
	 * 通过File的id获得File对象
	 * @param id
	 * @return
	 */
    File selectFileById(Integer id);
	/**
	 * 插入File到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertFile(File value);
	/**
	 * 插入File中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyFile(File value);
	/**
	 * 通过File的id删除File
	 * @param id
	 * @return
	 */
    int deleteFileById(Integer id);
	/**
	 * 通过File的id更新File中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateFileById(File enti);
	/**
	 * 通过File的id更新File中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyFileById(File enti);
}