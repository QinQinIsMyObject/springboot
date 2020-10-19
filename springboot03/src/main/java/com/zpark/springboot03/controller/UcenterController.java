package com.zpark.springboot03.controller;

import com.zpark.springboot03.entity.Ucenter;
import com.zpark.springboot03.service.UcenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object sendVerifyCode(@RequestBody Ucenter ucenter){
        return ucenterService.sendVerifyCode(ucenter);
    }
}
