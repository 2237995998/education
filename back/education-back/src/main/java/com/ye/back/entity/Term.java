package com.ye.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Term implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "term_id", type = IdType.AUTO)
    private Integer termId;

    private String termName;

    private Integer termStatus;
}
