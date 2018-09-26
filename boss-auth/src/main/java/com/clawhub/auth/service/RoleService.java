package com.clawhub.auth.service;

import com.clawhub.auth.entity.SysRole;
import com.clawhub.auth.vo.SearchModel;

import java.util.List;
import java.util.Set;

/**
 * <Description> 角色管理接口 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月06日<br>
 */
public interface RoleService {
    /**
     * Description: Find roles by user <br>
     *
     * @param userId userId
     * @return list
     * @author LiZhiming <br>
     * @taskId <br>
     */
    Set<String> findRoleNamesByUserId(String userId);

    /**
     * 分页查询角色
     *
     * @param searchModel 查询模型
     * @param sysRole     角色
     * @return 角色信息
     */
    String queryRoleByPage(SearchModel searchModel, SysRole sysRole);

    /**
     * 新增角色
     *
     * @param sysRole 角色信息
     */
    void addRole(SysRole sysRole);

    /**
     * 批量删除角色
     *
     * @param roleIds 角色ID列表
     * @param sysRole 角色信息
     * @return 删除数量
     */
    int batchDel(List<String> roleIds, SysRole sysRole);
}
