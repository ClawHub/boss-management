package com.clawhub.auth.mapper;

import com.clawhub.auth.common.IMapper;
import com.clawhub.auth.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IMapper<SysUser> {

    /**
     * 删除专用批量更新
     *
     * @param sysUser 用户信息
     * @param userIds 用户id列表
     */
    int batchUpdateUserForDel(@Param("sysUser") SysUser sysUser, @Param("userIds") List<String> userIds);
}