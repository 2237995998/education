package com.ye.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.back.entity.Permission;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 通过用户名获取所有权限码
     * @param username
     * @return
     */
    Set<String> getAllPermissionByUsername(String username);
}
