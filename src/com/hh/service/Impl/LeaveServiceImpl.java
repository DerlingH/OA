package com.hh.service.Impl;
import java.util.List;
import com.hh.dao.LeaveDao;
import com.hh.entity.Leave;
import com.hh.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LeaveServiceImpl implements LeaveService{
    @Autowired
    private LeaveDao leaveDao;
    @Override
    public long getLeaveRowCount(){
        return leaveDao.getLeaveRowCount();
    }
    @Override
    public List<Leave> selectLeave(){
        return leaveDao.selectLeave();
    }
    @Override
    public Leave selectLeaveByObj(Leave obj){
        return leaveDao.selectLeaveByObj(obj);
    }
    @Override
    public Leave selectLeaveById(Integer id){
        return leaveDao.selectLeaveById(id);
    }
    @Override
    public int insertLeave(Leave value){
        return leaveDao.insertLeave(value);
    }
    @Override
    public int insertNonEmptyLeave(Leave value){
        return leaveDao.insertNonEmptyLeave(value);
    }
    @Override
    public int deleteLeaveById(Integer id){
        return leaveDao.deleteLeaveById(id);
    }
    @Override
    public int updateLeaveById(Leave enti){
        return leaveDao.updateLeaveById(enti);
    }
    @Override
    public int updateNonEmptyLeaveById(Leave enti){
        return leaveDao.updateNonEmptyLeaveById(enti);
    }

    public LeaveDao getLeaveDao() {
        return this.leaveDao;
    }

    public void setLeaveDao(LeaveDao leaveDao) {
        this.leaveDao = leaveDao;
    }
	@Override
	public List<Leave> selectLeaveByUserId(Integer id) {
		
		return leaveDao.selectLeaveByUserId(id);
	}
	@Override
	public List<Leave> selectAllLeave() {
		// TODO Auto-generated method stub
		return leaveDao.selectAllLeave();
	}

}