package com.clawhub.auth.facade.impl;

import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.facade.UserFacade;
import com.clawhub.auth.service.UserService;
import com.clawhub.auth.service.UserTransactionalServer;
import com.clawhub.auth.vo.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> 用户管理相关接口实现 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月08日<br>
 */
@Service
public class UserFacadeImpl implements UserFacade {

    /**
     * The User service.
     */
    @Autowired
    private UserService userService;
    /**
     * The User transactional server.
     */
    @Autowired
    private UserTransactionalServer userTransactionalServer;

    @Override
    public SysUser findByUsername(String username) {
        return userService.findByUsername(username);
    }


    @Override
    public String queryUserByPage(SearchModel searchModel, SysUser sysUser) {
        return userService.queryUserByPage(searchModel, sysUser);
    }

    @Override
    public void addUser(SysUser sysUser, List<String> roleIds) {
        userTransactionalServer.addUser(sysUser, roleIds);
    }

    @Override
    public void batchDelUser(List<String> userIds, SysUser currentUser) {
        userTransactionalServer.batchDelUser(userIds, currentUser);
    }
}