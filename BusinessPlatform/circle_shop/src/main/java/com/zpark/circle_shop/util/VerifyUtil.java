package com.zpark.circle_shop.util;


import org.apache.axis.encoding.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, Object> generateVerifyPic() {
        int len = 4;
        //验证码的字典
        String letters = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        //从验证码当中随机选取len个
        String verifyCode = "";
        for (int i = 0; i < len; i++) {
            verifyCode += letters.charAt((int) (Math.random() * letters.length()));
        }
        //准备一个图片
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_3BYTE_BGR);
        //从图片中获得画笔Graphics
        Graphics2D g = image.createGraphics();
        //加入抗锯齿、防抖等等的要求
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

        //给验证码图片增加背景颜颜色为白色
        g.setColor(Color.white);
        g.fillRect(0, 0, 100, 30);
        //在验证码的外围增加一个黑框
        g.setColor(Color.black);
        g.drawRect(0, 0, 100, 30);
        //画文字
        g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        for (int i = 0; i < len; i++) {
            //加入旋转效果
            //得到一个旋转角度
            double theta = Math.random() * Math.PI / 4 * ((int) (Math.random() * 2) == 0 ? 1 : -1);
            //开始旋转
            g.rotate(theta, 20 + i * 22, 15);
            g.setColor(new Color((int) (Math.random() * 240), (int) (Math.random() * 240), (int) (Math.random() * 240)));
            g.drawString(verifyCode.charAt(i) + "", 10 + i * 22, 22);
            //旋转归位
            g.rotate(-theta, 20 + i * 22, 15);
        }

        //加干扰线
        for (int i = 0; i < 5; i++) {
            g.setColor(new Color((int) (Math.random() * 240), (int) (Math.random() * 240), (int) (Math.random() * 240)));
            //选取第一个点
            int x = (int) (Math.random() * 101);
            int y = (int) (Math.random() * 31);
            //选取第二个点
            int x1 = (int) (Math.random() * 101);
            int y1 = (int) (Math.random() * 31);
            g.drawLine(x, y, x1, y1);
        }

        //base64转化
        String base64 = null;
        try {
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", stream);
            base64 = Base64.encode(stream.toByteArray());
            System.out.println("data:image/jpeg;base64," + base64);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建一个map
        Map<String, Object> data = new HashMap<>();
        //在data里面加入数据
        data.put("verifyCode", verifyCode);
        data.put("verifyPic", image);
        data.put("verifyBase64", "data:image/jpeg;base64," + base64);

        return data;
    }

}