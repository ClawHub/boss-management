package com.clawhub.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.auth.entity.SysRole;
import com.clawhub.auth.mapper.SysRoleMapper;
import com.clawhub.auth.mapper.UserRoleMapper;
import com.clawhub.auth.service.RoleService;
import com.clawhub.auth.vo.SearchModel;
import com.clawhub.constants.ParamConstant;
import com.clawhub.result.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
 * <Description> 角色管理 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    /**
     * The Sys role mapper.
     */
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * The User role mapper.
     */
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * Description: Find roles by userId <br>
     *
     * @param userId userId
     * @return list
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Override
    public Set<String> findRoleNamesByUserId(String userId) {
        logger.info("RoleServiceImpl.findRoleNamesByUserId");
        if (StringUtils.isBlank(userId)) {
            return Collections.emptySet();
        }
        logger.info("userId :{}", userId);
        //根据用户id获取角色ID列表
        List<String> roleIds = userRoleMapper.findRoleIdsByUserId(userId);

        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        logger.info("roleIds.size :{}", roleIds.size());
        //根据角色ID列表获取角色名称列表
        List<String> roleNames = sysRoleMapper.findRoleNamesByRoleIds(roleIds);
        return new HashSet<>(roleNames);
    }

    @Override
    public String queryRoleByPage(SearchModel searchModel, SysRole sysRole) {
        Page<SysRole> page = PageHelper.startPage(searchModel.getPageNumber(), searchModel.getPageSize());
        List<SysRole> list = sysRoleMapper.select(sysRole);
        page.getTotal();
        JSONObject pageObject = new JSONObject();
        pageObject.put(ParamConstant.PAGE_ROWS, list);
        pageObject.put(ParamConstant.PAGE_TOTAL, page.getTotal());
        return ResultUtil.getSucc(pageObject, "1000");
    }

    @Override
    public void addRole(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public int batchDel(List<String> roleIds, SysRole sysRole) {
        return sysRoleMapper.batchUpdateRoleForDel(roleIds, sysRole);
    }

}