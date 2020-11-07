package com.zpark.circle_shop.aspect;

import com.zpark.circle_shop.entity.Goods;
import com.zpark.circle_shop.entity.GoodsDetail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@Order(-1) //规定顺序，保证这个环绕先开始
public class GoodsViewRecordAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 随便命名一个方法: public void 名称随便起(参数不用写)
     * 添加 @PointCut("@annotation(注解全名)") 注解，规定切点，表名，当前的注解加给谁，谁就是切点
     */
    @Pointcut("@annotation(com.zpark.circle_shop.annotation.GoodsViewRecord)")
    public void goodsViewRecord() {
    }

    /**
     * 环绕增强
     *
     * @return
     */
    @Around("goodsViewRecord()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取被增强方法的参数
        Object[] args = joinPoint.getArgs();
        Goods goods = (Goods) args[0];
        //让被增强方法（原方法）正常执行，得到执行的返回值结果
        Object result = joinPoint.proceed();
        //进行类型转化
        List<GoodsDetail> goodsDetails = (List<GoodsDetail>) result;
        if (goods != null && goods.getGId() != null && goodsDetails.size() > 0) {
            if (redisTemplate.opsForValue().get("goodsViews::" + goods.getGId()) == null) {
                //在redis中加入商品的浏览记录
                redisTemplate.opsForList().leftPush("goodsViewList", goods.getGId());
            }
            //有这个键，则自增，没有这个键，则创建
            redisTemplate.opsForValue().increment("goodsViews::" + goods.getGId());
        }
        //返回被增强方法（原方法）的结果
        return result;
    }

}
