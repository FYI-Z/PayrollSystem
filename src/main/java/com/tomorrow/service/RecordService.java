package com.tomorrow.service;

import com.tomorrow.entity.Record;

import java.util.List;

public interface RecordService {
    public boolean addRecord(Record record);
    public boolean deleteRecord(String recordId);
    public List<Record> findAllRecord();
    public boolean updateRecordByUserId(Record record);
}
