package com.ye.back.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.ye.back.entity.ChooseCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 何进业
 * @since 2021-06-10
 */
@Mapper
public interface ChooseCourseMapper extends MppBaseMapper<ChooseCourse> {

    int deleteChooseCourse(int userId, int courseId);

    int insertChooseCourse(int userId, int courseId);

    ChooseCourse selectChooseCourseByCourseIdUserId(int userId, int courseId);

    int updateChooseCourse(int userId, int courseId, double usualGrade, double endGrade, double chooseStatus);
}
