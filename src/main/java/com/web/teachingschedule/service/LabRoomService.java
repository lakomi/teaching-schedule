package com.web.teachingschedule.service;

import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.entity.LabRoom;

/**
 * 机房 service
 * @author q
 */
public interface LabRoomService {
    /**
     * 得到机房列表
     * @return
     */
    ResultVo getLabRooms();

    /**
     * 添加机房
     * @return
     */
    ResultVo addLabRoom(LabRoom labRoom);

    /**
     * 删除机房
     * @param labName
     * @return
     */
    ResultVo deleteRoom(String labName);

    /**
     * 修改机房信息
     * @param labRoom
     * @return
     */
    ResultVo modifyRoom(LabRoom labRoom);


}
