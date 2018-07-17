package com.web.teachingschedule.dao;

import com.web.teachingschedule.vo.entity.LabRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机房表操作
 * @author q
 */
@Repository
@Mapper
public interface LabRoomDAO {
    /**
     * 机房列表
     * @return
     */
    List<LabRoom> getRooms();
    /**
     * 根据机房名称查询机房信息
     * @param labName
     * @return
     */
    LabRoom getRoomByName(@Param("labName") String labName);

    /**
     * 修改机房信息
     * @param labRoom
     * @return
     */
    int modifyLabRoom(LabRoom labRoom);

    /**
     * 增加机房
     * @param labRoom
     * @return
     */
    int addLabRoom(LabRoom labRoom);

    /**
     * 删除机房
     * @param labName
     * @return
     */
    int deleteLabRoom(@Param("labName") String labName);



}
