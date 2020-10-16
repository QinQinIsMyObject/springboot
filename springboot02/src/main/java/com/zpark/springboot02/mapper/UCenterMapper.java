package com.zpark.springboot02.mapper;

import com.zpark.springboot02.entity.UCenter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Celery
 * Repository：解决UCenterServiceImpl.java中Mapper报错
 */
@Mapper
@Repository
public interface UCenterMapper {
    /**
     * 查询
     *
     * @return
     */
    List<UCenter> ucenterList();
}
