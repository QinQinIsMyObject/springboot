package com.zpark.springboot03.mapper;

import com.zpark.springboot03.entity.Goods;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Celery
 */
@Repository
public interface GoodsMapper {
    /**
     * 查询商品
     *
     * @return
     */
    @Select("select * from goods")
    List<Goods> goodsList();
}
