package com.clawhub.auth.config;

import com.clawhub.auth.entity.SysResource;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.facade.ResourceFacade;
import com.clawhub.auth.facade.RoleFacade;
import com.clawhub.auth.facade.UserFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <Description> 自定义权限匹配和账号密码匹配  <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    /**
     * The User facade.
     */
    @Autowired
    private UserFacade userFacade;

    /**
     * The Role Facade.
     */
    @Autowired
    private RoleFacade roleFacade;

    /**
     * The Resource facade.
     */
    @Autowired
    private ResourceFacade resourceFacade;

    /**
     * Description: Do get authorization info <br>
     *
     * @param principals principals
     * @return authorization info
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取用户信息
        SysUser userInfo = (SysUser) principals.getPrimaryPrincipal();
        logger.info("userInfo :{}" + userInfo);
        if (userInfo == null) {
            return null;
        }
        //根据用户信息获取角色名称列表
        Set<String> roles = roleFacade.findRoleNamesByUserId(userInfo.getUserId());
        logger.info("角色名称列表：{}", roles);
        authorizationInfo.addRoles(roles);

        //根据用户信息获取权限列表
        Set<SysResource> resources = resourceFacade.findSysResourcesByUserId(userInfo.getUserId());
        Set<String> permissions = new HashSet<>();
        if (!CollectionUtils.isEmpty(resources)) {
            for (SysResource sysResource : resources) {
                permissions.add(sysResource.getPermission());
            }
        }
        logger.info("权限列表：{}", permissions);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * Description: 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确<br>
     *
     * @param token token
     * @return authentication info
     * @throws AuthenticationException authentication exception
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        logger.info("----->>username :{}", username);
        logger.info("----->>Credentials :{}", token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser userInfo = userFacade.findByUsername(username);
        logger.info("----->>userInfo :{}", userInfo);
        if (userInfo == null) {
            return null;
        }
        //账户冻结
        if (userInfo.getState().equals("1")) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /**
     * Get authorization info authorization info.
     *
     * @return the authorization info
     */
    public AuthorizationInfo getAuthorizationInfo() {
        return getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}