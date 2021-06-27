package com.ye.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ye.back.entity.*;
import com.ye.back.mapper.*;
import com.ye.back.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2021-06-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private ChooseCourseMapper chooseCourseMapper;
    @Autowired
    private TermMapper termMapper;

    @Override
    public Map<String, Object> getThisCourses(Integer userId, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        List<Course> courses = courseMapper.getThisCourses(userId);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            User teacher = userMapper.selectById(course.getTeacherId());
            School school = schoolMapper.selectById(course.getSchoolId());
            map1.put("teacher", teacher);
            map1.put("course", course);
            map1.put("school", school);
            list.add(map1);
        }
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> getChooseCourseResult(Integer userId, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        List<Course> courses = courseMapper.getChooseCourseResult(userId);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            User teacher = userMapper.selectById(course.getTeacherId());
            School school = schoolMapper.selectById(course.getSchoolId());
            map1.put("teacher", teacher);
            map1.put("course", course);
            map1.put("school", school);
            list.add(map1);
        }
        map.put("list", list);
        return map;
    }

    @Override
    public int deleteChooseCourse(Integer userId, Integer courseId) {
        return chooseCourseMapper.deleteChooseCourse(userId, courseId);
    }

    @Override
    public int insertChooseCourse(Integer userId, Integer courseId) {
        return chooseCourseMapper.insertChooseCourse(userId, courseId);
    }

    @Override
    public boolean isContradict(Integer userId, Integer courseId) {
        List<Course> courses = courseMapper.getChooseCourseResult(userId);
        for (Course course : courses) {
            Course course1 = courseMapper.selectById(courseId);
            if (course.getCourseTime().equals(course1.getCourseTime())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getHistoryCourses(Integer userId, Integer term, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        if (term == 0) {
            term = null;
        }
        List<Course> courses = courseMapper.getHistoryCourses(userId, term);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            User teacher = userMapper.selectById(course.getTeacherId());
            School school = schoolMapper.selectById(course.getSchoolId());
            map1.put("teacher", teacher);
            map1.put("course", course);
            map1.put("school", school);
            list.add(map1);
        }
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> getThisStartCourses(Integer userId, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        List<Course> courses = courseMapper.getThisStartCourses(userId);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            User teacher = userMapper.selectById(course.getTeacherId());
            School school = schoolMapper.selectById(course.getSchoolId());
            map1.put("teacher", teacher);
            map1.put("course", course);
            map1.put("school", school);
            list.add(map1);
        }
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> getHistoryStartCourses(Integer userId, Integer term, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        if (term == 0) {
            term = null;
        }
        List<Course> courses = courseMapper.getHistoryStartCourses(userId, term);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            User teacher = userMapper.selectById(course.getTeacherId());
            School school = schoolMapper.selectById(course.getSchoolId());
            map1.put("teacher", teacher);
            map1.put("course", course);
            map1.put("school", school);
            list.add(map1);
        }
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> getALLStartCourses(Integer schoolId, Integer term, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        if (term == 0) {
            term = null;
        }
        List<Course> courses = courseMapper.getAllStartCourses(schoolId, term);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            User teacher = userMapper.selectById(course.getTeacherId());
            School school = schoolMapper.selectById(course.getSchoolId());
            map1.put("teacher", teacher);
            map1.put("course", course);
            map1.put("school", school);
            list.add(map1);
        }
        map.put("list", list);
        return map;
    }

    @Override
    public void openChooseCourse() {
        List<Course> courses = getAllThisStartCourses();
        for (Course course : courses) {
            course.setCourseStatus(1);
            courseMapper.updateById(course);
        }
    }

    @Override
    public void closeChooseCourse() {
        List<Course> courses = courseMapper.selectList(null);
        for (Course course : courses) {
            course.setCourseStatus(0);
            courseMapper.updateById(course);
        }
    }

    @Override
    public Map<String, Object> getThisGrade(Integer userId, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        List<Course> courses = courseMapper.getThisCoursesHasGrade(userId);
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            ChooseCourse chooseCourse = chooseCourseMapper.selectChooseCourseByCourseIdUserId(userId, course.getCourseId());
            map1.put("course", course);
            map1.put("grade", chooseCourse);
            maps.add(map1);
        }
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("list", maps);
        return map;
    }

    @Override
    public Map<String, Object> getHistoryGrade(Integer userId, Integer term, Integer pageNum, Integer pageSize) {
        int first = pageNum == null ? 1 : pageNum;
        int second = pageSize == null ? 5 : pageSize;
        if (term == 0) {
            term = null;
        }
        PageHelper.startPage(first, second);
        List<Course> courses = courseMapper.getHistoryGrade(userId, term);
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            ChooseCourse chooseCourse = chooseCourseMapper.selectChooseCourseByCourseIdUserId(userId, course.getCourseId());
            map1.put("course", course);
            map1.put("grade", chooseCourse);
            maps.add(map1);
        }
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("list", maps);
        return map;
    }

    private List<Course> getAllThisStartCourses(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("term_status", 1);
        Term term = termMapper.selectOne(queryWrapper);
        List<Course> courses = courseMapper.getAllStartCourses(null, term.getTermId());
        return courses;
    }
}
