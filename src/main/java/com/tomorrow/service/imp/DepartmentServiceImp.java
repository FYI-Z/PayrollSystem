package com.tomorrow.service.imp;


import com.tomorrow.dao.DepartmentDao;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public List<Department> findAll(int count) {
        return departmentDao.findAll(count);
    }

    @Override
    public ReturnResult del(String departmentid) {
        if( departmentDao.del(departmentid)>=0){
            return ResultUtil.success(departmentid,Constant.RESCODE_SUCCESS,1);
        }else{
           return ResultUtil.error(Constant.RESCODE_DELETEERROR,"删除失败");
        }
    }

    @Override
    public List<Department> findMsg(String name,String operator) {
        return departmentDao.findMsg(name,operator);
    }

    @Override
    public List<Department> findOrMsg(String name, String operator) {
        return departmentDao.findOrMsg(name,operator);
    }

    /**
     *
     * @param name
     * @param operator
     * @return
     */
    @Override
    public List<Department> findResult(String name,String operator){
        if(name==""||operator==""){
            return findOrMsg(name,operator);
        }else{
            return findMsg(name,operator);
        }
    }
}