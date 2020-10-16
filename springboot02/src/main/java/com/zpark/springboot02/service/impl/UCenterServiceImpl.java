package com.zpark.springboot02.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpark.springboot02.entity.UCenter;
import com.zpark.springboot02.mapper.UCenterMapper;
import com.zpark.springboot02.service.UCenterService;
import com.zpark.springboot02.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Celery
 */
@Service
public class UCenterServiceImpl implements UCenterService {

    @Autowired
    private UCenterMapper uCenterMapper;

    /**
     * @param pageNum
     * @return
     */
    @Override
    public R findUCenterInPage(Integer pageNum) {
        int pageSize = 3;
        // 开启pageHelper插件(页面数量，页面大小)，约束下一次的查询
        PageHelper.startPage(pageNum, pageSize);
        //查询需要的结果
        List<UCenter> ucenters = uCenterMapper.ucenterList();
        //将结果封装在“页面信息”类中
        PageInfo<UCenter> pageInfo = new PageInfo<>(ucenters);
        //返回结果
        return R.ok("查询成功！").addData("pageInfo", pageInfo);
    }

}
