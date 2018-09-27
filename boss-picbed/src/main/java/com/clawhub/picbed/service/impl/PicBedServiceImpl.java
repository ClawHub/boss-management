package com.clawhub.picbed.service.impl;

import com.clawhub.picbed.service.PicBedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@Service
public class PicBedServiceImpl implements PicBedService {
    /**
     * The Path.
     */
    @Value("${pic.bed.path}")
    private String path;
    /**
     * 上传图片
     *
     * @param map 图片
     * @return 图片url
     */
    @Override
    public String upload(Map<String, MultipartFile> map) {
        MultipartFile multipartFile = map.get("file");
        String originalFilename = multipartFile.getOriginalFilename();
        try {
            byte2image(multipartFile.getBytes(), path + originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用git函数

        //入库

        return "https://raw.githubusercontent.com/wiki/clawhub/pic-bed/pic/"+originalFilename;
    }

    public void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

}
