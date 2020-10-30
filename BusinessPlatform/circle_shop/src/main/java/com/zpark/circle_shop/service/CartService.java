package com.zpark.circle_shop.service;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;

import java.util.List;

public interface CartService {

    /**
     * 用户添加商品到购物车
     *
     * @param cart
     * @return
     */
    boolean addToCart(Cart cart);

    /**
     * 查询某用户的所有购物车记录
     *
     * @param user
     * @return
     */
    List<Cart> selectByUId(CircleUser user);

    /**
     * 删除某用户的全部购物车记录
     *
     * @return
     */
    Integer deleteByUId(CircleUser user);

    /**
     * 批量删除某用户的一些购物车记录
     *
     * @param carts
     * @return
     */
    Integer deleteByUIdAndGdId(CircleUser user, List<Cart> carts);


}
