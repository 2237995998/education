package com.ye.back.mapper;

import com.ye.back.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
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
public interface PermissionMapper extends BaseMapper<Permission> {
    Set<String> selectAllPermissionCodeByUsername(String username);

    List<Permission> selectFatherPermissionByUsername(String username);

    List<Permission> selectSubPermissionByFatherId(int fatherId);

}
