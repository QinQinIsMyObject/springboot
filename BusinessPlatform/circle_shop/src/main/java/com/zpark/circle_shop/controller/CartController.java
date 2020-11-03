package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.service.CartService;
import com.zpark.circle_shop.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public Object addToCart(@RequestBody Cart cart, HttpSession session) {
        CircleUser user = (CircleUser) session.getAttribute("circleUser");
        if (user == null) {
            return R.error("您还未登录").setCode(2);
        }
        cart.setUId(user.getId());
        return cartService.addToCart(cart) ? R.ok("添加到购物车成功！") : R.error("添加至购物车失败！请重试。");
    }

    @GetMapping("/selectByUId")
    public Object selectByUId(HttpSession session) {
        CircleUser user = (CircleUser) session.getAttribute("circleUser");
        List<Cart> carts = cartService.selectByUId(user);
        return carts == null ? R.error("服务器异常，查询失败！") : R.ok("查询成功！").addData("carts", carts);
    }

    @PostMapping("/api/cart/deleteByUId")
    public Object deleteByUId(HttpSession session) {
        CircleUser user = (CircleUser) session.getAttribute("circleUser");
        return cartService.deleteByUId(user) > 0 ? R.ok("删除成功！") : R.error("服务器异常！删除失败。");
    }

    @PostMapping("/deleteByUIdAndGdId")
    public Object deleteByUIdAndGdId(@RequestBody List<Cart> carts, HttpSession session) {
        CircleUser user = (CircleUser) session.getAttribute("circleUser");
        return cartService.deleteByUIdAndGdId(user, carts) > 0 ? R.ok("删除成功！") : R.error("服务器异常！删除失败。");
    }

}
