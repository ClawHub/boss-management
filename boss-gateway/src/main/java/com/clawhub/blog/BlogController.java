package com.clawhub.blog;//package com.clawhub.boss;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.blog.service.BlogService;
import com.clawhub.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * <Description> 博客网关<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/4 <br>
 */
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/submit")
    public String submit(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String author = body.getString("author");
        String title = body.getString("title");
        String headerImg = body.getString("headerImg");
        String subtitle = body.getString("subtitle");
        List<String> tags = body.getJSONArray("tags").toJavaList(String.class);
        String content = body.getString("content");
        String displayTime = body.getString("displayTime");
        try {
            blogService.submit(author, title, subtitle, tags, content, headerImg, displayTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.getSucc();
    }

    @GetMapping("/refreshBlog")
    public String refreshBlog() {
        String res = null;
        try {
            res = blogService.refreshBlog();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResultUtil.getSucc(res);
    }

}
