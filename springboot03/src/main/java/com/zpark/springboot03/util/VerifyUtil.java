package com.zpark.springboot03.util;

/**
 * @author Celery
 */
public class VerifyUtil {
    /**
     * 生成任意长度纯数字验证码
     *
     * @param len
     * @return
     */
    public static String generateVerifyCode(int len) {
        String verifyCode = "";
        for (int i = 0; i < len; i++) {
            verifyCode += (int) (Math.random() * 10);
        }
        return verifyCode;
    }
}
