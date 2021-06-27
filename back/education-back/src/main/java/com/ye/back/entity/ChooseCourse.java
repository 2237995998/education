package com.ye.back.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
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
@ApiModel("学生选课实体")
public class ChooseCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @MppMultiId
    @ApiModelProperty("学生id")
    private Integer userId;

    @MppMultiId
    @ApiModelProperty("课程id")
    private Integer courseId;

    @ApiModelProperty("平时成绩")
    private Double usualGrade;

    @ApiModelProperty("期末考试成绩")
    private Double endGrade;

    @ApiModelProperty("总成绩")
    private Double totalGrade;

    @ApiModelProperty("是否出成绩")
    private int chooseStatus;

    @ApiModelProperty("是否通过")
    private String isPass;

}
