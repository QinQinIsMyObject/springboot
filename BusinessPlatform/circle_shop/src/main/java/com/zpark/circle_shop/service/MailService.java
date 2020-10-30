package com.zpark.circle_shop.service;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.util.R;

public interface MailService {

    R sendVerify(CircleUser user);

}
