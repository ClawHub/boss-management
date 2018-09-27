package com.clawhub.picbed.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 图床服务
 */
public interface PicBedService {
    /**
     * 图片上传
     *
     * @param map 图片信息
     * @return url
     * @throws IOException
     * @throws InterruptedException
     */
    String upload(Map<String, MultipartFile> map) throws IOException, InterruptedException;
}
