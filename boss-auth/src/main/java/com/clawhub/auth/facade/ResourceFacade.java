package com.clawhub.auth.facade;

import com.clawhub.auth.entity.SysResource;

import java.util.List;
import java.util.Set;

/**
 * <Description> 资源管理相关接口，CURD <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月06日<br>
 */
public interface ResourceFacade {

    /**
     * Description: 根据用户ID获取权限列表 <br>
     *
     * @param userId userId
     * @return list
     * @author LiZhiming <br>
     * @taskId <br>
     */
    Set<SysResource> findSysResourcesByUserId(String userId);

    /**
     * Description: 获取所有资源列表 <br>
     *
     * @return list
     * @author LiZhiming <br>
     * @taskId <br>
     */
    List<SysResource> getAllResource();

    /**
     * Description: 插入资源 <br>
     *
     * @param sysResource sysResource
     * @author LiZhiming <br>
     * @taskId <br>
     */
    void insertResource(SysResource sysResource);
}
