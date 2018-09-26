package com.clawhub.auth.config;


import com.clawhub.redis.core.RedisTemplate;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> ShiroConfig <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * The Shiro switch.
     * false:关闭
     * true:开启
     */
    @Value("${shiro.switch}")
    private Boolean shiroSwitch;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * Description: Shir filter <br>
     *
     * @param securityManager security manager
     * @return shiro filter factory bean
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        logger.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/auth/unAuth");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/auth/success");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/unAuthorized");
        //开关控制
        if (!shiroSwitch) {
            return shiroFilterFactoryBean;
        }
        // 权限控制map.
        shiroFilterFactoryBean.setFilterChainDefinitionMap(getAuthFacadeAdapter().loadFilterChainDefinitions());
        return shiroFilterFactoryBean;
    }

    /**
     * Description: Security manager <br>
     *
     * @return security manager
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public SecurityManager securityManager() {
        logger.info("ShiroConfiguration.securityManager()");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * Description: My shiro realm <br>
     *
     * @return my shiro realm
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        logger.info("ShiroConfiguration.myShiroRealm()");
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * Description: 凭证匹配器<br>
     * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *
     * @return hashed credentials matcher
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        logger.info("ShiroConfiguration.hashedCredentialsMatcher()");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * Description: Get auth facade adapter <br>
     *
     * @return auth facade adapter
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public AuthFacadeAdapter getAuthFacadeAdapter() {
        return new AuthFacadeAdapter();
    }


    /**
     * Description: 自定义sessionManager<br>
     *
     * @return session manager
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public SessionManager sessionManager() {
        logger.info("自定义sessionManager: ShiroConfiguration.sessionManager()");
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        //会话超时时间，单位：毫秒
        mySessionManager.setGlobalSessionTimeout(30 * 60 * 1000);
        //监听类
//        List<SessionListener> listeners = new ArrayList<>();
//        listeners.add(new ShiroSessionListener());
//        mySessionManager.setSessionListeners(listeners);
        //定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话,单位：毫秒
//        mySessionManager.setSessionValidationInterval(10 * 60 * 1000);
//        mySessionManager.setSessionValidationSchedulerEnabled(false);
        return mySessionManager;
    }

    /**
     * Description: 配置shiro redisManager
     * 使用的是shiro-redis开源插件<br>
     *
     * @return redis manager
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public IRedisManager redisManager() {
        logger.info("配置shiro :ShiroConfiguration.redisManager()");
        IRedisManager redisManager = RedisManagerFactory.getRedisManager(redisTemplate);
        return redisManager;
    }

    /**
     * Description: cacheManager 缓存 redis实现<br>
     * 使用的是shiro-redis开源插件
     *
     * @return redis cache manager
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public RedisCacheManager cacheManager() {
        logger.info("缓存 redis实现 :ShiroConfiguration.cacheManager()");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * Description: RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件 <br>
     *
     * @return redis session dao
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        logger.info(" shiro sessionDao层的实现 :ShiroConfiguration.redisSessionDAO()");
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Description: 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持 <br>
     *
     * @param securityManager security manager
     * @return authorization attribute source advisor
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        logger.info("开启shiro aop注解支持:ShiroConfiguration.authorizationAttributeSourceAdvisor()");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}