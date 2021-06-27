package com.ye.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author 何进业
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("课程实体")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.AUTO)
    @ApiModelProperty("课程id")
    private Integer courseId;

    @ApiModelProperty("课程代码")
    private String courseCode;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程类型")
    private String courseType;

    @ApiModelProperty("学院id")
    private Integer schoolId;

    @ApiModelProperty("教师id")
    private Integer teacherId;

    @ApiModelProperty("课程状态：表示是否为本学期课程")
    private Integer courseStatus;

    @ApiModelProperty("开课学期名")
    private String courseTerm;

    @ApiModelProperty("开课学期id")
    private Integer courseTermId;

    @ApiModelProperty("上课时间")
    private String courseTime;

    @ApiModelProperty("上课地点")
    private String coursePlace;

    @ApiModelProperty("教学班号")
    private Integer courseClass;

    @ApiModelProperty("学分")
    private Integer courseCredit;

    @ApiModelProperty("平时成绩占比")
    private double usualWeight;

    @ApiModelProperty("最终成绩占比")
    private double endWeight;

}
