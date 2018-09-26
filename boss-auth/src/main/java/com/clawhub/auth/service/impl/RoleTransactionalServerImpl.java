package com.clawhub.auth.service.impl;

import com.clawhub.auth.entity.RoleResource;
import com.clawhub.auth.entity.SysRole;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.entity.UserRole;
import com.clawhub.auth.service.RoleResourceService;
import com.clawhub.auth.service.RoleService;
import com.clawhub.auth.service.RoleTransactionalServer;
import com.clawhub.auth.service.UserRoleService;
import com.clawhub.constants.StatusConstant;
import com.clawhub.util.IDGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <Description> 角色事务服务<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/12 <br>
 */
@Service
public class RoleTransactionalServerImpl implements RoleTransactionalServer {
    /**
     * The Role service.
     */
    @Autowired
    private RoleService roleService;
    /**
     * The Role resource service.
     */
    @Autowired
    private RoleResourceService roleResourceService;
    /**
     * The User role service.
     */
    @Autowired
    private UserRoleService userRoleService;

    @Transactional
    @Override
    public void addRole(SysRole sysRole, List<String> resourceIds) {
        roleService.addRole(sysRole);
        RoleResource roleResource = new RoleResource();
        roleResource.setCreateOperatorId(sysRole.getCreateOperatorId());
        roleResource.setCreateOperatorName(sysRole.getCreateOperatorName());
        roleResource.setCreateTime(System.currentTimeMillis());
        roleResource.setIsDelete(StatusConstant.UN_DELETED);
        roleResource.setId(IDGenarator.getID());
        roleResource.setRoleId(sysRole.getRoleId());
        roleResourceService.add(resourceIds, roleResource);
    }

    @Transactional
    @Override
    public void batchDelRole(List<String> roleIds, SysUser currentUser) {
        //删除role
        SysRole sysRole = new SysRole();
        sysRole.setUpdateOperatorId(currentUser.getUserId());
        sysRole.setUpdateOperatorName(currentUser.getUsername());
        sysRole.setUpdateTime(System.currentTimeMillis());
        sysRole.setIsDelete(StatusConstant.DELETED);
        roleService.batchDel(roleIds, sysRole);
        //删除role-resource对应关系
        RoleResource roleResource = new RoleResource();
        roleResource.setUpdateOperatorId(currentUser.getUserId());
        roleResource.setUpdateOperatorName(currentUser.getUsername());
        roleResource.setUpdateTime(System.currentTimeMillis());
        roleResource.setIsDelete(StatusConstant.DELETED);
        roleResourceService.batchDelByRoleIds(roleIds, roleResource);
        //删除role-user对应关系
        UserRole userRole = new UserRole();
        userRole.setUpdateOperatorId(currentUser.getUserId());
        userRole.setUpdateOperatorName(currentUser.getUsername());
        userRole.setUpdateTime(System.currentTimeMillis());
        userRole.setIsDelete(StatusConstant.DELETED);
        userRoleService.batchDelByRoleIds(userRole, roleIds);
    }
}
