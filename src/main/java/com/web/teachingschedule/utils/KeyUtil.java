package com.web.teachingschedule.utils;

import java.util.Random;

/**
 * 生成排课表主键编号
 * @author q
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+随机数(3位)
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(90) +10;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
