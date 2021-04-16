package com.tomorrow.service.imp;


import com.tomorrow.dao.DepartmentDao;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    /**
     *
     * @param name
     * @param operator
     * @return
     */
    @Override
    public List<Department> findResult(String name,String operator){
        if(name!=""&&operator!=""){
            return findMsg(name,operator);
        }else if(name==""&&operator!=""){
            return findMsgByOper(operator);
        }else{
            return findMsgByName(name);
        }
    }

    @Override
    public ReturnResult add(Department department) {
        int flag = 0;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today=simpleDateFormat.format(new Date());
        String departSn = "miku"+UUID.randomUUID().toString().replace("-", "").toUpperCase();
        department.setCreatetime(today);
        department.setDepartmentid(departSn);
        List<Department> repeat = departmentDao.findMsgAccByName(department.getName());
        if(repeat.size()==0){
             flag = departmentDao.add(department.getDepartmentid(),department.getName(),
                    department.getNumber(),department.getOperator(),
                    department.getCreatetime(),department.getRemark());
        }
        if(flag>0){
            return ResultUtil.success(department,Constant.RESCODE_SUCCESS,1);
        }else{
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"添加失败");
        }

    }

    @Override
    public ReturnResult update(String name,String Oname,String remark) {
        List<Department> repeat = departmentDao.findMsgAccByName(name);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today=simpleDateFormat.format(new Date());
        if(repeat.size()==0) {
            if (departmentDao.update(name, Oname,remark,today) > 0) {
                return ResultUtil.success(name, Constant.RESCODE_SUCCESS, 1);
            } else {
                return ResultUtil.error(Constant.RESCODE_MODIFYERROR, "更名失败，详细请联系管理员");
            }
        }else{
            return ResultUtil.error(Constant.RESCODE_MODIFYERROR,"部门重复");
        }
    }

    public List<Department> findMsg(String name,String operator) {
        return departmentDao.findMsg(name,operator);
    }
    public List<Department> findMsgByOper(String operator){
        return departmentDao.findMsgByOper(operator);
    }
    public List<Department> findMsgByName(String name){
        return departmentDao.findMsgByName(name);
    }



}
