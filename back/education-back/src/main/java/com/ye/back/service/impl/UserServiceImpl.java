package com.ye.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.fxml.builder.JavaFXFontBuilder;
import com.ye.back.entity.ChooseCourse;
import com.ye.back.entity.Permission;
import com.ye.back.entity.User;
import com.ye.back.mapper.ChooseCourseMapper;
import com.ye.back.mapper.PermissionMapper;
import com.ye.back.mapper.UserMapper;
import com.ye.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ChooseCourseMapper chooseCourseMapper;

    @Override
    public User getUserByUsername(String usernmae) {
        return userMapper.getUserByUsername(usernmae);
    }

    @Override
    public List<Map<String, Object>> getMenuList(String username) {
        List<Map<String, Object>> maps = new ArrayList<>();
        List<Permission> list = permissionMapper.selectFatherPermissionByUsername(username);
        for (Permission s : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("menu", s);
            List<Permission> subList = permissionMapper.selectSubPermissionByFatherId(s.getPermissionId());
            map.put("subMenu", subList);
            maps.add(map);
        }
        return maps;
    }

    @Override
    public Map<String, Object> lookPerson(String username, String roleName, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 4 : pageSize;
        PageHelper.startPage(first, second);
        List<User> userList = userMapper.lookPerson(username, roleName);
        Map<String, Object> map = new HashMap<>();
        map.put("list", userList);
        PageInfo pageInfo = new PageInfo(userList);
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public List<User> getStudentListByCourseId(Integer courseId) {
        return userMapper.getStudentListByCourseId(courseId);
    }

    @Override
    public List<User> getAllTeachersBySchoolId(Integer schoolId) {
        return userMapper.getAllTeachersBySchoolId(schoolId);
    }

    @Override
    public List<Map<String, Object>> getStudentsGradeByCourseId(Integer courseId) {
        List<Map<String, Object>> maps = new ArrayList<>();
        List<User> users = userMapper.getStudentListByCourseId(courseId);
        for (User user : users) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id", user.getUserId());
            queryWrapper.eq("course_id", courseId);
            ChooseCourse chooseCourse = chooseCourseMapper.selectOne(queryWrapper);
            Map<String, Object> map = new HashMap<>();
            map.put("student", user);
            map.put("grade", chooseCourse);
            maps.add(map);
        }
        return maps;
    }

}
