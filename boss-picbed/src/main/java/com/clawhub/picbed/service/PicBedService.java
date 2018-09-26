package com.clawhub.picbed.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图床服务
 */
public interface PicBedService {
    /**
     * 上传图片
     *
     * @param map 图片
     * @return 图片url
     */
    String upload(Map<String, MultipartFile> map);
}
