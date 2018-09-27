package com.clawhub.auth.mapper;

import com.clawhub.auth.entity.RoleResource;
import com.clawhub.common.IMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface Role Resource mapper.
 */
public interface RoleResourceMapper extends IMapper<RoleResource> {
    /**
     * Description: 根据角色ID列表获取权限ID列表<br>
     *
     * @param roleIds role ids
     * @return list
     * @author LiZhiming <br>
     * @taskId <br>
     */
    List<String> findResourceIdsByRoleIds(@Param(value = "roleIds") List<String> roleIds);

    /**
     * 新增角色资源关系
     *
     * @param resourceIdsMap  key：ID val:资源ID列表
     * @param roleResource 角色资源信息
     */
    void add(@Param(value = "resourceIdsMap") Map<String, String> resourceIdsMap, @Param(value = "roleResource") RoleResource roleResource);

    /**
     * 通过角色ID列表批量删除角色资源对应关系
     *
     * @param roleIds      角色ID列表
     * @param roleResource 角色资源对应关系
     * @return 删除条数
     */
    int batchUpdateForDelByRoleIds(@Param(value = "roleIds") List<String> roleIds, @Param(value = "roleResource") RoleResource roleResource);

    /**
     * 通过资源ID列表批量删除角色资源对应关系
     *
     * @param resourceIds  资源ID列表
     * @param roleResource 角色资源对应关系
     * @return 删除条数
     */
    int batchUpdateForDelByResourceIds(@Param(value = "resourceIds") List<String> resourceIds, @Param(value = "roleResource") RoleResource roleResource);
}