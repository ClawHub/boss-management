package com.clawhub.auth.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_sys_role")
public class SysRole implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 创建人id
     */
    @Column(name = "create_operator_id")
    private String createOperatorId;

    /**
     * 创建人姓名
     */
    @Column(name = "create_operator_name")
    private String createOperatorName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新人id
     */
    @Column(name = "update_operator_id")
    private String updateOperatorId;

    /**
     * 更新人姓名
     */
    @Column(name = "update_operator_name")
    private String updateOperatorName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 0-未删除,1-已删除
     */
    @Column(name = "is_delete")
    private String isDelete;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 0-未锁定,1-已锁定
     */
    private String state;

    /**
     * 角色描述
     */
    private String description;

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
     * 获取创建人id
     *
     * @return create_operator_id - 创建人id
     */
    public String getCreateOperatorId() {
        return createOperatorId;
    }

    /**
     * 设置创建人id
     *
     * @param createOperatorId 创建人id
     */
    public void setCreateOperatorId(String createOperatorId) {
        this.createOperatorId = createOperatorId;
    }

    /**
     * 获取创建人姓名
     *
     * @return create_operator_name - 创建人姓名
     */
    public String getCreateOperatorName() {
        return createOperatorName;
    }

    /**
     * 设置创建人姓名
     *
     * @param createOperatorName 创建人姓名
     */
    public void setCreateOperatorName(String createOperatorName) {
        this.createOperatorName = createOperatorName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人id
     *
     * @return update_operator_id - 更新人id
     */
    public String getUpdateOperatorId() {
        return updateOperatorId;
    }

    /**
     * 设置更新人id
     *
     * @param updateOperatorId 更新人id
     */
    public void setUpdateOperatorId(String updateOperatorId) {
        this.updateOperatorId = updateOperatorId;
    }

    /**
     * 获取更新人姓名
     *
     * @return update_operator_name - 更新人姓名
     */
    public String getUpdateOperatorName() {
        return updateOperatorName;
    }

    /**
     * 设置更新人姓名
     *
     * @param updateOperatorName 更新人姓名
     */
    public void setUpdateOperatorName(String updateOperatorName) {
        this.updateOperatorName = updateOperatorName;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取0-未删除,1-已删除
     *
     * @return is_delete - 0-未删除,1-已删除
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置0-未删除,1-已删除
     *
     * @param isDelete 0-未删除,1-已删除
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取0-未锁定,1-已锁定
     *
     * @return state - 0-未锁定,1-已锁定
     */
    public String getState() {
        return state;
    }

    /**
     * 设置0-未锁定,1-已锁定
     *
     * @param state 0-未锁定,1-已锁定
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取角色描述
     *
     * @return description - 角色描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置角色描述
     *
     * @param description 角色描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}