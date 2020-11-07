package com.zpark.circle_shop.aspect;

import com.zpark.circle_shop.entity.GoodsDetail;
import com.zpark.circle_shop.mapper.GoodsMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-1)
public class GoodsDealRecordAspect {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.zpark.circle_shop.annotation.GoodsDealRecord)")
    public void goodsDealRecord() {
    }

    @Around("goodsDealRecord()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        //拿参数
        Object[] args = joinPoint.getArgs();
        com.zpark.circle_shop.entity.Order order = (com.zpark.circle_shop.entity.Order) args[0];
        //正常执行
        Object result = joinPoint.proceed();
        Integer row = (Integer) result;
        if (order != null && order.getGdId() != null && row > 0) {
            //通过gdId查询商品的gId
            GoodsDetail goodsDetail = goodsMapper.findGoodsDetailById(new GoodsDetail().setGdId(order.getGdId()));
            if (goodsDetail != null) {
                if (redisTemplate.opsForValue().get("goodsDeals::" + goodsDetail.getGId()) == null) {
                    redisTemplate.opsForList().leftPush("goodsDealList", goodsDetail.getGId());
                }
                redisTemplate.opsForValue().increment("goodsDeals::" + goodsDetail.getGId());
            }
        }
        return result;
    }

}
