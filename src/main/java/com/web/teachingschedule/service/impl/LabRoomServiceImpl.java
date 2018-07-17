package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.LabRoomDAO;
import com.web.teachingschedule.enums.BackMessageEnum;
import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.service.LabRoomService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.utils.TransformToDTO;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.entity.LabRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 机房接口实现
 * @author q
 */
@Service
@Slf4j
public class LabRoomServiceImpl implements LabRoomService {

    @Autowired
    private LabRoomDAO labRoomDAO;

    @Override
    public ResultVo getLabRooms() {
        List<LabRoom> labRoomList = labRoomDAO.getRooms();
        return ResultVoUtil.success(labRoomList);
    }

    @Override
    public ResultVo addLabRoom(LabRoom labRoom) {

        int i = labRoomDAO.addLabRoom(labRoom);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.ADD_SUCCESS.getMessage());
    }

    @Override
    public ResultVo deleteRoom(String labName) {

        int i = labRoomDAO.deleteLabRoom(labName);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.DEL_SUCCESS.getMessage());
    }

    @Override
    public ResultVo modifyRoom(LabRoom labRoom) {
        /** 没有传入机房名称 */
        if (StringUtils.isEmpty(labRoom.getLabName())){
            throw new TeachingException(TeachExceptionEnum.LABROOM_NAME_NOT_NULL);
        }
        /** labRoom 中信息没有修改 不进行数据库操作 */
        if (StringUtils.isEmpty(labRoom.getLabDescribe()) &&
                labRoom.getLabNumber()==-1 &&
                labRoom.getLabPeople()==-1){
            return ResultVoUtil.success("无任何修改");
        }
        int i = labRoomDAO.modifyLabRoom(labRoom);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.MODIFY_SUCCESS.getMessage());
    }
}
