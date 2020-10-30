package com.zpark.circle_shop.mapper;

import com.zpark.circle_shop.entity.CircleUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    CircleUser userLogin(CircleUser circleUser);

    CircleUser exists(CircleUser user);

}
