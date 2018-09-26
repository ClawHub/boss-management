package com.clawhub.auth.facade.impl;

import com.clawhub.auth.entity.SysRole;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.facade.RoleFacade;
import com.clawhub.auth.service.RoleService;
import com.clawhub.auth.service.RoleTransactionalServer;
import com.clawhub.auth.vo.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <Description> 角色接口实现 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月08日<br>
 */
@Service
public class RoleFacadeImpl implements RoleFacade {

    /**
     * The Role service.
     */
    @Autowired
    private RoleService roleService;
    /**
     * The Role transactional server.
     */
    @Autowired
    private RoleTransactionalServer roleTransactionalServer;

    @Override
    public Set<String> findRoleNamesByUserId(String userId) {
        return roleService.findRoleNamesByUserId(userId);
    }

    @Override
    public String queryRoleByPage(SearchModel searchModel, SysRole sysRole) {
        return roleService.queryRoleByPage(searchModel, sysRole);
    }

    @Override
    public void addRole(SysRole sysRole, List<String> resourceIds) {
        roleTransactionalServer.addRole(sysRole, resourceIds);
    }

    @Override
    public void batchDelRole(List<String> roleIds, SysUser currentUser) {
        roleTransactionalServer.batchDelRole(roleIds, currentUser);
    }
}