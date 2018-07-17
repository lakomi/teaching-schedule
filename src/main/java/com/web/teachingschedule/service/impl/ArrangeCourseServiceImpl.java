package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.*;
import com.web.teachingschedule.enums.BackMessageEnum;
import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.service.ArrangeCourseService;
import com.web.teachingschedule.utils.*;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.dto.*;
import com.web.teachingschedule.vo.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排课接口实现
 * @author q
 */
@Service
@Slf4j
public class ArrangeCourseServiceImpl implements ArrangeCourseService {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TeachingClassDAO teachingClassDAO;
    @Autowired
    private CourseTableDAO courseTableDAO;
    @Autowired
    private ExperimentDAO experimentDAO;
    @Autowired
    private LabRoomDAO labRoomDAO;

    @Override
    public ResultVo chooseTerm(String termName, HttpServletRequest request) {
        log.info("前端选择的term： " + termName);
        if (!SessionUtil.SettermToSession(termName, request)){
            throw new TeachingException(TeachExceptionEnum.CHOOSE_TERM_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.CHOOSE_SUCCESS.getMessage());
    }

    @Override
    public ResultVo chooseTeachingClass(String courseId,String classId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!SessionUtil.SetClassAndCourseToSession(courseId, classId, request)){
            throw new TeachingException(TeachExceptionEnum.CHOOSE_CLASS_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.CHOOSE_SUCCESS.getMessage());
    }

    @Override
    public ResultVo cancelArrangeCourse(String ctId) {
        int i = courseTableDAO.deleteTable(ctId);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.CANCEL_SUCCESS.getMessage());
    }

    @Override
    public ResultVo arrangeCourse(ArrcourseDTO arrcourseDTO) {
        /** 排课冲突 */
        if (checkClash(arrcourseDTO)){
            throw new TeachingException(TeachExceptionEnum.COURSE_CLASH);
        }
        /** 查询课程的教学班，从而得到教师id */
        TeachingClass teachingClass = teachingClassDAO.getClassByCourseAndClass(arrcourseDTO.getCourseId(),arrcourseDTO.getClassId());
        /** 只为查询个机房容纳人数 */
        LabRoom labRoom = labRoomDAO.getRoomByName(arrcourseDTO.getLabName());
        CourseTable courseTable = new CourseTable();
        BeanUtils.copyProperties(arrcourseDTO,courseTable);
        courseTable.setCtId(KeyUtil.getUniqueKey());
        courseTable.setCtPeople(labRoom.getLabPeople());
        courseTable.setUserId(teachingClass.getUserId());
        log.info(courseTable.toString());
        /**排课*/
        int i = courseTableDAO.addTable(courseTable);
        if (i != 1) {
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.ADD_SUCCESS.getMessage());
    }

    @Transactional
    @Override
    public ResultVo arrangeLotCourses(ArrlotcourseDTO arrlotcourseDTO) {
        /** 查询课程的教学班，从而得到教师id */
        TeachingClass teachingClass = teachingClassDAO.getClassByCourseAndClass(arrlotcourseDTO.getCourseId(),arrlotcourseDTO.getClassId());
        /** 判断课程是否冲突 */
        if (checklotClash(arrlotcourseDTO)){
            throw new TeachingException(TeachExceptionEnum.COURSE_IS_CLASH);
        }
        /** 只为查询个机房容纳人数 */
        LabRoom labRoom = labRoomDAO.getRoomByName(arrlotcourseDTO.getLabName());

        CourseTable courseTable = new CourseTable();
        courseTable.setTermName(arrlotcourseDTO.getTermName());
        courseTable.setCtOneday(arrlotcourseDTO.getOneDay());
        courseTable.setCtPeriod(arrlotcourseDTO.getPeriod());
        courseTable.setLabName(arrlotcourseDTO.getLabName());
        courseTable.setUserId(teachingClass.getUserId());
        courseTable.setCourseId(arrlotcourseDTO.getCourseId());
        courseTable.setCtPeople(labRoom.getLabPeople());
        courseTable.setClassId(arrlotcourseDTO.getClassId());

        /** 循环插入数据库中 */
        for (int i = 0;i<=arrlotcourseDTO.getEndWeek()-arrlotcourseDTO.getStartWeek();i++){
            courseTable.setCtId(KeyUtil.getUniqueKey());
            courseTable.setCtWeek(arrlotcourseDTO.getStartWeek()+i);
            int temp = courseTableDAO.addTable(courseTable);
            if (temp != 1) {
                throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
            }
        }
        return ResultVoUtil.success(BackMessageEnum.ADD_SUCCESS.getMessage());
    }

