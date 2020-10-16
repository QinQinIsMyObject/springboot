package com.zpark.springboot02.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zpark.springboot02.util.AliPayUtil;
import com.zpark.springboot02.util.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Celery
 *
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    /**
     * 使用支付宝支付功能前，需要先添加依赖： alipay-sdk
     *
     * @param money
     * @return
     */
    @RequestMapping("/aliPayTest")
    public Object aliPayTest(@RequestParam("money") String money, HttpServletResponse response) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayUtil.gatewayUrl, AliPayUtil.appId, AliPayUtil.privateKey, "json", AliPayUtil.charset, AliPayUtil.privateKey, AliPayUtil.signType);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AliPayUtil.return_url);
        alipayRequest.setNotifyUrl(AliPayUtil.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填

        String out_trade_no = UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
        //付款金额，必填  ShopName
        String total_amount = money;
        //订单名称，必填
        String subject = "Iphone 12";
        //商品描述，可空
        String body = "Iphone12上市了！";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "15m";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String url = "";
        try {
            url = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

            response.setContentType("text/html;charset=" + AliPayUtil.charset);

            response.getWriter().write(url); // 直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/notify")
    public Object notifyUrl() {
        return "SUCCESS";
    }

    //
    @RequestMapping("/return")
    public Object returnUrl() {
        return R.ok("支付成功！");
    }

}
