package com.ye.back.controller;


import com.alibaba.fastjson.JSON;
import com.ye.back.entity.ChooseCourse;
import com.ye.back.entity.Course;
import com.ye.back.entity.User;
import com.ye.back.response.Result;
import com.ye.back.service.CourseService;
import com.ye.back.service.UserService;
import com.ye.back.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import javafx.geometry.Pos;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 何进业
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @ApiOperation("获取可选课程列表")
    @RequiresPermissions("course:choose")
    @RequestMapping(value = "/showThis", method = RequestMethod.GET)
    public String getThisCourses(String userId, @Nullable String pageNum, @Nullable String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Integer id = Integer.valueOf(userId);
        Map<String, Object> map = courseService.getThisCourses(id, first, second);
        if ((long)(map.get("total")) == 0){
            return JSON.toJSONString(new Result().setCode(200).setMessage("当前无可选课程，或者不在选课时间"));
        }
        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("查看选课结果")
    @RequiresPermissions("course:result")
    @RequestMapping(value = "/showResult", method = RequestMethod.GET)
    public String getChooseCourseResult( String userId, @Nullable String pageNum, @Nullable String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Integer id = Integer.valueOf(userId);
        Map<String, Object> map = courseService.getChooseCourseResult(id, first, second);

        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("退课")
    @RequiresPermissions("course:drop")
    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    public String deleteChooseCourse(@RequestBody ChooseCourse chooseCourse){
        int result = courseService.deleteChooseCourse(chooseCourse.getUserId(), chooseCourse.getCourseId());
        if (result == 1){
            return JSON.toJSONString(new Result().setCode(200).setMessage("退课成功"));
        }else {
            return JSON.toJSONString(new Result().setCode(500).setMessage("服务器异常,退课失败"));
        }
    }

    @ApiOperation("选课")
    @RequiresPermissions("course:choose")
    @RequestMapping(value = "choose", method = RequestMethod.POST)
    public String chooseCourse(@RequestBody ChooseCourse chooseCourse){
        System.out.println(chooseCourse);
        if (courseService.isContradict(chooseCourse.getUserId(), chooseCourse.getCourseId())){
            return JSON.toJSONString(new Result().setCode(500).setMessage("选课冲突,该时间段存在已选课程"));
        }
        int result = courseService.insertChooseCourse(chooseCourse.getUserId(), chooseCourse.getCourseId());
        if (result == 1){
            return JSON.toJSONString(new Result().setCode(200).setMessage("选课成功"));
        }else {
            return JSON.toJSONString(new Result().setCode(500).setMessage("服务器异常,选课失败"));
        }
    }

    @ApiOperation("查看历史课程")
    @RequiresPermissions("course:history")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String getHistoryCourses(Integer userId, Integer term, @Nullable String pageNum, @Nullable String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Map<String, Object> map = courseService.getHistoryCourses(userId, term, first, second);
        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("查看本学期开课")
    @RequiresPermissions("course_start:this")
    @RequestMapping(value = "/showThisStart", method = RequestMethod.GET)
    public String getThisStartCourses(Integer userId, @Nullable String pageNum, @Nullable String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Map<String, Object> map = courseService.getThisStartCourses(userId, first, second);
        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("查看课程学生名单")
    @RequiresPermissions("showCourseStudent")
    @RequestMapping(value = "/showStudentList", method = RequestMethod.GET)
    public String showStudentList(Integer courseId){
        List<User> users = userService.getStudentListByCourseId(courseId);
        Map<String, Object>  map = new HashMap<>();
        Course course = courseService.getById(courseId);
        User teacher = userService.getById(course.getTeacherId());
        map.put("list", users);
        map.put("course", course);
        map.put("teacher", teacher);
        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("查看历史开课")
    @RequiresPermissions("course_start:history")
    @RequestMapping(value = "/showHistoryStart", method = RequestMethod.GET)
    public String getHistoryStartCourses(Integer userId, Integer term, @Nullable String pageNum, @Nullable String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Map<String, Object> map = courseService.getHistoryStartCourses(userId, term, first, second);
        System.out.println(map);
        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("查看学院所有开课")
    @RequiresPermissions("course_manage:look")
    @RequestMapping(value = "/showAllStart", method = RequestMethod.GET)
    public String getAllStartCourses(Integer schoolId, Integer term, @Nullable String pageNum, @Nullable String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Map<String, Object> map = courseService.getALLStartCourses(schoolId, term, first, second);
        return JSON.toJSONString(new Result().setCode(200).setData(map));
    }

    @ApiOperation("删除课程")
    @RequiresPermissions("course_manage:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String deleteCourse(Integer courseId){
        courseService.removeById(courseId);
        return JSON.toJSONString(new Result().setCode(200).setMessage("删除成功"));
    }

    @ApiOperation("添加课程")
    @RequiresPermissions("course_manage:add")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addCourse(@RequestBody Course course){
        courseService.saveOrUpdate(course);
        return JSON.toJSONString(new Result().setCode(200).setMessage("添加成功"));
    }

    @ApiOperation("修改课程")
    @RequiresPermissions("course_manage:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCourse(@RequestBody  Course course){
        courseService.saveOrUpdate(course);
        return JSON.toJSONString(new Result().setCode(200).setMessage("修改成功"));
    }

    @ApiOperation("开放选课")
    @RequiresPermissions("choose_manage:open")
    @RequestMapping(value = "/open",method = RequestMethod.GET)
    public String openChooseCourse(){
        courseService.openChooseCourse();
        return JSON.toJSONString(new Result().setCode(200).setMessage("开启成功"));
    }

    @ApiOperation("关闭选课")
    @RequiresPermissions("choose_manage:close")
    @RequestMapping(value = "/close",method = RequestMethod.GET)
    public String closeChooseCourse(){
        courseService.closeChooseCourse();
        return JSON.toJSONString(new Result().setCode(200).setMessage("关闭成功"));
    }

}
