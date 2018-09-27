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
     * 图片路径
     */
    private String src;

    /**
     * 类型
     */
    private String classify;

    /**
     * 描述
     */
    private String remark;

    /**
     * 姓名
     */
    private String name;

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
     * 获取图片路径
     *
     * @return src - 图片路径
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置图片路径
     *
     * @param src 图片路径
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * 获取类型
     *
     * @return classify - 类型
     */
    public String getClassify() {
        return classify;
    }

    /**
     * 设置类型
     *
     * @param classify 类型
     */
    public void setClassify(String classify) {
        this.classify = classify;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}