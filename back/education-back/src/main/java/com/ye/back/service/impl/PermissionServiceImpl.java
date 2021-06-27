package com.ye.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ye.back.entity.Permission;
import com.ye.back.mapper.PermissionMapper;
import com.ye.back.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements com.ye.back.service.PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Set<String> getAllPermissionByUsername(String username) {
        return permissionMapper.selectAllPermissionCodeByUsername(username);
    }
}
