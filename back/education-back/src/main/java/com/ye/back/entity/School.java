package com.ye.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "school_id", type = IdType.AUTO)
    private Integer schoolId;

    private String schoolName;


}