    @Override
    public ResultVo getTablesByWeek() {

        String userId = AuthenticationUtil.getAuthUserId();
        /** 按周次查询排课表 */
        List<CourseTable> courseTableList = courseTableDAO.getTablesByTeacher(userId);
        System.out.println("按周次查出的课表信息： "+courseTableList.toString());
        /** 将查出的排课表放入应返回的信息中 */
        List<BackTeacherTableDTO> tableDTOList = courTablesToDTOs(courseTableList);

        return ResultVoUtil.success(tableDTOList);
    }

    @Override
    public ResultVo getTtables(String userName,HttpServletRequest request,int ctWeek) {
        UserInfo userInfo = userDAO.getUserByName(userName);
        HttpSession session = request.getSession();
        String termName =(String) session.getAttribute("term");
        if (termName == null){
            throw new TeachingException(TeachExceptionEnum.FIRST_CHOOSE_TERM);
        }
        List<CourseTable> courseTableList = courseTableDAO.getTablesByTeacherAndTerm(userInfo.getUserId(),termName,ctWeek);
        /** 将查出的排课表放入应返回的信息中 */
        List<BackTeacherTableDTO> tableDTOList = courTablesToDTOs(courseTableList);
        return ResultVoUtil.success(tableDTOList);
    }

    @Override
    public ResultVo getTCtables(String userName,String courseName,int ctWeek,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String termName =(String) session.getAttribute("term");
//        String termName = "2016春";
        if (termName == null){
            throw new TeachingException(TeachExceptionEnum.FIRST_CHOOSE_TERM);
        }
        UserInfo userInfo = userDAO.getUserByName(userName);
        Course course = courseDAO.getCourseByName(courseName);
        List<CourseTable> courseTableList = courseTableDAO.getTablesByTeaAndCou(userInfo.getUserId(),course.getCourseId(),ctWeek,termName);
        List<BackACDTO> backACDTOList = tranBackDTO(courseTableList);
        return ResultVoUtil.success(backACDTOList);
    }

    @Override
    public ResultVo getClassTable(String classId,String userName,int ctWeek,String termName) {

        List<CourseTable> courseTableList = new ArrayList<>();
        List<BackTeacherTableDTO> tableDTOList = new ArrayList<>();
        /** 按教学班号，学期，周数，查询课表 */
        if (userName.length() == 0){
             courseTableList = courseTableDAO.getTablesByClassId(classId,termName,ctWeek);
             tableDTOList = courTablesToDTOs(courseTableList);
        }
        UserInfo  userInfo = userDAO.getUserByName(userName);
        /** 按教师名，学期，周数，查询课表 */
        if (classId.length() == 0){
            courseTableList = courseTableDAO.getTablesByTeacherAndTerm(userInfo.getUserId(),termName,ctWeek);
            tableDTOList = courTablesToDTOs(courseTableList);
        }
        /** 按教师名，教学班号，学期，周数查询课表*/
        if (classId.length()!=0&&userName.length()!=0){
            courseTableList = courseTableDAO.getTablesByTeaAndClass(userInfo.getUserId(),classId,ctWeek,termName);
            tableDTOList = courTablesToDTOs(courseTableList);
        }
        return ResultVoUtil.success(tableDTOList);
    }

