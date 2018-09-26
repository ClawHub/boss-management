package com.clawhub.auth.facade.impl;

import com.clawhub.auth.entity.SysResource;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.facade.TokenFacade;
import com.clawhub.auth.service.ResourceService;
import com.clawhub.auth.service.RoleService;
import com.clawhub.auth.service.UserService;
import com.clawhub.constants.StatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <Description> Token接口暴露<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/8 <br>
 */
@Service
public class TokenFacadeImpl implements TokenFacade {
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(TokenFacadeImpl.class);
    /**
     * The User service.
     */
    @Autowired
    private UserService userService;
    /**
     * The Role service.
     */
    @Autowired
    private RoleService roleService;
    /**
     * The Resource service.
     */
    @Autowired
    private ResourceService resourceService;

    @Override
    public Map<String, Object> getInfo(String username) {
        logger.info("TokenFacadeImpl getInfo username:{}", username);
        SysUser sysUser = userService.findByUsername(username);
        Set<String> roleNames = roleService.findRoleNamesByUserId(sysUser.getUserId());
        Set<SysResource> sysResources = resourceService.findSysResourcesByUserId(sysUser.getUserId());
        Set<String> buttons = new HashSet<>();
        Set<String> menus = new HashSet<>();
        for (SysResource sysResource : sysResources) {
            //button例子：role:create,role:update,role:delete,role:view
            if (StatusConstant.RESOURCE_TYPE_BUTTON.equals(sysResource.getResourceType())) {
                buttons.add(sysResource.getPermission());
            }
            //menu例子：role:*
            if (StatusConstant.RESOURCE_TYPE_MENU.equals(sysResource.getResourceType())) {
                menus.add(sysResource.getPermission());
            }
        }
        Map<String, Object> body = new HashMap<>();
        body.put("user", sysUser);
        body.put("roles", roleNames);
        body.put("buttons", buttons);
        body.put("menus", menus);
        return body;
    }
}
