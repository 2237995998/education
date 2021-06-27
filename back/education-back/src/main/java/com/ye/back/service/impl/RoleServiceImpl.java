package com.ye.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ye.back.entity.Role;
import com.ye.back.mapper.RoleMapper;
import com.ye.back.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getAllRoleNamesByUsername(String username) {
        return roleMapper.selectAllRoleNamesByUsername(username);
    }
}
