package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.GoodsRecord;
import com.zpark.circle_shop.mapper.GoodsRecordMapper;
import com.zpark.circle_shop.service.GoodsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsRecordServiceImpl implements GoodsRecordService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GoodsRecordMapper goodsRecordMapper;

    @Override
    public void viewRecord() {
        //获取redis中用户近期的浏览商品清单
        List<Object> viewList = redisTemplate.opsForList().range("goodsViewList", 0, -1);
        if (viewList != null) {
            //遍历集合，准备进行持久化
            for (Object o : viewList) {
                Integer gId = (Integer) o;
                //获取商品的浏览记录
                Integer views = (Integer) redisTemplate.opsForValue().get("goodsViews::" + gId);
                //查询数据库的信息
                GoodsRecord goodsRecord = goodsRecordMapper.selectRecord(gId);
                if (goodsRecord == null) {
                    //封装记录
                    goodsRecord = new GoodsRecord().setGId(gId).setViews(views).setDeals(0);
                    //新增记录
                    goodsRecordMapper.insertRecord(goodsRecord);
                } else {
                    if (views != null && views > 0) {
                        //更新记录
                        goodsRecordMapper.updateRecord(goodsRecord.setViews(views));
                    }
                }
                //删除上一次的记录，准备下次的记录
                redisTemplate.delete("goodsViews::" + gId);
            }
            //删除上一次的记录，准备下次的记录
            redisTemplate.delete("goodsViewList");
        }
    }

    @Override
    public void dealRecord() {
        //准备持久化交易量
        List<Object> dealList = redisTemplate.opsForList().range("goodsDealList", 0, -1);
        if (dealList != null) {
            //遍历集合，准备进行持久化
            for (Object o : dealList) {
                Integer gId = (Integer) o;
                //获取商品的交易记录
                Integer deals = (Integer) redisTemplate.opsForValue().get("goodsDeals::" + gId);
                //查询数据库的信息
                GoodsRecord goodsRecord = goodsRecordMapper.selectRecord(gId);
                if (goodsRecord == null) {
                    //封装记录
                    goodsRecord = new GoodsRecord().setGId(gId).setDeals(deals).setViews(0);
                    //新增记录
                    goodsRecordMapper.insertRecord(goodsRecord);
                } else {
                    if (deals != null && deals > 0) {
                        //更新记录
                        goodsRecordMapper.updateRecord(goodsRecord.setDeals(deals));
                    }
                }
                //删除上一次的记录，准备下次的记录
                redisTemplate.delete("goodsDeals::" + gId);
            }
            //删除上一次的记录，准备下次的记录
            redisTemplate.delete("goodsDealList");
        }
    }

}
