package com.zpark.circle_shop;

import com.zpark.circle_shop.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class CircleShopApplicationTests {

    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
        System.out.println(goodsMapper.goodsList());
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.6");
        BigDecimal b = new BigDecimal("1.3");
        System.out.println(a.multiply(b));
    }

}
