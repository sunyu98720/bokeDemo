package com.example.demo.TimeTask;

import com.example.demo.service.DeleteLoginStatus;
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

    @Scheduled(cron = "0 */30 * * * ?")
    private void configureTasks(){
        deleteLoginStatus.deleteStatus();

    }
}