    @Override
    public ResultVo getArrangeVo(String labName, int ctWeek, String termName) {
        List<CourseTable> courseTableList = courseTableDAO.getTablesByTermAndWeekAndLab(termName,ctWeek,labName);
        List<RarrangeVo> rarrangeVoList = tranRVo(courseTableList);
        return ResultVoUtil.success(rarrangeVoList);
    }

    /**
     * 判断单次排课添加的时间是否有冲突
     * @param arrcourseDTO
     * @return
     */
    public Boolean checkClash(ArrcourseDTO arrcourseDTO){
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setTermName(arrcourseDTO.getTermName());
        timeDTO.setCtWeek(arrcourseDTO.getCtWeek());
        timeDTO.setCtOneday(arrcourseDTO.getCtOneday());
        timeDTO.setCtPeriod(arrcourseDTO.getCtPeriod());
        timeDTO.setLabName(arrcourseDTO.getLabName());
        CourseTable courseTable = courseTableDAO.getTableByTime(timeDTO);
        if (StringUtils.isEmpty(courseTable)){
            //添加课程的时间段不冲突
            return false;
        }
        System.out.println("courseTable: "+courseTable.toString());
        return true;
    }

    /**
     * 判断批量排课中是否有冲突
     * @return
     */
    public Boolean checklotClash(ArrlotcourseDTO arrlotcourseDTO){
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setTermName(arrlotcourseDTO.getTermName());
        timeDTO.setLabName(arrlotcourseDTO.getLabName());
        timeDTO.setCtOneday(arrlotcourseDTO.getOneDay());
        timeDTO.setCtPeriod(arrlotcourseDTO.getPeriod());
        for (int i = arrlotcourseDTO.getStartWeek();i<=arrlotcourseDTO.getEndWeek();i++){
            timeDTO.setCtWeek(i);
            CourseTable courseTable = courseTableDAO.getTableByTime(timeDTO);
            if (!StringUtils.isEmpty(courseTable)){
                /** 某个时间段有冲突 */
                return true;
            }
        }
        return false;
    }

    /**
     * 返回给教师的课表视图
     * [ 本应写在个 util 中，但在util中无法调用dao层的方法。 ]  ===> ioc容器外的类不能使用ioc容器内的类的方法？？?
     * @param courseTableList
     * @return
     */
    public List<BackTeacherTableDTO> courTablesToDTOs(List<CourseTable> courseTableList){
        List<BackTeacherTableDTO> tableDTOList = new ArrayList<>();
        int k = 0;
        /** 存放已经循环过的课表信息 */
        int[] indexs = new int[courseTableList.size()];

        for (int i = 0;i<courseTableList.size();i++){
            /** 判断是否已经处理过课表 */
            if (isExit(i,indexs)){
                continue;
            }

            BackTeacherTableDTO tableDTO = new BackTeacherTableDTO();
            /**周数,星期几,节次,课程名,教师名等信息*/
            tableDTO.setCtWeek(courseTableList.get(i).getCtWeek());
            tableDTO.setCtOneday(courseTableList.get(i).getCtOneday());
            tableDTO.setCtPeriod(courseTableList.get(i).getCtPeriod());
            tableDTO.setCourseName(courseDAO.getCourseById(courseTableList.get(i).getCourseId()).getCourseName());
            tableDTO.setUserName(userDAO.getUserById(courseTableList.get(i).getUserId()).getUserName());

            /** 设置机房信息 */
            List<String> labNames = new ArrayList<>();
            labNames.add(courseTableList.get(i).getLabName());
            /**采用循环判断教师是否有两个机房*/
            for (int j = i;j<courseTableList.size();j++){
                /** 时间，人物相同，地点不同，认定为排了两个机房 */
                if ((courseTableList.get(i).getTermName().equals(courseTableList.get(j).getTermName())) && (courseTableList.get(i).getCtOneday() == courseTableList.get(j).getCtOneday()) &&
                        (courseTableList.get(i).getCtPeriod() == courseTableList.get(j).getCtPeriod()) &&
                        (courseTableList.get(i).getUserId().equals(courseTableList.get(j).getUserId())) &&
                        (!courseTableList.get(i).getLabName().equals(courseTableList.get(j).getLabName()))){
                    labNames.add(courseTableList.get(j).getLabName());
                    /** 放入存放课表信息的数组中 */
                    indexs[k] = j;
                    k ++;
                }
            }
            tableDTO.setLabName(labNames);
            /** 加到 tableDTOList 中 */
            tableDTOList.add(tableDTO);
        }
        System.out.println(tableDTOList.toString());
        return tableDTOList;
    }

