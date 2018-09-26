package com.clawhub.auth.service;

import com.clawhub.auth.entity.SysUser;

import java.util.List;

/**
 * <Description> 用户事务服务<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/12 <br>
 */
public interface UserTransactionalServer {

    /**
     * 增加用户
     *
     * @param sysUser 用户信息
     * @param roleIds 角色ID列表
     */
    void addUser(SysUser sysUser, List<String> roleIds);

    /**
     * 批量删除
     *
     * @param userIds     用户ID列表
     * @param currentUser 操作用户
     */
    void batchDelUser(List<String> userIds, SysUser currentUser);
}
