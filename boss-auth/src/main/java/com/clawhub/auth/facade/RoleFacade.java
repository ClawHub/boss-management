package com.clawhub.auth.facade;

import com.clawhub.auth.entity.SysRole;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.vo.SearchModel;

import java.util.List;
import java.util.Set;

/**
 * <Description> 角色管理相关接口，CURD <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月06日<br>
 */
public interface RoleFacade {
    /**
     * Description: 通过用户ID获取角色名称列表 <br>
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
