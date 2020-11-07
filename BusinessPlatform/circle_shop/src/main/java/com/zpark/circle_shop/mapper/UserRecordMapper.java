package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.UserRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordMapper {

    Integer insertRecord(UserRecord userRecord);

    Integer updateVisitRecord(UserRecord userRecord);

    Integer updateRegisterRecord();

    UserRecord selectRecord();

}
