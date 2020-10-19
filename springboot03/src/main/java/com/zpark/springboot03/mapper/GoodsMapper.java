package com.zpark.springboot03.mapper;

import com.zpark.springboot03.entity.Goods;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    /**
     * 购买商品
     *
     * @param goods
     * @return
     */
    @Update("update goods set gnum = gnum -1 where gid = #{gid} and gnum > 0")
    Integer buyGoods(Goods goods);
}
