package com.clawhub.auth.service.impl;

import com.clawhub.auth.entity.SysResource;
import com.clawhub.auth.mapper.RoleResourceMapper;
import com.clawhub.auth.mapper.SysResourceMapper;
import com.clawhub.auth.mapper.UserRoleMapper;
import com.clawhub.auth.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <Description> 资源管理 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    /**
     * The Sys resource mapper.
     */
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * The Role resource mapper.
     */
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * The User role mapper.
     */
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<SysResource> findSysResourcesByUserId(String userId) {
        logger.info("PermissionServiceImpl.findPermissionsByUserId");
        if (StringUtils.isBlank(userId)) {
            return Collections.EMPTY_SET;
        }
        // 根据用户id获取角色ID列表
        List<String> roleIds = userRoleMapper.findRoleIdsByUserId(userId);
        logger.info("roleIds.size :{}", roleIds.size());
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.EMPTY_SET;
        }
        //根据角色ID列表获取资源ID列表
        List<String> permissionIds = roleResourceMapper.findResourceIdsByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Collections.EMPTY_SET;
        }
        logger.info("permissionIds.size :{}", permissionIds.size());
        //根据资源ID列表获取资源列表
        List<SysResource> resources = sysResourceMapper.findResourcesByIds(permissionIds);

        return new HashSet<>(resources);
    }

    @Override
    public List<SysResource> getAllResource() {
        return sysResourceMapper.selectAllResource();
    }

    @Override
    public void add(SysResource sysResource) {
        sysResourceMapper.insert(sysResource);
    }
}