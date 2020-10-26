package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.service.GoodsService;
import com.zpark.circle_shop.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goodsList")
    public Object goodsList(@RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        Object pageInfo = goodsService.goodsList(pageNum);
        return pageInfo == null ? R.error("服务器异常，查询失败！") : R.ok("查询成功！").addData("goodsList", pageInfo);
    }

}
