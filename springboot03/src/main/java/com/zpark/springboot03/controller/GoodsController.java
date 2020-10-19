package com.zpark.springboot03.controller;

import com.zpark.springboot03.entity.Goods;
import com.zpark.springboot03.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("goodsList")
    public Object goodsList() {
        return goodsService.goodsList();
    }

    @PostMapping("/buyGoods")
    public Object buyGoods(@RequestBody Goods goods) {
        return goodsService.buyGoods(goods);
    }

}
