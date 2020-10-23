package com.zpark.springboot05.controller;

import com.zpark.springboot05.util.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    public Object hello() {
        return R.ok("Hello Security");
    }

    @PreAuthorize("hasAuthority('insert')")
    @GetMapping("/insert")
    public Object insert() {
        return R.ok("新增成功！");
    }

    @PreAuthorize("hasAuthority('delete')")
    @GetMapping("/delete")
    public Object delete() {
        return R.ok("删除成功！");
    }

    @GetMapping("/update")
    public Object update() {
        return R.ok("修改成功！");
    }

    @GetMapping("/select")
    public Object select() {
        return R.ok("查询成功！");
    }

}
