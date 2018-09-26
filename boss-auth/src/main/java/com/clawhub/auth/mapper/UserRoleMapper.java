package com.clawhub.auth.mapper;

import com.clawhub.auth.common.IMapper;
import com.clawhub.auth.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface User role mapper.
 */
public interface UserRoleMapper extends IMapper<UserRole> {
    /**
     * Description: 根据用户id获取角色ID列表 <br>
     *
     * @param userId user id
     * @return list
     * @author LiZhiming <br>
     * @taskId <br>
     */
    List<String> findRoleIdsByUserId(@Param(value = "userId") String userId);

    /**
     * 新增角色与用户对应关系
     *
     * @param userRole   用户信息
     * @param roleIdsMap key:id  val:角色列表
     */
    void add(@Param(value = "userRole") UserRole userRole, @Param(value = "roleIdsMap") Map<String, String> roleIdsMap);

    /**
     * 通过userIds批量删除角色用户关系
     *
     * @param userRole 角色用户关系
     * @param userIds  用户列表
     * @return 删除条数
     */
    int batchUpdateForDelByUserIds(@Param("userRole") UserRole userRole, @Param("userIds") List<String> userIds);

    /**
     * 通过roleIds批量删除角色用户关系
     *
     * @param userRole 角色用户关系
     * @param roleIds  角色列表
     * @return 删除条数
     */
    int batchUpdateForDelByRoleIds(@Param("userRole") UserRole userRole, @Param("roleIds") List<String> roleIds);
}