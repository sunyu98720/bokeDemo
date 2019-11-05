package com.example.demo.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
public class TimeUtils {
    /**
     * 获得当天零时零分零秒
     * @return Date类型
     */
    public Date initDateToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    /**
    *@explain 获取当天时间戳零时零分零秒
    *Author
    *@return long 类型
    *@time 2019/11/5 13:59
    */
    public long initToday(){
        long current = System.currentTimeMillis();
        long zero = current-(current+ TimeZone.getDefault().getRawOffset())%(1000*3600*24);
        return zero;
    }
    /**
    *@explain 
    *Author 
    *@return String
    *@time 2019/11/5 14:22
    */ 
    public String initDate(){
        long current = System.currentTimeMillis();
        long zero = current-(current+ TimeZone.getDefault().getRawOffset())%(1000*3600*24);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(zero));
    }
    /**
    *@explain
    *Author
    *@return 年月日
    *@time 2019/11/5 14:24
    */
    public String initDateInt(){
            long current = System.currentTimeMillis();
            long zero = current-(current+ TimeZone.getDefault().getRawOffset())%(1000*3600*24);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(new Date(zero));
    }
}
