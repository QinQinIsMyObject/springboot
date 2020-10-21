package com.zpark.springboot03.controller;

import com.zpark.springboot03.entity.Ucenter;
import com.zpark.springboot03.service.UcenterService;
import com.zpark.springboot03.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/ucenter")
public class UcenterController {
    @Autowired
    private UcenterService ucenterService;

    @PostMapping("/ucenterRegister")
    public Object ucenterRegister(@RequestBody Ucenter ucenter) {
        return null;
    }

    @PostMapping("/sendVerifyCode")
    public Object sendVerifyCode(@RequestBody Ucenter ucenter) {
        return ucenterService.sendVerifyCode(ucenter);
    }

    @GetMapping("verifyPic")
    public Object verifyPic(HttpServletResponse response) {
        //输出验证码图片，返回的就不是json了
        Map<String, Object> data = VerifyUtil.generateVerifyPic();
        BufferedImage image = (BufferedImage) data.get("verifyPic");
        try {
            //输出图片
            ImageIO.write(image, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
