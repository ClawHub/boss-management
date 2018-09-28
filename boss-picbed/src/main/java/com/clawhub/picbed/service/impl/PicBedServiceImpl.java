package com.clawhub.picbed.service.impl;

import com.clawhub.picbed.entity.PicBed;
import com.clawhub.picbed.mapper.PicBedMapper;
import com.clawhub.picbed.service.PicBedService;
import com.clawhub.util.FileUtil;
import com.clawhub.util.IDGenarator;
import com.clawhub.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class PicBedServiceImpl implements PicBedService {
    /**
     * The Path.
     */
    @Value("${pic.bed.path}")
    private String picBedPath;

    /**
     * The Pic bed shell path.
     */
    @Value("${pic.bed.shell.path}")
    private String picBedShellPath;

    /**
     * The Pic bed mapper.
     */
    @Autowired
    private PicBedMapper picBedMapper;

    @Override
    public String upload(Map<String, MultipartFile> map) throws IOException, InterruptedException {
        MultipartFile multipartFile = map.get("file");
        String originalFilename = multipartFile.getOriginalFilename();
        String src = "https://raw.githubusercontent.com/wiki/clawhub/pic-bed/pic/" + originalFilename;
        //写图片
        FileUtil.byte2image(multipartFile.getBytes(), picBedPath + originalFilename);
        //上传图片
        ShellUtil.runShell(picBedShellPath);
        //入库
        PicBed record = new PicBed();
        record.setId(IDGenarator.getID());
        record.setSrc(src);
        record.setTags("default");
        record.setTitle(originalFilename);
        record.setAlt("this is pic");
        picBedMapper.insert(record);
        //返回url
        return src;
    }

    @Override
    public List<PicBed> queryPic(String tags) {
        return picBedMapper.queryPic(tags);
    }

    @Override
    public String upload(String image, String title, String alt, String tags) throws IOException {
        String src = "https://raw.githubusercontent.com/wiki/clawhub/pic-bed/pic/" + title;
        String msrc = "https://raw.githubusercontent.com/wiki/clawhub/pic-bed/pic/small/" + title;
        //base64解密
        byte[] bytes = Base64.getDecoder().decode(image);
        //写大图片
        FileUtil.byte2image(bytes, picBedPath + title);
        //写小图片处理
        BufferedImage bi = FileUtil.resizeImage(bytes, msrc, 240);
        float width = bi.getWidth(); // 像素
        float height = bi.getHeight(); // 像素
        //入库
        PicBed record = new PicBed();
        record.setAlt(alt);
        record.setTags(tags);
        record.setTitle(title);
        record.setSrc(src);
        record.setId(IDGenarator.getID());
        record.setMsrc(msrc);
        record.setW((int) width);
        record.setH((int) height);
        picBedMapper.insert(record);
        return src;
    }

}
