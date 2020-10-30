package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {

    /**
     * 向购物车中加入一条数据（某个用户购买的某个商品）
     *
     * @param cart
     * @return
     */
    Integer insert(Cart cart);

    /**
     * 更新某用户购物车中商品的数量
     *
     * @param cart
     * @return
     */
    Integer updateAmount(Cart cart);

    /**
     * 根据用户id和商品细节id查询有无购物车记录
     *
     * @param cart
     * @return
     */
    Cart selectByUIdAndGdId(Cart cart);

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
    Integer deleteByUIdAndGdId(@Param("user") CircleUser user, @Param("carts") List<Cart> carts);

}
