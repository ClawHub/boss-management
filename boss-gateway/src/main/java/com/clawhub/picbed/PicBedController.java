package com.clawhub.picbed;//package com.clawhub.boss;

import com.clawhub.picbed.service.PicBedService;
import com.clawhub.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * <Description> 图床网关<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/4 <br>
 */
@RestController
@RequestMapping("picBed")
public class PicBedController {

    @Autowired
    private PicBedService picBedService;
    @PostMapping("/post")
    public String post(@RequestBody String param) {
        return ResultUtil.getSucc(param);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "/upload/**")
    public String upload(MultipartHttpServletRequest request) throws IOException {
        Map<String, MultipartFile> map = request.getFileMap();
        return ResultUtil.getSucc(picBedService.upload(map));
    }




    /**
     * Welcome string.
     *
     * @return the string
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return ResultUtil.getSucc();
    }
}
