package com.zpark.circle_shop.util;

/**
 * @author Celery
 */
public class UserInfoUtil {

    public static boolean checkUsername(String username) {
        String regex = "[a-zA-Z]+[a-zA-Z0-9_]+";
        return username != null && username.matches(regex) && username.length() <= 16 && username.length() >= 5;
    }

    public static boolean checkEmail(String email) {
        String regex = "[a-zA-Z0-9_]{4,20}@(qq|QQ|163|126|gmail)(\\.com|\\.cn|\\.com\\.cn)";
        return email != null && email.matches(regex);
    }

    public static void main(String[] args) {
        System.out.println(checkEmail("lizueyuan163.com"));
    }

}
