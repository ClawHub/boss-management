package com.clawhub.picbed;//package com.clawhub.boss;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.picbed.entity.PicBed;
import com.clawhub.picbed.service.PicBedService;
import com.clawhub.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
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

    @PostMapping("/upload")
    public String upload(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String image = body.getString("image");
        String title = body.getString("title");
        String alt = body.getString("alt");
        String tags = body.getString("tags");
        String url = null;
        try {
            url = picBedService.upload(image, title, alt, tags);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.getSucc(url);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "/put/**")
    public String put(MultipartHttpServletRequest request) throws IOException {
        Map<String, MultipartFile> map = request.getFileMap();
        String url = "";
        try {
            url = ResultUtil.getSucc(picBedService.upload(map));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return url;
    }

    @GetMapping("/queryPic/{tags}")
    public String queryPic(@PathVariable String tags) {
        List<PicBed> list = picBedService.queryPic(tags);
        return ResultUtil.getSucc(list);
    }

    @GetMapping("/refreshPicBed")
    public String refreshPicBed() {
        String res = null;
        try {
            res = picBedService.refreshPicBed();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResultUtil.getSucc(res);
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
