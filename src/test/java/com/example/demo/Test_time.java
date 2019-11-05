package com.example.demo;

import com.example.demo.common.TimeUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.Format;
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
public class Test_time {
    @Test
    public void time(){
        TimeUtils timeUtils = new TimeUtils();
        System.out.println(timeUtils.initDateToday());
        long current = System.currentTimeMillis();
        long zero = current-(current+ TimeZone.getDefault().getRawOffset())%(1000*3600*24);
        System.out.println(zero);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date(zero)));
    }
}
