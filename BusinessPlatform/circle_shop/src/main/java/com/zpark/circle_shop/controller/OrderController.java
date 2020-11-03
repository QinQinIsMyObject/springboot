package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.entity.Cart;
import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.entity.Order;
import com.zpark.circle_shop.service.CartService;
import com.zpark.circle_shop.service.OrderService;
import com.zpark.circle_shop.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping("/createOrder")
    public Object createOrder(@RequestBody List<Cart> carts, HttpSession session) {
        CircleUser user = (CircleUser) session.getAttribute("circleUser");
        if (carts.size() < 1) {
            return R.error("参数提交有误！");
        }
        return orderService.createOrder(carts, user);
    }

    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/notifyUrl")
    public Object notifyUrl(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入异步回调...");
        response.setContentType("text/html;charset=utf-8");
        //验签
        try {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            if ("TRADE_SUCCESS".equals(trade_status)) {
                Order order = orderService.selectByNum(new Order().setONum(out_trade_no));
                if (order == null) {
                    return "fail";
                }
                //修改订单状态
                order.setState(1);
                orderService.updateOrder(order);
                //删除购物车信息
                List<Cart> carts = new ArrayList<>();
                carts.add(new Cart().setGdId(order.getGdId()));
                cartService.deleteByUIdAndGdId(new CircleUser().setId(order.getUId()), carts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping("/returnUrl")
    public Object returnUrl(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入同步回调...");
        //验签
        response.setContentType("text/html?charset=UTF-8");
        String redirect = "<script>location.href='http://127.0.0.1:5500/pay-success.html'</script>";
        return redirect;
    }

    @GetMapping("/getPayUrl")
    public Object getPayUrl(Order order, HttpServletResponse response) {
        String url = orderService.getPayUrl(order);
        System.out.println(order);

        //设置Content-Type
        response.setContentType("text/html;charset=UTF-8");

        if (url == null) {
            return "<script>location.href='http://127.0.0.1:5500/pay-error.html'</script>";
        } else {
            return url;
        }
    }

    @GetMapping("/getOrders")
    public Object getOrders(HttpSession session) {

        CircleUser user = (CircleUser) session.getAttribute("circleUser");
        List<Order> orders = orderService.selectOrderByUId(user);
        return R.ok("查询成功！").addData("orders", orders);
    }

}
