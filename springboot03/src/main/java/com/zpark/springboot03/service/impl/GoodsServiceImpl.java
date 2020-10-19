package com.zpark.springboot03.service.impl;

import com.zpark.springboot03.entity.Goods;
import com.zpark.springboot03.mapper.GoodsMapper;
import com.zpark.springboot03.service.GoodsService;
import com.zpark.springboot03.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Celery
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public R goodsList() {
        //查看redis中有没有当前的数据
        List<Goods> redisGoodsList = (List<Goods>) redisTemplate.opsForValue().get("goodsList");

        if (redisGoodsList != null) {
            return R.ok("查询成功").addData("redisGoodsList", redisGoodsList);
        }

        //查询数据库
        List<Goods> goodsList = goodsMapper.goodsList();
        System.out.println("查询了一次数据库");

        //将数据存入redis中
        redisTemplate.opsForValue().set("goodsList", goodsList);

        return R.ok("查询成功！").addData("goodsList", goodsList);
    }
}
