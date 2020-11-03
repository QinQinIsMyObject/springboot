package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.entity.GoodsDetail;
import com.zpark.circle_shop.entity.Order;
import com.zpark.circle_shop.mapper.CartMapper;
import com.zpark.circle_shop.mapper.GoodsMapper;
import com.zpark.circle_shop.mapper.OrderMapper;
import com.zpark.circle_shop.service.OrderService;
import com.zpark.circle_shop.util.AliPayUtil;
import com.zpark.circle_shop.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Celery
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public R createOrder(List<Cart> carts, CircleUser user) {
        int count = 0;
        for (Cart cart : carts) {
            try {
                createOneOrder(cart, user);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.ok("创建订单完成").addData("createOrders", count);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Integer createOneOrder(Cart cart, CircleUser user) throws Exception {
        cart.setUId(user.getId());
        Cart c = cartMapper.selectById(cart);
        if (c == null) {
            throw new Exception("参数格式不正确");
        }

        GoodsDetail goodsDetail = goodsMapper.findGoodsDetailById(new GoodsDetail().setGdId(c.getGdId()));
        if (goodsDetail.getAmount() < c.getAmount()) {
            throw new Exception("库存不足");
        }

        //计算总价 BigDecimal
        BigDecimal amount = new BigDecimal(c.getAmount() + "");
        BigDecimal price = new BigDecimal(c.getPrice() + "");
        BigDecimal totalPrice = amount.multiply(price).setScale(2, RoundingMode.HALF_UP);

        String orderNum = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        //封装一个order对象
        Order order = new Order()
                .setUId(user.getId())
                .setGdId(c.getGdId())
                .setONum(orderNum)
                .setOName("CircleShop电子商城:" + c.getGName())
                .setPrice(c.getSpecial() == null ? c.getPrice() : c.getSpecial())
                .setAmount(c.getAmount())
                .setTotalPrice(Double.parseDouble(totalPrice.toString()))
                .setDetails("~~~");

        orderMapper.createOrder(order);
        goodsMapper.updateGoodsDetail(goodsDetail.setAmount(goodsDetail.getAmount() - order.getAmount()));

        //得到一个订单的支付链接
        String url = AliPayUtil.createPayUrl(order);
        redisTemplate.opsForValue().set("order::" + orderNum, url, 15, TimeUnit.MINUTES);

        //十五分钟倒计时
        new Thread(() -> {
            try {
                Thread.sleep(15 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order o = orderMapper.selectOrderByNum(order);
            if (o.getState() == 0) {
                o.setState(3);
                orderMapper.updateOrder(order);
            }
        }).start();

        return 1;
    }

    @Override
    public String getPayUrl(Order order) {
        String url = (String) redisTemplate.opsForValue().get("order::" + order.getONum());
        return url;
    }

    @Override
    public List<Order> selectOrderByUId(CircleUser user) {
        return orderMapper.selectOrderByUId(user);
    }

    @Override
    public Integer updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public Order selectByNum(Order order) {
        return orderMapper.selectOrderByNum(order);
    }

}
