package com.ye.back.mapper;

import com.ye.back.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Set<String> selectAllRoleNamesByUsername(String username);
}
