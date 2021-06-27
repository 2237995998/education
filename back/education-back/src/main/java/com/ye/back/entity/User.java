package com.ye.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author 何进业
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("加密的盐")
    private String salt;

    @ApiModelProperty("姓名")
    private String nickname;

    @ApiModelProperty("学院id")
    private int schoolId;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("电话号码")
    private String tel;

    @ApiModelProperty("班级")
    private String studentClass;
}
