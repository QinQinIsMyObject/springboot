package com.zpark.springboot03.service;

import com.zpark.springboot03.entity.Goods;
import com.zpark.springboot03.util.R;

/**
 * @author Celery
 */
public interface GoodsService {
    R goodsList();

    R buyGoods(Goods goods);
}
