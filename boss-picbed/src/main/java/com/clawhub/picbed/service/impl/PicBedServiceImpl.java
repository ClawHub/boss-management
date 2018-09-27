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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /**
     * 上传图片
     *
     * @param image
     * @param title
     * @param alt
     * @param classify
     * @return
     */
    @Override
    public String upload(String image, String title, String alt, String classify) throws IOException {
        String src = "https://raw.githubusercontent.com/wiki/clawhub/pic-bed/pic/" + title;
        String msrc = "https://raw.githubusercontent.com/wiki/clawhub/pic-bed/pic/small/" + title;
        //base64解密
        byte[] bytes = Base64.getDecoder().decode(image);
        //写图片
        FileUtil.byte2image(bytes, picBedPath + title);
        //图片处理
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        float width = bi.getWidth(); // 像素
        float height = bi.getHeight(); // 像素
//        scaleSize 图片的修改比例，目标宽度
        int scaleSize = 100;
        float scale = width / scaleSize;
        BufferedImage buffImg = new BufferedImage(scaleSize, (int) (height / scale), BufferedImage.TYPE_INT_RGB);
        //使用TYPE_INT_RGB修改的图片会变色
        buffImg.getGraphics().drawImage(bi.getScaledInstance(scaleSize, (int) (height / scale), Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(buffImg, "JPEG", new File(msrc));

        //入库

        return src;
    }


}
