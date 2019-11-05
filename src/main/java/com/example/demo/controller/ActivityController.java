package com.example.demo.controller;

import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */

@RestController
@RequestMapping(value = "/Activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @RequestMapping(value = "/sign")
    public HttpResult sign(@RequestParam(value = "userId") String userId){
        return activityService.sign(userId);
    }
}