    /**
     * 管理员 教师-课程 查询
     * 转换返回视图 （有教学班号）
     * @return
     */
    public List<BackACDTO> tranBackDTO(List<CourseTable> courseTableList){
        List<BackACDTO> backACDTOList = new ArrayList<>();

        int k = 0;
        /** 存放已经循环过的课表信息 */
        int[] indexs = new int[courseTableList.size()];

        for (int i = 0;i<courseTableList.size();i++){
            /** 判断是否已经处理过课表 */
            if (isExit(i,indexs)){
                continue;
            }

            BackACDTO tableDTO = new BackACDTO();
            /**周数,星期几,节次,课程名,教师名等信息*/
            tableDTO.setCtWeek(courseTableList.get(i).getCtWeek());
            tableDTO.setCtOneday(courseTableList.get(i).getCtOneday());
            tableDTO.setCtPeriod(courseTableList.get(i).getCtPeriod());
            tableDTO.setClassId(courseTableList.get(i).getClassId());

            /** 设置机房信息 */
            List<String> labNames = new ArrayList<>();
            labNames.add(courseTableList.get(i).getLabName());
            /**采用循环判断教师是否有两个机房*/
            for (int j = i;j<courseTableList.size();j++){
                /** 时间，教学班相同，地点不同，认定为排了两个机房 */
                if ((courseTableList.get(i).getTermName().equals(courseTableList.get(j).getTermName())) && (courseTableList.get(i).getCtOneday() == courseTableList.get(j).getCtOneday()) &&
                        (courseTableList.get(i).getCtPeriod() == courseTableList.get(j).getCtPeriod()) &&
                        (courseTableList.get(i).getClassId().equals(courseTableList.get(j).getClassId())) &&
                        (!courseTableList.get(i).getLabName().equals(courseTableList.get(j).getLabName()))){
                    labNames.add(courseTableList.get(j).getLabName());
                    /** 放入存放课表信息的数组中 */
                    indexs[k] = j;
                    k ++;
                }
            }
            tableDTO.setLabName(labNames);
            /** 加到 tableDTOList 中 */
            backACDTOList.add(tableDTO);
        }
        System.out.println(backACDTOList.toString());
        return backACDTOList;
    }

    /**
     * 转换返回管理员排课视图
     * @return
     */
    public List<RarrangeVo> tranRVo(List<CourseTable> courseTableList){
        List<RarrangeVo> rarrangeVoList = new ArrayList<>();
        for (CourseTable courseTable:courseTableList){
            RarrangeVo rarrangeVo = new RarrangeVo();
            rarrangeVo.setCtId(courseTable.getCtId());
            rarrangeVo.setCtWeek(courseTable.getCtWeek());
            rarrangeVo.setCtOneday(courseTable.getCtOneday());
            rarrangeVo.setCtPeriod(courseTable.getCtPeriod());
            rarrangeVo.setCourseName(courseDAO.getCourseById(courseTable.getCourseId()).getCourseName());
            rarrangeVo.setUserName(userDAO.getUserById(courseTable.getUserId()).getUserName());
            rarrangeVo.setLabName(courseTable.getLabName());

            rarrangeVoList.add(rarrangeVo);
        }
        System.out.println(rarrangeVoList.toString());
        return rarrangeVoList;

    }

    /**
     * 判断一个数是否存在一个数组中
     * @param i
     * @param indexs
     * @return
     */
    public Boolean isExit(int i,int[] indexs){
        if (i == 0){
            return false;
        }
        for (int j = 0;j<indexs.length;j++){
            if (indexs[j] == i){
                return true;
            }
        }
        return false;
    }





}
