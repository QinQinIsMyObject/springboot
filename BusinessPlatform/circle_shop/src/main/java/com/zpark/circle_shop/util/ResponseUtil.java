package com.zpark.circle_shop.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Celery
 */
public class ResponseUtil {

    /**
     * 直接使用response对象返回json的工具包
     *
     * @param response HttpServletResponse 对象
     * @param body     将要转变为Json的对象
     */
    public static void responseJson(HttpServletResponse response, Object body) {
        //设定响应体类型
        response.setContentType("application/json;charset=UTF-8");
        //创建一个对象映射json的工具类实例
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(response.getOutputStream(), body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
