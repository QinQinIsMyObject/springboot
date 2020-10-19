package com.zpark.springboot03.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpark.springboot03.entity.Goods;
import com.zpark.springboot03.mapper.GoodsMapper;
import com.zpark.springboot03.service.GoodsService;
import com.zpark.springboot03.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Celery
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

        //设置redis中数据的超时时间
        redisTemplate.expire("goodsList", 1, TimeUnit.HOURS);

        return R.ok("查询成功！").addData("goodsList", goodsList);
    }

    @Override
    @Transactional
    public R buyGoods(Goods goods) {
        Integer row = goodsMapper.buyGoods(goods);
        if (row > 0) {
            List<Goods> goodsList = goodsMapper.goodsList();
            //存入redis
            redisTemplate.opsForValue().set("goodsList", goodsList);
            return R.ok("购买成功！");
        } else {
            return R.error("购买失败！");
        }
    }

    /**
     * 当执行被@Cacheable标记的方法时，会先检查redis中是否有对应的缓存，有则返回，无则执行当前方法，并将返回值存储入redis中，以便下次查询使用redis中的缓存
     *
     * @param pageNum
     * @return Cacheable  在redis中存储的键为“value::key"
     */
    @Override
    @Cacheable(value = "goodsListInPage", key = "#pageNum")
    public PageInfo<Goods> goodsListInPage(Integer pageNum) {
        //开启分页插件
        PageHelper.startPage(pageNum, 3);

        //查询结果
        List<Goods> goodsList = goodsMapper.goodsList();
        System.out.println("查询数据库！");

        //将结果封装到PageInfo对象中
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);

        return goodsPageInfo;
    }

    /**
     * 简单的业务使用
     * @Cacheable 查询操作
     * @CachePut 被此注解修饰的方法被调用时，一定会执行其内容，并且将返回的结果保存入缓存--》更新和插入数据的操作
     * @CacheEvict 直接删除对应的缓存--》删除操作
     */

}
