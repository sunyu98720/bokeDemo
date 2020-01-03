package com.example.demo.TimeTask;

import com.example.demo.service.DeleteLoginStatus;
import com.example.demo.service.impl.ActivityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author
 *定时删除数据库登录日志
 * @param
 * @return
 * @time
 */
@Component
@Configuration
@EnableScheduling
public class StartTimeTask {

    @Autowired
    private DeleteLoginStatus deleteLoginStatus;

    private static final Logger logger = LoggerFactory.getLogger(StartTimeTask.class);

    @Scheduled(cron = "0 */30 * * * ?")
    private void configureTasks(){
        logger.info("开始执行定时删除登录log");
        deleteLoginStatus.deleteStatus();

    }
}
