package com.zpark.circle_shop.service;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.entity.Order;
import com.zpark.circle_shop.util.R;

import java.util.List;

/**
 * @author Celery
 */
public interface OrderService {

    R createOrder(List<Cart> carts, CircleUser user);

    Integer createOneOrder(Cart cart, CircleUser user) throws Exception;

    String getPayUrl(Order order);

    List<Order> selectOrderByUId(CircleUser user);

    Integer updateOrder(Order order);

    Order selectByNum(Order order);

}
