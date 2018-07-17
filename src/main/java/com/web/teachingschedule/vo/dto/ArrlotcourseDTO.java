package com.web.teachingschedule.vo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 批量排课传入信息
 * @author q
 */
@Data
public class ArrlotcourseDTO {
    /**
     * 机房名称
     */
    @NotBlank(message = "机房名称不能为空")
    private String labName;
    /**
     * 开始周次
     */
    @Min(value = 1,message = "请输入正确的周数")
    private int startWeek;
    /**
     * 结束周次
     */
    @Min(value = 1,message = "请输入正确的周数")
    private int endWeek;
    /**
     * 星期几
     */
    @Max(value = 7,message = "请输入正确的天数")
    @Min(value = 1,message = "请输入正确的天数")
    private int oneDay;
    /**
     * 节次
     */
    @Max(value = 5,message = "请输入正确的节次")
    @Min(value = 1,message = "请输入正确的节次")
    private int period;
    /**
     * 学期
     */
    @NotBlank(message = "请输入学期")
    private String termName;
    /**
     * 教学班号
     */
    @NotBlank(message = "请输入教学班号")
    private String classId;
    /**
     * 课程号
     */
    @NotBlank(message = "请输入课程号")
    private String courseId;
}
