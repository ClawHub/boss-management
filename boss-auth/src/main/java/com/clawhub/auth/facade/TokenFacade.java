package com.clawhub.auth.facade;

import java.util.Map;

/**
 * <Description> Token接口暴露<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/8 <br>
 */
public interface TokenFacade {
    /**
     * 根据用户名获取用户，角色，菜单，按钮等资源信息
     *
     * @param username 用户名
     * @return 信息
     */
    Map<String, Object> getInfo(String username);
}
