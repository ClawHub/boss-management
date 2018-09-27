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
    private String type;

    /**
     * 描述
     */
    private String desc;

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
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取描述
     *
     * @return desc - 描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}