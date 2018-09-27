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

import java.io.IOException;
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
        record.setClassify("default");
        record.setName(originalFilename);
        record.setRemark("this is pic");
        picBedMapper.insert(record);
        //返回url
        return src;
    }

    @Override
    public List<PicBed> queryPic(String classify) {
        return picBedMapper.queryPic(classify);
    }


}
