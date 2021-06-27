package com.ye.back.mapper;

import com.ye.back.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(String username);

    List<User> lookPerson(String username, String  roleName);

    List<User> getStudentListByCourseId(Integer courseId);

    List<User> getAllTeachersBySchoolId(Integer schoolId);
}
