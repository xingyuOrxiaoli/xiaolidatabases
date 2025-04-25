package com.xiaoli.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: my-blog
 * @classname: DateUtils
 **/
public class DateUtils {

    /**
     * 获得本地当前时间
     */
    public static Timestamp getLocalCurrentDate(){
        return new Timestamp(new Date().getTime());
    }
}
