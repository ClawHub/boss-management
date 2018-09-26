package com.clawhub.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.auth.entity.SysUser;
import com.clawhub.auth.mapper.SysUserMapper;
import com.clawhub.auth.service.UserService;
import com.clawhub.auth.vo.SearchModel;
import com.clawhub.constants.ParamConstant;
import com.clawhub.result.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> 用户管理 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月07日<br>
 */
@Service
public class UserServiceImpl implements UserService {


    /**
     * The Sys user mapper.
     */
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        SysUser record = new SysUser();
        record.setUsername(username);
        record.setIsDelete("0");
        return sysUserMapper.selectOne(record);
    }

    @Override
    public String queryUserByPage(SearchModel searchModel, SysUser sysUser) {
        Page<SysUser> page = PageHelper.startPage(searchModel.getPageNumber(), searchModel.getPageSize());
        List<SysUser> list = sysUserMapper.select(sysUser);
        page.getTotal();
        JSONObject pageObject = new JSONObject();
        pageObject.put(ParamConstant.PAGE_ROWS, list);
        pageObject.put(ParamConstant.PAGE_TOTAL, page.getTotal());
        return ResultUtil.getSucc(pageObject, "1000");
    }

    @Override
    public void addUser(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }

    @Override
    public int batchDelUser(SysUser sysUser, List<String> userIds) {
        return sysUserMapper.batchUpdateUserForDel(sysUser, userIds);
    }
}
