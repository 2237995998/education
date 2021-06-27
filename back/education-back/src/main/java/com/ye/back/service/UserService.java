package com.ye.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.back.entity.User;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
public interface UserService extends IService<User> {

    User getUserByUsername(String usernmae);

    List<Map<String, Object>>  getMenuList(String username);

    Map<String, Object> lookPerson(String username, String roleName, Integer pageNum, Integer pageSize);

    List<User> getStudentListByCourseId(Integer courseId);

    List<User> getAllTeachersBySchoolId(Integer schoolId);

    List<Map<String, Object>> getStudentsGradeByCourseId(Integer courseId);
}
