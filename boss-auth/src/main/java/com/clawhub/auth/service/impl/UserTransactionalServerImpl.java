package com.clawhub.auth.service.impl;

import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.entity.UserRole;
import com.clawhub.auth.service.UserRoleService;
import com.clawhub.auth.service.UserService;
import com.clawhub.auth.service.UserTransactionalServer;
import com.clawhub.constants.StatusConstant;
import com.clawhub.util.IDGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <Description> 用户事务处理<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/12 <br>
 */
@Service
public class UserTransactionalServerImpl implements UserTransactionalServer {
    /**
     * The User service.
     */
    @Autowired
    private UserService userService;
    /**
     * The User role service.
     */
    @Autowired
    private UserRoleService userRoleService;

    @Transactional
    @Override
    public void addUser(SysUser sysUser, List<String> roleIds) {
        userService.addUser(sysUser);
        UserRole userRole = new UserRole();
        userRole.setCreateOperatorId(sysUser.getCreateOperatorId());
        userRole.setCreateOperatorName(sysUser.getCreateOperatorName());
        userRole.setId(IDGenarator.getID());
        userRole.setCreateTime(System.currentTimeMillis());
        userRole.setIsDelete(StatusConstant.UN_DELETED);
        userRole.setUserId(sysUser.getUserId());
        userRoleService.add(roleIds, userRole);
    }

    @Transactional
    @Override
    public void batchDelUser(List<String> userIds, SysUser currentUser) {
        SysUser sysUser = new SysUser();
        sysUser.setUpdateOperatorId(currentUser.getUserId());
        sysUser.setUpdateOperatorName(currentUser.getUsername());
        sysUser.setUpdateTime(System.currentTimeMillis());
        sysUser.setIsDelete(StatusConstant.DELETED);
        userService.batchDelUser(sysUser, userIds);
        UserRole userRole = new UserRole();
        userRole.setUpdateOperatorId(currentUser.getUserId());
        userRole.setUpdateOperatorName(currentUser.getUsername());
        userRole.setUpdateTime(System.currentTimeMillis());
        userRole.setIsDelete(StatusConstant.DELETED);
        userRoleService.batchDelByUserIds(userRole, userIds);
    }
}
