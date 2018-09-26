package com.clawhub.auth.facade;

import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.vo.SearchModel;

import java.util.List;

/**
 * <Description> 用户管理相关接口，查询，锁定，删除 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月06日<br>
 */
public interface UserFacade {
    /**
     * Description: 根据用户名查询用户信息 <br>
     *
     * @param username username
     * @return sys user
     * @author LiZhiming <br>
     * @taskId <br>
     */
    SysUser findByUsername(String username);


    /**
     * 分页查询用户信息
     *
     * @param searchModel 查询模型
     * @param sysUser     用户模型
     * @return 用户及分页信息
     */
    String queryUserByPage(SearchModel searchModel, SysUser sysUser);

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
