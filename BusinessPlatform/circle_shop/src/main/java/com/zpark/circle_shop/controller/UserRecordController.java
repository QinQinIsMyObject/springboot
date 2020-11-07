package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/record/user")
public class UserRecordController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/visit")
    public Object visit(HttpSession session) {
        String sessionId = session.getId();
        Object r = redisTemplate.opsForHash().get("userVisitMap", sessionId);
        if (r == null) {
            redisTemplate.opsForValue().increment("userVisits");
            redisTemplate.opsForHash().put("userVisitMap", sessionId, 1);
        }
        return R.ok("");
    }

}
