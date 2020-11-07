package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.GoodsRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRecordMapper {

    Integer insertRecord(GoodsRecord goodsRecord);

    Integer updateRecord(GoodsRecord goodsRecord);

    GoodsRecord selectRecord(@Param("gId") Integer gId);

}
