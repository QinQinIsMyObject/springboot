package com.zpark.springboot03.service;

import com.github.pagehelper.PageInfo;
import com.zpark.springboot03.entity.Goods;
import com.zpark.springboot03.util.R;

/**
 * @author Celery
 */
public interface GoodsService {

    R goodsList();

    R buyGoods(Goods goods);

    PageInfo<Goods> goodsListInPage(Integer pageNum);

}
