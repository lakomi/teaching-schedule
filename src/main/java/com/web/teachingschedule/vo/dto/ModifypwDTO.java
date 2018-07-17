package com.web.teachingschedule.vo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码传入信息
 * @author q
 */
@Data
public class ModifypwDTO {

    @NotBlank(message = "用户名不能为空")
    private String userId;

    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
