package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.Goods;
import com.zpark.circle_shop.entity.GoodsDetail;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 通过商品id查询商品的全部信息
     *
     * @param goods
     * @return
     */
    Goods findGoodsById(Goods goods);

    /**
     * 通过关键词查询商品
     *
     * @param keywords
     * @return
     */
    List<Goods> findGoodsByKeywords(@Param("keywords") String keywords);

    /**
     * 更新商品的信息
     *
     * @param goodsDetail
     * @return
     */
    Integer updateGoodsDetail(GoodsDetail goodsDetail);

    /**
     * 通过主键查询商品细节信息
     *
     * @param goodsDetail
     * @return
     */
    GoodsDetail findGoodsDetailById(GoodsDetail goodsDetail);

}
