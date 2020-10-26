package com.zpark.circle_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpark.circle_shop.entity.Goods;
import com.zpark.circle_shop.mapper.GoodsMapper;
import com.zpark.circle_shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Celery
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    @Cacheable(value = "goodsList", key = "#pageNum")
    public PageInfo<Goods> goodsList(Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Goods> goodsList = goodsMapper.goodsList();
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        return pageInfo;
    }

}
