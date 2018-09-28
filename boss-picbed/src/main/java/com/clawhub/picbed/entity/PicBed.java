package com.clawhub.picbed.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_pic_bed")
public class PicBed {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 大图片路径
     */
    private String src;

    /**
     * 标签
     */
    private String tags;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片替换文字
     */
    private String alt;

    /**
     * 小图片路径
     */
    private String msrc;

    /**
     * 源图片宽度
     */
    private Integer w;

    /**
     * 源图片高度
     */
    private Integer h;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取大图片路径
     *
     * @return src - 大图片路径
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置大图片路径
     *
     * @param src 大图片路径
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * 获取标签
     *
     * @return tags - 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置标签
     *
     * @param tags 标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取图片替换文字
     *
     * @return alt - 图片替换文字
     */
    public String getAlt() {
        return alt;
    }

    /**
     * 设置图片替换文字
     *
     * @param alt 图片替换文字
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * 获取小图片路径
     *
     * @return msrc - 小图片路径
     */
    public String getMsrc() {
        return msrc;
    }

    /**
     * 设置小图片路径
     *
     * @param msrc 小图片路径
     */
    public void setMsrc(String msrc) {
        this.msrc = msrc;
    }

    /**
     * 获取源图片宽度
     *
     * @return w - 源图片宽度
     */
    public Integer getW() {
        return w;
    }

    /**
     * 设置源图片宽度
     *
     * @param w 源图片宽度
     */
    public void setW(Integer w) {
        this.w = w;
    }

    /**
     * 获取源图片高度
     *
     * @return h - 源图片高度
     */
    public Integer getH() {
        return h;
    }

    /**
     * 设置源图片高度
     *
     * @param h 源图片高度
     */
    public void setH(Integer h) {
        this.h = h;
    }
}