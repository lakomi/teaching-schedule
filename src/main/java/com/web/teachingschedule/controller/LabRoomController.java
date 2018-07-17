package com.web.teachingschedule.controller;

import com.web.teachingschedule.enums.CodeEnum;
import com.web.teachingschedule.service.LabRoomService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.entity.LabRoom;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 机房操作接口
 * @author q
 */
@RequestMapping("/root")
@RestController
@Slf4j
@CrossOrigin
public class LabRoomController {

    @Autowired
    private LabRoomService labRoomService;

    /**
     * 获取机房详情列表
     * @return
     */
    @RequestMapping("/getLabRooms")
    public ResultVo getLabRooms(){
        return labRoomService.getLabRooms();
    }
    /**
     * 添加机房
     * @param labRoom
     * @param bindingResult
     * @return
     */
    @RequestMapping("/addLabRoom")
    public ResultVo addLabRoom(@Valid LabRoom labRoom, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.info("/arrange @Valid 注解 验证错误");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultVoUtil.error(CodeEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return labRoomService.addLabRoom(labRoom);
    }
    /**
     * 删除机房
     * @param labName
     * @return
     */
    @RequestMapping("/delLabRoom")
    public ResultVo delLabRoom(@RequestParam("labName") String labName){
        return labRoomService.deleteRoom(labName);
    }
    /**
     * 修改机房信息
     * @param labRoom
     * @return
     */
    @RequestMapping("/modifylab")
    public ResultVo modifylab(LabRoom labRoom){
        return labRoomService.modifyRoom(labRoom);
    }






}
