package com.clawhub.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.service.AuthService;
import com.clawhub.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> 权限认证管理<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    /**
     * The Auth service.
     */
    @Autowired
    private AuthService authService;

    /**
     * Description: 登录方法 <br>
     *
     * @param param param
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String param) {
        SysUser userInfo = JSONObject.parseObject(param, SysUser.class);
        return authService.login(userInfo);
    }


    /**
     * Description: 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面 <br>
     *
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @RequestMapping(value = "/unAuth")
    public String unauth() {
        return ResultUtil.getError("10005");
    }

    /**
     * Description: 未授权 <br>
     *
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @RequestMapping(value = "/unAuthorized")
    public String unAuthorized() {
        return ResultUtil.getError("10006");
    }

    /**
     * Description: 登出 <br>
     *
     * @return string
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        return authService.logout();
    }


    /**
     * Gets info.
     *
     * @return the info
     */
    @RequestMapping(value = "/getInfo")
    public String getInfo() {
        return ResultUtil.getSucc(authService.getInfo(), "1000");
    }

}