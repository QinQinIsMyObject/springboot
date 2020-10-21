package com.zpark.springboot04.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Celery
 */
@Data
public class R {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 封装的信息
     */
    private String message;
    /**
     * 封装的数据
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 操作成功的状态
     *
     * @param message 信息
     * @return R对象
     */
    public static R ok(String message) {
        R r = new R();
        r.setCode(1);
        r.setMessage(message);
        return r;
    }

    /**
     * 操作失败的状态
     *
     * @param message 信息
     * @return R对象
     */
    public static R error(String message) {
        R r = new R();
        r.setCode(0);
        r.setMessage(message);
        return r;
    }

    /**
     * 链式增加data数据的方法
     *
     * @param key   数据的键
     * @param value 数据的值
     * @return R对象
     */
    public R addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
