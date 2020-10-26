package com.zpark.circle_shop;

import com.zpark.circle_shop.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CircleShopApplicationTests {

    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
        System.out.println(goodsMapper.goodsList());
    }

}
