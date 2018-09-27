package com.clawhub.picbed;//package com.clawhub.boss;

import com.clawhub.picbed.service.PicBedService;
import com.clawhub.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
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
        String url = "";
        try {
            url = ResultUtil.getSucc(picBedService.upload(map));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return url;
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
