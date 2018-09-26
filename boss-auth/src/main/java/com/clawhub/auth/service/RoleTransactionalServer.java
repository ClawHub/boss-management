package com.clawhub.auth.service;

import com.clawhub.auth.entity.SysRole;
import com.clawhub.auth.entity.SysUser;

import java.util.List;

/**
 * <Description> 角色事务服务<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/12 <br>
 */
public interface RoleTransactionalServer {
    /**
     * 新增角色
     *
     * @param sysRole     角色信息
     * @param resourceIds 资源ID列表
     */
    void addRole(SysRole sysRole, List<String> resourceIds);

    /**
     * 批量删除
     *
     * @param roleIds     角色ID列表
     * @param currentUser 操作用户
     */
    void batchDelRole(List<String> roleIds, SysUser currentUser);
}
