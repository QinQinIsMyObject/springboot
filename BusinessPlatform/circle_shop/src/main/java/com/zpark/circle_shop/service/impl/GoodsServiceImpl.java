package com.zpark.circle_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpark.circle_shop.entity.Goods;
import com.zpark.circle_shop.entity.GoodsDetail;
import com.zpark.circle_shop.mapper.GoodsMapper;
import com.zpark.circle_shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Celery
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Cacheable(value = "goodsList", key = "#pageNum")
    public PageInfo<Goods> goodsList(Integer pageNum) {
        PageHelper.startPage(pageNum, 12);
        List<Goods> goodsList = goodsMapper.goodsList();
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "goods", key = "#goods.gId")
    public List<GoodsDetail> findGoodsById(Goods goods) {
        Integer state = goodsMapper.findGoodsById(goods).getState();
        if (state == 2) {
            return new ArrayList<GoodsDetail>();
        }
        List<GoodsDetail> goodsDetails = goodsMapper.findGoodsDetailByGId(goods);
        return goodsDetails;
    }

    @Override
    public Object findByKeywords(String keywords, Integer pageNum) {

        PageHelper.startPage(pageNum, 12);

        Object redisGoodsKeywords = redisTemplate.opsForValue().get("findGoodsByKeywords::" + keywords);
        if (redisGoodsKeywords != null) {
            return redisGoodsKeywords;
        }

        PageInfo<Goods> goodsKeywords = new PageInfo<>(goodsMapper.findGoodsByKeywords(keywords));

        //计算商品搜索的热度，满足热度，则存redis
        Integer r = (Integer) redisTemplate.opsForValue().get("goodsKeywords::" + keywords);
        if (r == null) {
            redisTemplate.opsForValue().set("goodsKeywords::" + keywords, 1, 10, TimeUnit.SECONDS);
        } else {
            if (r >= 29) {//30是热度临界值，10秒查30次
                //存redis
                redisTemplate.opsForValue().set("findGoodsByKeywords::" + keywords, goodsKeywords, 10, TimeUnit.MINUTES);
            }
            redisTemplate.opsForValue().increment("goodsKeywords::" + keywords);
        }

        return goodsKeywords;
    }

}
