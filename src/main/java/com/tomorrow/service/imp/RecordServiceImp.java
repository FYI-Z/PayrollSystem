package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.RecordDao;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.Record;
import com.tomorrow.service.RecordService;
import com.tomorrow.util.StringUtil;
import com.tomorrow.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImp implements RecordService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RecordDao recordDao;

    @Override
    public boolean addRecord(Record record) {
        if(record.getUserid() == null){
            return false;
        }

        /*生成recordid*/
        String id = StringUtil.getUUID();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid" , record.getUserid());
        while(recordDao.selectById(id) != null){
            id = StringUtil.getUUID();
        }
        try {
            if(recordDao.selectOne(queryWrapper) != null){
                return false;
            }
        }catch (Exception e){
            System.out.println("过多结果集");
            return false;
        }

        record.setRecordTime(TimeUtil.getTime());
        record.setRecordid(id);
        int flag = recordDao.insert(record);
        return flag != 0;
    }

    @Override
    public boolean deleteRecord(String recordId) {
        int flag = recordDao.deleteById(recordId);
        return flag != 0;
    }

    @Override
    public List<Record> findAllRecord() {
        List<Record> recordList = new ArrayList<>();
        recordList = recordDao.selectList(null);
        return recordList;
    }

    @Override
    public boolean updateRecordByUserId(Record record) {
        if(userDao.selectById(record.getUserid()) == null){
            return false;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid",record.getUserid());
        Record re = recordDao.selectOne(queryWrapper);
        if(record.getReward() != 0.0 ) re.setReward(record.getReward());
        if(record.getPunishment() != 0.0 ) re.setPunishment(record.getPunishment());
        if(record.getStatus() != null ) re.setStatus(record.getStatus());
        if(record.getExaminer() != null ) re.setExaminer(record.getExaminer());
        re.setRecordTime(TimeUtil.getTime());

        int flag = recordDao.update(re,queryWrapper);
        return flag != 0;
    }
}
