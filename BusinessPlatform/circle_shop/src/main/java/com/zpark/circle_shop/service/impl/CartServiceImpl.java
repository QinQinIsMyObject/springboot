package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.mapper.CartMapper;
import com.zpark.circle_shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public boolean addToCart(Cart cart) {
        Cart c = cartMapper.selectByUIdAndGdId(cart);
        if (c == null) {
            //购物车里没有原始记录，说明要新增
            Integer result = cartMapper.insert(cart);
            return result > 0;
        } else {
            //有原始记录，要更新
            Integer result = cartMapper.updateAmount(cart);
            return result > 0;
        }
    }

    @Override
    public List<Cart> selectByUId(CircleUser user) {
        return cartMapper.selectByUId(user);
    }

    @Override
    public Integer deleteByUId(CircleUser user) {
        return cartMapper.deleteByUId(user);
    }

    @Override
    public Integer deleteByUIdAndGdId(CircleUser user, List<Cart> carts) {
        return cartMapper.deleteByUIdAndGdId(user, carts);
    }

}
