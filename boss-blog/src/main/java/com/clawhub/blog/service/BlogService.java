package com.clawhub.blog.service;

import java.io.IOException;
import java.util.List;

/**
 * <Description> 博客<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/30 <br>
 */
public interface BlogService {
    /**
     * 文章提交
     *
     * @param author      作者
     * @param title       标题
     * @param subtitle    副标题
     * @param tags        标签
     * @param content     文本
     * @param headerImg   标题图片
     * @param displayTime 发布时间
     */
    void submit(String author, String title, String subtitle, List<String> tags, String content, String headerImg, String displayTime) throws IOException;

    /**
     * git 刷新
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    String refreshBlog() throws IOException, InterruptedException;
}
