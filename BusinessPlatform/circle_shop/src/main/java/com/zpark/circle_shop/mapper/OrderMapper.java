package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Celery
 */
@Repository
public interface OrderMapper {

    Integer createOrder(Order order);

    Integer updateOrder(Order order);

    List<Order> selectOrderByUId(CircleUser user);

    Order selectOrderByNum(Order order);

}
