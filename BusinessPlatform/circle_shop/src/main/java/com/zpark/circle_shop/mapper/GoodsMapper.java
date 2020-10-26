package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.Goods;
import com.zpark.circle_shop.entity.GoodsDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Celery
 */
@Repository
public interface GoodsMapper {

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Goods> goodsList();

    /**
     * 通过商品的id查询商品的细节信息
     *
     * @param goods
     * @return
     */
    List<GoodsDetail> findGoodsDetailByGId(Goods goods);

}
