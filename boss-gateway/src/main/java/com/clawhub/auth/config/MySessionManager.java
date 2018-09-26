package com.clawhub.auth.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * <Description> 自定义sessionId获取  <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
public class MySessionManager extends DefaultWebSessionManager {

    /**
     * The constant AUTHORIZATION.
     */
    private static final String AUTHORIZATION = "Authorization";

    /**
     * The constant REFERENCED_SESSION_ID_SOURCE.
     */
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(MySessionManager.class);

    /**
     * Instantiates a new My session manager.
     */
    public MySessionManager() {
        super();
    }

    /**
     * Description: Get session id <br>
     *
     * @param request  request
     * @param response response
     * @return serializable
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        logger.info("MySessionManager.getSessionId");
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        logger.info("Authorization-id : {}", id);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}