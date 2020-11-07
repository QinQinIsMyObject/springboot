package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.UserRecord;
import com.zpark.circle_shop.mapper.UserRecordMapper;
import com.zpark.circle_shop.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRecordServiceImpl implements UserRecordService {

    @Autowired
    private UserRecordMapper userRecordMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void recordVisit() {
        Integer userVisits = (Integer) redisTemplate.opsForValue().get("userVisits");
        if (userVisits != null) {
            UserRecord userRecord = userRecordMapper.selectRecord();
            if (userRecord == null) {
                userRecord = new UserRecord().setVisits(userVisits).setRegists(0);
                userRecordMapper.insertRecord(userRecord);
            } else {
                userRecord.setVisits(userVisits);
                userRecordMapper.updateVisitRecord(userRecord);
            }
            userRecordMapper.updateVisitRecord(userRecord);
            redisTemplate.delete("userVisits");
        }
    }

    @Override
    public void recordRegister(Integer row) {
        //��¼ҵ�� ���Բ�ʹ��redis
        if (row == 1) {//˵�����ע��
            UserRecord userRecord = userRecordMapper.selectRecord();
            if (userRecord == null) {
                userRecord = new UserRecord().setRegists(1).setVisits(0);
                userRecordMapper.insertRecord(userRecord);
            } else {
                userRecordMapper.updateRegisterRecord();
            }
        }
    }

}
