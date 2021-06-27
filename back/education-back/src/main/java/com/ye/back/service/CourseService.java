package com.ye.back.service;

import com.ye.back.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.back.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 * @author 何进业
 * @since 2021-06-10
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> getThisCourses(Integer userId, Integer pageNum, Integer pageSize);

    Map<String, Object> getChooseCourseResult(Integer userId, Integer pageNum, Integer pageSize);

    int deleteChooseCourse(Integer userId, Integer courseId);

    int insertChooseCourse(Integer userId, Integer courseId);

    boolean isContradict(Integer userId, Integer courseId);

    Map<String, Object> getHistoryCourses(Integer userId, Integer term, Integer pageNum, Integer pageSize);

    Map<String, Object> getThisStartCourses(Integer userId, Integer pageNum, Integer pageSize);

    Map<String, Object> getHistoryStartCourses(Integer userId, Integer term, Integer pageNum, Integer pageSize);

    Map<String, Object> getALLStartCourses(Integer schoolId, Integer term, Integer pageNum, Integer pageSize);

    void openChooseCourse();

    void closeChooseCourse();

    Map<String, Object> getThisGrade(Integer userId, Integer pageNum, Integer pageSize);

    Map<String, Object> getHistoryGrade(Integer userId, Integer term, Integer pageNum, Integer pageSize);
}
