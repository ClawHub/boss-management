package com.clawhub.auth.service;

import com.clawhub.auth.entity.RoleResource;

import java.util.List;

/**
 * <Description> 角色资源服务接口<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/12 <br>
 */
public interface RoleResourceService {
    /**
     * 新增角色资源关系
     *
     * @param resourceIds  资源ID列表
     * @param roleResource 角色资源信息
     */
    void add(List<String> resourceIds, RoleResource roleResource);

    /**
     * 根据角色ID列表删除角色资源对应关系
     *
     * @param roleIds      角色ID列表
     * @param roleResource 角色资源对应关系
     * @return 删除数量
     */
    int batchDelByRoleIds(List<String> roleIds, RoleResource roleResource);

    /**
     * 根据资源ID列表删除角色资源对应关系
     *
     * @param resourceIds  资源ID列表
     * @param roleResource 角色资源对应关系
     * @return 删除数量
     */
    int batchDelByResourceIds(List<String> resourceIds, RoleResource roleResource);
}
