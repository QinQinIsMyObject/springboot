package com.zpark.circle_shop.service;

import com.zpark.circle_shop.entity.Goods;

/**
 * @author Celery
 */
public interface GoodsService {

    Object goodsList(Integer pageNum);

    /**
     * @param goods
     * @return
     */
    Object findGoodsById(Goods goods);

    Object findByKeywords(String keywords);
}
