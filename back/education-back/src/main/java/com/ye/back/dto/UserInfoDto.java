package com.ye.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName:com.ye.back.dto
 * @ClassName:UserInfoDto
 * @Description:
 * @author:何进业
 * @date:2021/6/1 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String username;

    private String password;
}
