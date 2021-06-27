package com.ye.back;

import com.ye.back.mapper.CourseMapper;
import com.ye.back.mapper.PermissionMapper;
import com.ye.back.mapper.TermMapper;
import com.ye.back.service.RoleService;
import com.ye.back.service.UserService;
import com.ye.back.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BackApplication.class)
class BackApplicationTests {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TermMapper termMapper;

    @Test
    void contextLoads() {
        System.out.println(StringUtil.md5("1234"+"wcwad12"));
    }

    @Test
    void testMapper(){
        System.out.println(courseMapper.getThisStartCourses(6));
    }

    @Test
    void testService(){
//        System.out.println(roleService.getAllRoleNamesByUsername("zhangsan"));
        System.out.println(userService.getMenuList("zhangsan"));
    }

}
