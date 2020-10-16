package com.zpark.springboot02.controller;

import com.zpark.springboot02.entity.MailInfo;
import com.zpark.springboot02.service.MailService;
import com.zpark.springboot02.service.UCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/ucenter")
public class UCenterController {

    @Autowired
    private UCenterService uCenterService;

    @Autowired
    private MailService mailService;

    /**
     * 查询
     *
     * @return
     */
    @GetMapping("/findUCenterInPage")
    public Object findUCenterInPage(@RequestParam("pageNum") Integer pageNum) {
        return uCenterService.findUCenterInPage(pageNum);
    }

    /**
     * 邮件
     *
     * @param mailInfo
     * @return
     */
    @PostMapping("/sendSimpleMail")
    public Object sendSimpleMail(@RequestBody MailInfo mailInfo) {
        return mailService.sendEmail(mailInfo);
    }

//    public Object

}
