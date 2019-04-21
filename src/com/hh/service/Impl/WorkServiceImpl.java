package com.hh.service.Impl;
import java.util.List;
import com.hh.dao.WorkDao;
import com.hh.entity.Work;
import com.hh.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WorkServiceImpl implements WorkService{
    @Autowired
    private WorkDao workDao;
    @Override
    public long getWorkRowCount(){
        return workDao.getWorkRowCount();
    }
    @Override
    public List<Work> selectWork(){
        return workDao.selectWork();
    }
    @Override
    public Work selectWorkByObj(Work obj){
        return workDao.selectWorkByObj(obj);
    }
    @Override
    public Work selectWorkById(Integer id){
        return workDao.selectWorkById(id);
    }
    @Override
    public int insertWork(Work value){
        return workDao.insertWork(value);
    }
    @Override
    public int insertNonEmptyWork(Work value){
        return workDao.insertNonEmptyWork(value);
    }
    @Override
    public int deleteWorkById(Integer id){
        return workDao.deleteWorkById(id);
    }
    @Override
    public int updateWorkById(Work enti){
        return workDao.updateWorkById(enti);
    }
    @Override
    public int updateNonEmptyWorkById(Work enti){
        return workDao.updateNonEmptyWorkById(enti);
    }

    public WorkDao getWorkDao() {
        return this.workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }
	@Override
	public List<Work> selectByuserId(Integer userid) {
		
		return workDao.selectoneWork(userid);
	}
	@Override
	public List<Work> selectAllWork() {
		
		return workDao.selectAllWork();
	}

}