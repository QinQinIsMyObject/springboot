package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.CircleUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    CircleUser userLogin(CircleUser user);

    CircleUser exists(CircleUser user);

    Integer userRegister(CircleUser user);

}
