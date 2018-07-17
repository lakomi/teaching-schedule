package com.web.teachingschedule.vo.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 机房实体
 * @author q
 */
@Data
public class LabRoom {
    /**
     * 机房名称
     */
    @NotBlank(message = "机房名称不能为空")
    private String labName;
    /**
     * 设备类型(PC,嵌入式设备，网络)
     */
    private String labDescribe;
    /**
     * 设备数量
     */
    @Min(value = 1,message = "请输入正确的设备数量")
    private int labNumber = -1;
    /**
     * 机房容纳人数
     */
    @Min(value = 1,message = "请输入正确的人数")
    private int labPeople = -1;

}
