package com.clawhub.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.facade.TokenFacade;
import com.clawhub.redis.core.RedisTemplate;
import com.clawhub.result.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

/**
 * <Description> <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018 -09-10 <br>
 */
@Service
public class AuthService {

    /**
     * Login string.
     *
     * @param userInfo the user info
     * @return the string
     */
    public String login(SysUser userInfo) {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        JSONObject jsonObject = new JSONObject();
        //当前用户信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            //登录成功,获取token
            Serializable token = subject.getSession().getId();
            //用户，角色，权限资源入redis
            saveTokenInfo(username, token);
            //返回给前端
            jsonObject.put("token", token);
            return ResultUtil.getSucc(jsonObject, "10000");
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.getError("10001");
        } catch (LockedAccountException e) {
            return ResultUtil.getError("10002");
        } catch (AuthenticationException e) {
            return ResultUtil.getError("10003");
        } catch (Exception e) {
            return ResultUtil.getError("10004");
        }
    }

    /**
     * Logout string.
     *
     * @return the string
     */
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //判断是否登陆
        if (subject.getPrincipal() != null) {
            //清除token相关
            Serializable token = subject.getSession().getId();
            delTokenInfo(token);
            //logout
            subject.logout();
            return ResultUtil.getSucc("10007");
        }
        //未登陆
        return ResultUtil.getError("10005");
    }

    /**
     * The Redis template.
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * The Token facade.
     */
    @Autowired
    private TokenFacade tokenFacade;


    /**
     * Save token info.
     *
     * @param username the username
     * @param token    the token
     */
    private void saveTokenInfo(String username, Serializable token) {
        String infoKey = "info:" + token;
        Map<String, Object> info = tokenFacade.getInfo(username);
        redisTemplate.set(infoKey, JSONObject.toJSONString(info), "NX", "EX", 30 * 60L);
    }

    /**
     * Del token info.
     *
     * @param token the token
     */
    private void delTokenInfo(Serializable token) {
        String infoKey = "info:" + token;
        redisTemplate.del(infoKey);
    }

    /**
     * Gets info.
     *
     * @return the info
     */
    public JSONObject getInfo() {
        String infoKey = "info:" + SecurityUtils.getSubject().getSession().getId();
        return JSONObject.parseObject(redisTemplate.get(infoKey));
    }

}
