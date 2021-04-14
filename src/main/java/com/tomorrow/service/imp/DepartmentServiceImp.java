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
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public ReturnResult del(String departmentid) {
        if( departmentDao.del(departmentid)>=0){
            return ResultUtil.success(departmentid,Constant.RESCODE_SUCCESS,1);
        }else{
           return ResultUtil.error(Constant.RESCODE_DELETEERROR,"删除失败");
        }
    }
}
