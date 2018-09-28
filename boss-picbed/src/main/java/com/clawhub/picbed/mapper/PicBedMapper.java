package com.clawhub.picbed.mapper;


import com.clawhub.common.IMapper;
import com.clawhub.picbed.entity.PicBed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PicBedMapper extends IMapper<PicBed> {
    /**
     * 模糊查询图片信息
     *
     * @param tags 标签
     * @return 图片信息
     */
    List<PicBed> queryPic(@Param("tags") String tags);
}