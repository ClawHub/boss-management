package com.clawhub.blog.service.impl;

import com.clawhub.blog.service.BlogService;
import com.clawhub.util.ShellUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <Description> 博客逻辑<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/30 <br>
 */
@Service
public class BlogServiceImpl implements BlogService {

    /**
     * 文章路径
     */
    @Value("${blog.file.path}")
    private String fileDesPath;
    /**
     * The Blog shell path.
     */
    @Value("${blog.shell.path}")
    private String blogShellPath;

    @Override
    public void submit(String author, String title, String subtitle, List<String> tags, String content, String headerImg, String displayTime) throws IOException {
        //博客文件名
        String blogFileName = new StringBuffer()
                .append(LocalDateTime.parse(displayTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .append("-")
                .append(title)
                .append(".markdown").toString();
        //文章模板读取
        InputStream inputStream = this.getClass().getResourceAsStream("/template/yyyy-MM-dd-title.markdown");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String str = new String(bytes);
        StringBuilder tagStr = new StringBuilder();
        String shortLine = "    - ";
        for (String tag : tags) {
            tagStr.append(shortLine).append(tag).append("\r\n");
        }
        //内容拼接
        content = MessageFormat.format(str, title, subtitle, displayTime, author, headerImg, tagStr.toString(), content);
        //写文件
        Path dsc = Paths.get(fileDesPath + blogFileName);
        Files.write(dsc, content.getBytes("utf-8"));
    }

    @Override
    public String refreshBlog() throws IOException, InterruptedException {
        return ShellUtil.runShell(blogShellPath);
    }


}
