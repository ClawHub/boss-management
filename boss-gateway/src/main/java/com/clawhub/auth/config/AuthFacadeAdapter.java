package com.clawhub.auth.config;

import com.clawhub.auth.entity.SysResource;
import com.clawhub.auth.facade.ResourceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description> 适配器 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月08日<br>
 */
public class AuthFacadeAdapter {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(AuthFacadeAdapter.class);
    /**
     * The Resource facade.
     */
    @Autowired
    private ResourceFacade resourceFacade;


    /**
     * Gets resource facade.
     *
     * @return the resource facade
     */
    public ResourceFacade getResourceFacade() {
        return resourceFacade;
    }


    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<SysResource> resources = resourceFacade.getAllResource();
        //测试：将鉴权关闭
//        filterChainDefinitionMap.put("/**", "anon");

        filterChainDefinitionMap.put("/auth/logout", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
//        for (SysResource resource : resources) {
//            logger.info("=======================权限配置开始================================");
//            logger.info("Size :{}", resources.size());
//            logger.info("Url() :{}", resource.getUrl());
//            logger.info("Permission() :{}", resource.getPermission());
//            filterChainDefinitionMap.put(resource.getUrl(), "perms[" + resource.getPermission() + "]");
//            logger.info("=======================权限配置结束================================");
//        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}