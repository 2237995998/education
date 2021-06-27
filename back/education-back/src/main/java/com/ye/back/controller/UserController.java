package com.ye.back.controller;


import com.alibaba.fastjson.JSON;
import com.ye.back.entity.User;
import com.ye.back.response.Result;
import com.ye.back.service.UserService;
import com.ye.back.token.JwtToken;
import com.ye.back.util.JwtUtil;
import com.ye.back.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 自定义token登录
     *
     * @return
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return JSON.toJSONString(new Result().setCode(500).setMessage("账号密码不能为空"));
        }
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.createJWT(user.getUsername(), "back", "user", 1000 * 60 * 60 * 24);
        JwtToken jwtToken = new JwtToken(token, user.getPassword());
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            return JSON.toJSONString(new Result().setCode(401).setMessage("账号不存在"));
        } catch (IncorrectCredentialsException e) {
            return JSON.toJSONString(new Result().setCode(402).setMessage("密码错误"));
        }
        User backUser = userService.getUserByUsername(user.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", token);
        return JSON.toJSONString(new Result().setCode(200).setMessage("登录成功").setData(map));
    }

//    @ApiOperation("登录")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@ApiParam("账号") String username, @ApiParam("密码") String password) {
//        System.out.println("登录");
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        try {
//            subject.login(token);
//        } catch (UnknownAccountException e) {
//            return JSON.toJSONString(new Result().setCode(401).setMessage("账号不存在"));
//        } catch (IncorrectCredentialsException e) {
//            return JSON.toJSONString(new Result().setCode(402).setMessage("密码错误"));
//        }
//        User user = userService.getUserByUsername(username);
//        user.setPassword(null);
//        user.setSalt(null);
//        return JSON.toJSONString(new Result().setCode(200).setMessage("登录成功").setData(user));
//    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return new Result().setCode(200).setMessage("成功退出");
    }

    //    @RequiresAuthentication
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public String getMenuList(String username) {
        if (StringUtils.isEmpty(username)) {
            return JSON.toJSONString(new Result().setCode(401).setMessage("未登录,请先登录"));
        }
        List<Map<String, Object>> list = userService.getMenuList(username);
        if (list == null) {
            return JSON.toJSONString(new Result().setCode(500).setMessage("获取错误"));
        } else {
            return JSON.toJSONString(new Result().setCode(200).setMessage("获取成功").setData(list));
        }
    }


    @RequiresPermissions("person:look")
    @RequestMapping(value = "/lookPerson", method = RequestMethod.GET)
    public String getPerson(@Nullable String username, @Nullable String roleName, @Nullable String pageNum, @Nullable String pageSize) {
        System.out.println(25);
        if (StringUtils.isBlank(username)) {
            username = null;
        }
        if (StringUtils.isBlank(roleName)) {
            roleName = null;
        }
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Map<String, Object> list = userService.lookPerson(username, roleName, first, second);
        System.out.println(list);
        return JSON.toJSONString(new Result().setCode(200).setMessage("成功").setData(list));
    }

    @ApiOperation("获取学院所有老师")
    @RequestMapping(value = "/getTeachers", method = RequestMethod.GET)
    public String getTeachersBySchoolId(Integer schoolId) {
        List<User> list = userService.getAllTeachersBySchoolId(schoolId);
        return JSON.toJSONString(new Result().setCode(200).setData(list));
    }

}
