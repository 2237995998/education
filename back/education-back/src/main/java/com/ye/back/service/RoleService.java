package com.ye.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.back.entity.Role;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
public interface RoleService extends IService<Role> {
    /**
     * 通过用户名获取所有角色名
     * @param username
     * @return
     */
    Set<String> getAllRoleNamesByUsername(String username);
}
