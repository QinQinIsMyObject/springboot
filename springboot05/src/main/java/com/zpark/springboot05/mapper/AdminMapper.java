package com.zpark.springboot05.mapper;

import com.zpark.springboot05.entity.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Celery
 * Repository持久层
 */
@Repository
public interface AdminMapper {
    @Select("select * from admin where name=#{name}")
    Admin findAdminByName(@RequestParam("name") String name);
}
