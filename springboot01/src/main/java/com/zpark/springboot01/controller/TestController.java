package com.zpark.springboot01.controller;

import com.zpark.springboot01.entity.TestObject;
import com.zpark.springboot01.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1")
    public Object test1() {
        return R.ok("请求成功!");
    }

    /**
     * 使用post方法提交数据,数据是存放在'请求体'中的;
     * 当请求体的类型为application/x-www-form-urlencoded,封装的对象不需要任何注解;
     * 当请求体的类型为application/json,接收参数一定需要添加@RequestBody;
     *
     * @return
     */
//    @PostMapping("/test2")
//    public Object test2(@RequestBody TestObject testObject) {
//        return R.ok("数据接收成功!").addData("params", testObject);
//    }
    @PostMapping("/test2")
    public Object test2(TestObject testObject) {
        return R.ok("数据接收成功!").addData("params", testObject);
    }

}
