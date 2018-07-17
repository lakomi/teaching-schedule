package com.web.teachingschedule.utils;

import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * session 中存取数据
 * @author q
 */
@Slf4j
public class SessionUtil {
    /**
     * 将学期存入 session
     * @param termName
     * @param request
     * @return
     */
    public static Boolean SettermToSession(String termName, HttpServletRequest request){
        HttpSession session = request.getSession();
        try{
            session.setAttribute("term",termName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        log.info("term 存入session");
        return true;
    }

    /**
     * 将课程号，教学班号存入 session
     * @param courseId
     * @param classId
     * @param request
     * @return
     */
    public static Boolean SetClassAndCourseToSession(String courseId,String classId,HttpServletRequest request){
        HttpSession session = request.getSession();
        try{
            session.setAttribute("courseId", courseId);
            session.setAttribute("classId", classId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        log.info("courseId: "+courseId+" 存入session");
        log.info("classId: "+classId+" 存入session");
        return true;
    }



}
