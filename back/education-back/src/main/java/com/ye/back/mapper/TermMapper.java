package com.ye.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ye.back.entity.Role;
import com.ye.back.entity.Term;
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
public interface TermMapper extends BaseMapper<Term> {

    List<Term> getAllTerm();
}
